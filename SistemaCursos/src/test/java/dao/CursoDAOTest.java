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

	private EntityManagerFactory emf;
	private EntityManager em;
	private CursoDAO cursoDAO;

	private Estudiante creador;
	private Estudiante otro;

	/* ───────── lifecycle ───────── */

	@BeforeAll
	void init() {
		emf = Persistence.createEntityManagerFactory("flashcards-jpa");
		em  = emf.createEntityManager();
		cursoDAO = new CursoDAO();
	}

	@BeforeEach
	void setUp() {
		/* ---------- limpieza ordenada para no violar FK ---------- */
		em.getTransaction().begin();
		em.createNativeQuery("DELETE FROM progreso_flashcards").executeUpdate(); // 1º tabla puente
		em.createQuery("DELETE FROM ProgresoEstudiante").executeUpdate();       // 2º tabla padre
		em.createQuery("DELETE FROM Inscripcion").executeUpdate();
		em.createQuery("DELETE FROM Curso").executeUpdate();
		em.createQuery("DELETE FROM Estudiante").executeUpdate();
		em.getTransaction().commit();
		/* --------------------------------------------------------- */

		/* ---------- datos de prueba ---------- */
		em.getTransaction().begin();
		String ts = String.valueOf(System.nanoTime());            // emails únicos
		creador = new Estudiante("Ana2",  "ana2+"  + ts + "@mail.com", "123");
		otro    = new Estudiante("Luis2", "luis2+" + ts + "@mail.com", "456");
		em.persist(creador);
		em.persist(otro);
		em.getTransaction().commit();
	}

	@AfterAll
	void tearDown() {
		if (em.isOpen())  em.close();
		if (emf.isOpen()) emf.close();
		CursoDAO.cerrarFactory();
	}

	/* ───────── tests ───────── */

	@Test
	void testGuardarCurso() {
		Curso curso = new Curso("Curso A", "Desc A",
				Curso.TipoEstrategia.SECUENCIAL, new HashSet<>(), creador, true);

		cursoDAO.guardar(curso);

		List<Curso> resultados =
				em.createQuery("SELECT c FROM Curso c", Curso.class).getResultList();

		assertEquals(1, resultados.size());
		assertEquals("Curso A", resultados.get(0).getNombre());
	}

	@Test
	void testBuscarTodos() {
		crearCurso("Curso A");
		crearCurso("Curso B");

		List<Curso> cursos = cursoDAO.buscarTodos();
		assertEquals(2, cursos.size());
	}

	@Test
	void testBuscarCursosDelUsuario() {
		crearCurso("Curso Propio");
		Curso ajeno = crearCurso("Curso Ajeno", otro);
		inscribirEstudiante(ajeno, creador);          // Ana se inscribe en curso de Luis

		List<Curso> cursos = cursoDAO.buscarCursosDelUsuario(creador);
		assertEquals(2, cursos.size());
	}

	@Test
	void testBuscarCursosPublicosDisponibles() {
		Curso publico  = crearCurso("Curso Público",  otro, true);
		Curso privado  = crearCurso("Curso Privado",  otro, false);
		inscribirEstudiante(publico, creador);        // Ana ya inscrita en “publico”
		Curso otroPub  = crearCurso("Curso No Inscrito", otro, true);

		List<Curso> disponibles = cursoDAO.buscarCursosPublicosDisponibles(creador);

		assertEquals(1, disponibles.size());
		assertEquals(otroPub.getNombre(), disponibles.get(0).getNombre());

		// Asegurar que no se cuela el curso privado
		List<String> nombres = disponibles.stream().map(Curso::getNombre).toList();
		assertFalse(nombres.contains(privado.getNombre()));
	}

	/* ───────── helpers ───────── */

	private Curso crearCurso(String nombre) {
		return crearCurso(nombre, creador, true);
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
