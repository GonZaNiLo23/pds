package dao;

import models.Curso;
import models.Estudiante;
import models.Inscripcion;
import org.junit.jupiter.api.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CursoDAOTest {

	private EntityManagerFactory emf;
	private EntityManager em;
	private CursoDAO cursoDAO;

	private Estudiante creador;
	private Estudiante otro;

	@BeforeAll
	void init() {
		emf = Persistence.createEntityManagerFactory("flashcards-jpa");
		em = emf.createEntityManager();
		cursoDAO = new CursoDAO();
	}

	@BeforeEach
	void setUp() {
		em.getTransaction().begin();
		em.createQuery("DELETE FROM ProgresoEstudiante").executeUpdate();
		em.createQuery("DELETE FROM Inscripcion").executeUpdate();
		em.createQuery("DELETE FROM Curso").executeUpdate();
		em.createQuery("DELETE FROM Estudiante").executeUpdate();

		String timestamp = String.valueOf(System.nanoTime());
		// Fix the parameter order - assuming constructor is (nombre, email, password)
		creador = new Estudiante("Ana2", "ana2+" + timestamp + "@email.com", "123");
		otro = new Estudiante("Luis2", "luis2+" + timestamp + "@email.com", "456");
		em.persist(creador);
		em.persist(otro);
		em.getTransaction().commit();
	}

	@AfterAll
	void tearDown() {
		em.close();
		emf.close();
		CursoDAO.cerrarFactory();
	}

	@Test
	void testGuardarCurso() {
		Curso curso = new Curso("Curso A", "Desc A", Curso.TipoEstrategia.SECUENCIAL, new HashSet<>(), creador, true);

		cursoDAO.guardar(curso);

		List<Curso> resultados = em.createQuery("SELECT c FROM Curso c", Curso.class).getResultList();
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
		inscribirEstudiante(ajeno, creador); // ana se inscribe en curso de luis 

		List<Curso> cursos = cursoDAO.buscarCursosDelUsuario(creador);
		assertEquals(2, cursos.size());
	}

	@Test
	void testBuscarCursosPublicosDisponibles() {
		Curso publico = crearCurso("Curso Público", otro, true);
		Curso privado = crearCurso("Curso Privado", otro, false);
		inscribirEstudiante(publico, creador);

		// Crear otro curso público no inscrito
		Curso otroPublico = crearCurso("Curso No Inscrito", otro, true);

		List<Curso> disponibles = cursoDAO.buscarCursosPublicosDisponibles(creador);
		assertEquals(1, disponibles.size());
		assertEquals(otroPublico.getNombre(), disponibles.get(0).getNombre());

		// Verificar que NO contiene cursos no deseados
		List<String> nombresDisponibles = disponibles.stream()
				.map(Curso::getNombre)
				.toList();

		assertFalse(nombresDisponibles.contains(privado.getNombre()), "No debe incluir cursos privados");
	}

	/* ───── helpers ───────────────────── */

	private Curso crearCurso(String nombre) {
		return crearCurso(nombre, creador, true);
	}

	private Curso crearCurso(String nombre, Estudiante autor) {
		return crearCurso(nombre, autor, true);
	}

	private Curso crearCurso(String nombre, Estudiante autor, boolean esPublico) {
		Curso c = new Curso(nombre, "Descripción de " + nombre, Curso.TipoEstrategia.SECUENCIAL, new HashSet<>(), autor, esPublico);
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();
		return c;
	}

	private void inscribirEstudiante(Curso curso, Estudiante estudiante) {
		em.getTransaction().begin();
		Inscripcion insc = new Inscripcion(estudiante, curso, Curso.TipoEstrategia.SECUENCIAL);
		em.persist(insc);
		em.getTransaction().commit();
	}
}
