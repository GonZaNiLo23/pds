package dao;

import models.*;
import models.Curso.TipoEstrategia;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class InscripcionDAOTest {

	private static EntityManagerFactory emf;
	private EntityManager em;

	private Estudiante estudiante;
	private Curso curso;

	private InscripcionDAO inscripcionDAO;

	@BeforeAll
	public void init() {
		emf = Persistence.createEntityManagerFactory("flashcards-jpa");
		inscripcionDAO = new InscripcionDAO();
	}

	@BeforeEach
	public void setUp() {
		em = emf.createEntityManager();
		em.getTransaction().begin();

		String emailUnico = "test+" + System.nanoTime() + "@correo.com";
		estudiante = new Estudiante("Test Estudiante", emailUnico, "1234");
		em.persist(estudiante);

		curso = new Curso("Curso Test", "Desc", TipoEstrategia.SECUENCIAL, new HashSet<>(), estudiante, true);
		em.persist(curso);

		em.getTransaction().commit();
	}

	@AfterAll
	public void tearDown() {
		em.close();
		emf.close();
		InscripcionDAO.cerrarFactory();
	}

	@Test
	public void testGuardarYBuscarInscripcion() {
		Inscripcion inscripcion = new Inscripcion(estudiante, curso, TipoEstrategia.SECUENCIAL);

		inscripcionDAO.guardar(inscripcion);

		List<Inscripcion> inscripciones = inscripcionDAO.buscarPorEstudiante(estudiante);
		assertFalse(inscripciones.isEmpty());
		assertEquals(estudiante.getId(), inscripciones.get(0).getEstudiante().getId());
		assertEquals(curso.getId(), inscripciones.get(0).getCurso().getId());
	}

	@Test
	public void testEstaInscrito() {
		Inscripcion inscripcion = new Inscripcion(estudiante, curso, TipoEstrategia.SECUENCIAL);
		inscripcionDAO.guardar(inscripcion);

		assertTrue(inscripcionDAO.estaInscrito(estudiante, curso));
	}

	@Test
	public void testEliminar() {
		Inscripcion inscripcion = new Inscripcion(estudiante, curso, TipoEstrategia.SECUENCIAL);
		inscripcionDAO.guardar(inscripcion);

		assertTrue(inscripcionDAO.estaInscrito(estudiante, curso));

		inscripcionDAO.eliminar(estudiante, curso);

		assertFalse(inscripcionDAO.estaInscrito(estudiante, curso));
	}
}
