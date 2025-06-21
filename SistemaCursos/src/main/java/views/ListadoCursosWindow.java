package views;

import dao.CursoDAO;
import dao.InscripcionDAO;
import models.Curso;
import models.Estudiante;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ListadoCursosWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Estudiante estudianteActual;
	private final CursoDAO   cursoDAO = new CursoDAO();
	private final InscripcionDAO inscripcionDAO = new InscripcionDAO();

	private final DefaultListModel<Curso> model = new DefaultListModel<>();
	private final JList<Curso> cursosList = new JList<>(model);

	private static final Dimension BTN = new Dimension(140,32);

	public ListadoCursosWindow(Estudiante est) {
		this.estudianteActual = est;
		initUI(); cargarCursos();
	}

	private void initUI(){
		setTitle("Mis cursos");
		setSize(600,480); setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		cursosList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		cursosList.setCellRenderer((l,v,i,s,f)->{
			JLabel lbl=(JLabel)new DefaultListCellRenderer().getListCellRendererComponent(l,v,i,s,f);
			if(v instanceof Curso c) {
				String creadorNombre = c.getCreador() != null ? c.getCreador().getNombre() : "Desconocido";
				boolean esMio = c.getCreador() != null && c.getCreador().equals(estudianteActual);
				boolean esInscrito = !esMio && inscripcionDAO.estaInscrito(estudianteActual, c);

				String estado;
				Color color;
				int estilo;

				if (esMio) {
					estado = "PROPIO";
					color = new Color(0, 120, 0); // Verde
					estilo = Font.BOLD;
				} else if (esInscrito) {
					estado = "INSCRITO - Por: " + creadorNombre;
					color = new Color(0, 0, 150); // Azul
					estilo = Font.PLAIN;
				} else {
					// Este caso no debería ocurrir con la nueva consulta
					estado = "DISPONIBLE - Por: " + creadorNombre;
					color = Color.GRAY;
					estilo = Font.ITALIC;
				}

				String visibilidad = "";
				if (esMio) {
					visibilidad = c.isPublico() ? " [PÚBLICO]" : " [PRIVADO]";
				}

				String texto = String.format("%s (%d flashcards) - %s%s",
						c.getNombre(),
						c.getFlashcards().size(),
						estado,
						visibilidad
						);

				lbl.setText(texto);
				lbl.setForeground(color);
				lbl.setFont(lbl.getFont().deriveFont(estilo));
			}
			return lbl;
		});

		JPanel topPanel = new JPanel(new BorderLayout());

		JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel infoLabel = new JLabel("Verde = Mis cursos | Azul = Cursos inscritos");
		infoLabel.setFont(infoLabel.getFont().deriveFont(Font.ITALIC, 11f));
		infoPanel.add(infoLabel);

		JPanel buscarPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton btnBuscarCursos = new JButton("Buscar cursos");
		btnBuscarCursos.setPreferredSize(new Dimension(120, 28));
		btnBuscarCursos.addActionListener(e -> abrirVentanaBusqueda());
		buscarPanel.add(btnBuscarCursos);

		topPanel.add(infoPanel, BorderLayout.WEST);
		topPanel.add(buscarPanel, BorderLayout.EAST);

		JPanel botones = new JPanel();
		JButton btnPract  = new JButton("Practicar");
		JButton btnEditar = new JButton("Editar");
		JButton btnDesinscribir = new JButton("Desinscribir");
		JButton btnRank   = new JButton("Ranking");
		JButton btnCerrar = new JButton("Cerrar");

		for (JButton b : new JButton[]{btnPract,btnEditar,btnDesinscribir ,btnRank,btnCerrar}) b.setPreferredSize(BTN);

		btnPract.addActionListener(e -> practicar());
		btnEditar.addActionListener(e -> editar());
		btnDesinscribir.addActionListener(e -> desinscribir());
		btnRank .addActionListener(e -> ranking());
		btnCerrar.addActionListener(e -> dispose());

		cursosList.addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				Curso seleccionado = cursosList.getSelectedValue();
				boolean haySeleccion = seleccionado != null;
				boolean esMio = haySeleccion && seleccionado.getCreador().equals(estudianteActual);

				btnPract.setEnabled(haySeleccion);
				btnEditar.setEnabled(haySeleccion && esMio); // Solo editar cursos propios
				btnDesinscribir.setEnabled(haySeleccion && !esMio); // Solo desinscribir de cursos ajenos
				btnRank.setEnabled(haySeleccion);
			}
		});

		botones.add(btnPract); botones.add(btnEditar); botones.add(btnDesinscribir); botones.add(btnRank); botones.add(btnCerrar);

		add(topPanel, BorderLayout.NORTH);
		add(new JScrollPane(cursosList), BorderLayout.CENTER);
		add(botones, BorderLayout.SOUTH);

		cursosList.clearSelection();
	}

	private void cargarCursos(){
		model.clear();
		List<Curso> cursos = cursoDAO.buscarCursosDelUsuario(estudianteActual);
		cursos.forEach(model::addElement);

		if (cursos.isEmpty()) {
			info("No tienes cursos. ¡Crea uno o inscríbete a cursos públicos!", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void abrirVentanaBusqueda() {
		new BuscarCursosWindow(estudianteActual, this).setVisible(true);
	}

	private void desinscribir() {
		Curso c = sel();
		if(c == null){info("Selecciona un curso",0); return;}
		if (c.getCreador().equals(estudianteActual)) {info("No puedes desinscribirte de tus propios cursos"); return;}

		int respuesta = JOptionPane.showConfirmDialog(
				this,
				"¿Estás seguro de que quieres desinscribirte del curso '" + c.getNombre() + "'?",
				"Confirmar desinscripción",
				JOptionPane.YES_NO_OPTION
				);

		if (respuesta == JOptionPane.YES_OPTION) {
			inscripcionDAO.eliminar(estudianteActual, c);
			cargarCursos(); // Recargar lista
			info("Te has desinscrito del curso correctamente", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void actualizarLista() {
		cargarCursos();
	}

	private Curso sel(){ return cursosList.getSelectedValue(); }

	private void practicar(){
		Curso c = sel();
		if(c == null){info("Selecciona un curso",0); return;}
		new PracticaWindow(c, estudianteActual).setVisible(true);
		dispose();
	}

	private void editar(){
		Curso c = sel();
		if(c == null){info("Selecciona un curso"); return;}
		if (!c.getCreador().equals(estudianteActual)){info("Solo puedes editar tus propios cursos"); return;}
		new CrearCursoWindow(c, estudianteActual).setVisible(true);
		dispose();
	}

	private void ranking(){
		Curso c = sel();
		if(c == null){info("Selecciona un curso"); return;}
		new RankingWindow(c).setVisible(true);
	}

	private void info(String m){ info(m, JOptionPane.WARNING_MESSAGE); }
	private void info(String m,int t){ JOptionPane.showMessageDialog(this,m,"Aviso",t); }
}
