package dao;

import models.Curso;
import models.Estudiante;
import models.Inscripcion;
import org.junit.jupiter.api.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests de integración para {@link CursoDAO}.
 * <p>
 * Antes de cada caso se limpian por completo las tablas implicadas,
 * **borrando primero la tabla puente <code>progreso_flashcards</code>**
 * para no violar la clave foránea hacia <code>progresos</code>.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CursoDAOTest {

	/* ───────── infraestructura ───────── */

	private static EntityManagerFactory emf;
	private EntityManager em;
	private CursoDAO cursoDAO;

	/* ───────── lifecycle ───────── */

	@BeforeAll
	void init() {
		emf = Persistence.createEntityManagerFactory("flashcards-jpa");
		cursoDAO = new CursoDAO();
	}

	@BeforeEach
	void setUp() {
		em = emf.createEntityManager();
		
		/* ---------- limpieza ordenada para no violar FK ---------- */
		em.getTransaction().begin();
		em.createNativeQuery("DELETE FROM progreso_flashcards").executeUpdate(); // 1º tabla puente
		em.createQuery("DELETE FROM ProgresoEstudiante").executeUpdate();       // 2º tabla padre
		em.createQuery("DELETE FROM Inscripcion").executeUpdate();
		em.createQuery("DELETE FROM Curso").executeUpdate();
		em.createQuery("DELETE FROM Estudiante").executeUpdate();
		em.getTransaction().commit();
		/* --------------------------------------------------------- */
	}

	@AfterAll
	void tearDown() {
		em.close();
		emf.close();
		CursoDAO.cerrarFactory();
	}

	/* ───────── tests ───────── */

	@Test
	void testGuardarCurso() {
		// Crear estudiante único para este test
		String testId = String.valueOf(System.nanoTime());
		Estudiante creador = crearEstudiante("Ana" + testId, "ana" + testId);
		
		String nombreCurso = "Curso A " + testId;
		Curso curso = new Curso(nombreCurso, "Desc A " + testId,
				Curso.TipoEstrategia.SECUENCIAL, new HashSet<>(), creador, true);

		cursoDAO.guardar(curso);

		List<Curso> resultados =
				em.createQuery("SELECT c FROM Curso c", Curso.class).getResultList();

		assertEquals(1, resultados.size());
		assertEquals(nombreCurso, resultados.get(0).getNombre());
	}

	@Test
	void testBuscarTodos() {
		// Crear estudiante único para este test
		String testId = String.valueOf(System.nanoTime());
		Estudiante creador = crearEstudiante("Luis" + testId, "luis" + testId);
		
		crearCurso("Curso A " + testId, creador);
		crearCurso("Curso B " + testId, creador);

		List<Curso> cursos = cursoDAO.buscarTodos();
		assertEquals(2, cursos.size());
	}

	@Test
	void testBuscarCursosDelUsuario() {
		// Crear estudiantes únicos para este test
		String testId = String.valueOf(System.nanoTime());
		Estudiante creador = crearEstudiante("Maria" + testId, "maria" + testId);
		Estudiante otro = crearEstudiante("Pedro" + testId, "pedro" + testId);
		
		crearCurso("Curso Propio " + testId, creador);
		Curso ajeno = crearCurso("Curso Ajeno " + testId, otro);
		inscribirEstudiante(ajeno, creador);          // Maria se inscribe en curso de Pedro

		List<Curso> cursos = cursoDAO.buscarCursosDelUsuario(creador);
		assertEquals(2, cursos.size());
	}

	@Test
	void testBuscarCursosPublicosDisponibles() {
		// Crear estudiantes únicos para este test
		String testId = String.valueOf(System.nanoTime());
		Estudiante creador = crearEstudiante("Carlos" + testId, "carlos" + testId);
		Estudiante otro = crearEstudiante("Sofia" + testId, "sofia" + testId);
		
		Curso publico  = crearCurso("Curso Público " + testId, otro, true);
		Curso privado  = crearCurso("Curso Privado " + testId, otro, false);
		inscribirEstudiante(publico, creador);        // Carlos ya inscrito en "publico"
		Curso otroPub  = crearCurso("Curso No Inscrito " + testId, otro, true);

		List<Curso> disponibles = cursoDAO.buscarCursosPublicosDisponibles(creador);

		assertEquals(1, disponibles.size());
		assertEquals(otroPub.getNombre(), disponibles.get(0).getNombre());

		// Asegurar que no se cuela el curso privado
		List<String> nombres = disponibles.stream().map(Curso::getNombre).toList();
		assertFalse(nombres.contains(privado.getNombre()));
	}

	/* ───────── helpers ───────── */

	private Estudiante crearEstudiante(String nombre, String emailPrefix) {
		em.getTransaction().begin();
		String emailUnico = emailPrefix + "+" + System.nanoTime() + "@correo.com";
		Estudiante estudiante = new Estudiante(nombre, emailUnico, "1234");
		em.persist(estudiante);
		em.getTransaction().commit();
		return estudiante;
	}

	private Curso crearCurso(String nombre, Estudiante autor) {
		return crearCurso(nombre, autor, true);
	}

	private Curso crearCurso(String nombre,
	                          Estudiante autor,
	                          boolean esPublico) {

		Curso c = new Curso(nombre, "Descripción de " + nombre,
				Curso.TipoEstrategia.SECUENCIAL, new HashSet<>(), autor, esPublico);

		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();
		return c;
	}

	private void inscribirEstudiante(Curso curso, Estudiante estudiante) {
		em.getTransaction().begin();
		Inscripcion insc =
				new Inscripcion(estudiante, curso, Curso.TipoEstrategia.SECUENCIAL);
		em.persist(insc);
		em.getTransaction().commit();
	}
}