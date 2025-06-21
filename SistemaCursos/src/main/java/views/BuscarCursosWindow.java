package views;

import dao.CursoDAO;
import dao.InscripcionDAO;
import models.Curso;
import models.Curso.TipoEstrategia;
import models.Estudiante;
import models.Inscripcion;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BuscarCursosWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Estudiante estudianteActual;
	private final ListadoCursosWindow ventanaPadre;
	private final CursoDAO cursoDAO = new CursoDAO();
	private final InscripcionDAO inscripcionDAO = new InscripcionDAO();

	private final DefaultListModel<Curso> model = new DefaultListModel<>();
	private final JList<Curso> cursosList = new JList<>(model);

	public BuscarCursosWindow(Estudiante estudiante, ListadoCursosWindow padre) {
		this.estudianteActual = estudiante;
		this.ventanaPadre = padre;
		initUI();
		cargarCursosDisponibles();
	}

	private void initUI() {
		setTitle("Cursos públicos disponibles");
		setSize(550, 400);
		setLocationRelativeTo(ventanaPadre);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		cursosList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Renderer para cursos disponibles
		cursosList.setCellRenderer((l, v, i, s, f) -> {
			JLabel lbl = (JLabel) new DefaultListCellRenderer().getListCellRendererComponent(l, v, i, s, f);
			if (v instanceof Curso c) {
				String creadorNombre = c.getCreador() != null ? c.getCreador().getNombre() : "Desconocido";
				String texto = String.format("%s (%d flashcards) - Por: %s",
						c.getNombre(),
						c.getFlashcards().size(),
						creadorNombre
						);
				lbl.setText(texto);
				lbl.setForeground(new Color(0, 100, 0)); // Verde oscuro
			}
			return lbl;
		});

		JPanel botones = new JPanel();
		JButton btnInscribir = new JButton("Inscribirse");
		JButton btnCerrar = new JButton("Cerrar");

		btnInscribir.addActionListener(e -> inscribirse());
		btnCerrar.addActionListener(e -> dispose());

		botones.add(btnInscribir);
		botones.add(btnCerrar);

		JLabel info = new JLabel("Cursos públicos donde puedes inscribirte:");
		info.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));

		add(info, BorderLayout.NORTH);
		add(new JScrollPane(cursosList), BorderLayout.CENTER);
		add(botones, BorderLayout.SOUTH);
	}

	private void cargarCursosDisponibles() {
		model.clear();
		List<Curso> cursosDisponibles = cursoDAO.buscarCursosPublicosDisponibles(estudianteActual);
		cursosDisponibles.forEach(model::addElement);

		if (cursosDisponibles.isEmpty()) {
			JOptionPane.showMessageDialog(this, 
					"No hay cursos públicos disponibles para inscribirse", 
					"Sin cursos", 
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void inscribirse() {
		Curso seleccionado = cursosList.getSelectedValue();
		if (seleccionado == null) {
			JOptionPane.showMessageDialog(this, "Selecciona un curso", "Aviso", JOptionPane.WARNING_MESSAGE);
			return;
		}

		// Crear inscripción
		TipoEstrategia estrategiaCurso = seleccionado.getTipoEstrategia();

		Inscripcion inscripcion = new Inscripcion(estudianteActual, seleccionado, estrategiaCurso);
		inscripcionDAO.guardar(inscripcion);

		JOptionPane.showMessageDialog(this, 
				"Te has inscrito correctamente al curso '" + seleccionado.getNombre() + "'", 
				"Inscripción exitosa", 
				JOptionPane.INFORMATION_MESSAGE);

		// Actualizar ventana padre y cerrar
		ventanaPadre.actualizarLista();
		dispose();
	}
}