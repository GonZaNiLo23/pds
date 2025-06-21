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
public class ProgresoEstudianteDAOTest {

	private static EntityManagerFactory emf;
	private EntityManager em;

	private Estudiante estudiante1;
	private Estudiante estudiante2;
	private Curso curso;

	private ProgresoEstudianteDAO progresoDAO;

	@BeforeAll
	public void init() {
		emf = Persistence.createEntityManagerFactory("flashcards-jpa");
		progresoDAO = new ProgresoEstudianteDAO();
	}

	@BeforeEach
	public void setUp() {
		em = emf.createEntityManager();
		em.getTransaction().begin();

		// Crear estudiantes de prueba
		String email1 = "estudiante1+" + System.nanoTime() + "@correo.com";
		String email2 = "estudiante2+" + System.nanoTime() + "@correo.com";

		estudiante1 = new Estudiante("Estudiante Uno", email1, "1234");
		estudiante2 = new Estudiante("Estudiante Dos", email2, "5678");
		em.persist(estudiante1);
		em.persist(estudiante2);

		// Crear curso de prueba
		curso = new Curso("Curso Test", "Descripción del curso", 
				TipoEstrategia.SECUENCIAL, new HashSet<>(), estudiante1, true);
		em.persist(curso);

		em.getTransaction().commit();
	}

	@AfterAll
	public void tearDown() {
		em.close();
		emf.close();
		progresoDAO.cerrar();
	}

	@Test
	public void testGuardarYBuscarProgresoPorEstudianteYCurso() {
		// Crear progreso de prueba
		ProgresoEstudiante progreso = new ProgresoEstudiante(estudiante1, curso);
		progreso.setAciertos(8);
		progreso.setTotalRespondidas(10);

		// Guardar
		progresoDAO.guardar(progreso);

		// Buscar
		ProgresoEstudiante progresoEncontrado = 
				progresoDAO.buscarPorEstudianteYCurso(estudiante1, curso);

		assertNotNull(progresoEncontrado);
		assertEquals(estudiante1.getId(), progresoEncontrado.getEstudiante().getId());
		assertEquals(curso.getId(), progresoEncontrado.getCurso().getId());
		assertEquals(8, progresoEncontrado.getAciertos());
		assertEquals(10, progresoEncontrado.getTotalRespondidas());
	}

	@Test
	public void testBuscarPorEstudianteYCursoNoExiste() {
		// Buscar progreso que no existe
		ProgresoEstudiante progreso = 
				progresoDAO.buscarPorEstudianteYCurso(estudiante1, curso);

		assertNull(progreso);
	}

	@Test
	public void testBuscarPorEstudiante() {
		// Crear varios progresos para el mismo estudiante
		ProgresoEstudiante progreso1 = new ProgresoEstudiante(estudiante1, curso);
		progreso1.setAciertos(5);
		progreso1.setTotalRespondidas(10);

		// Crear otro curso para tener múltiples progresos
		Curso curso2 = new Curso("Curso Test 2", "Segundo curso", 
				TipoEstrategia.ALEATORIO, new HashSet<>(), estudiante1, true);

		em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(curso2);
		em.getTransaction().commit();
		em.close();

		ProgresoEstudiante progreso2 = new ProgresoEstudiante(estudiante1, curso2);
		progreso2.setAciertos(7);
		progreso2.setTotalRespondidas(12);

		progresoDAO.guardar(progreso1);
		progresoDAO.guardar(progreso2);

		// Buscar todos los progresos del estudiante
		List<ProgresoEstudiante> progresos = 
				progresoDAO.buscarPorEstudiante(estudiante1);

		assertEquals(2, progresos.size());
		assertTrue(progresos.stream()
				.anyMatch(p -> p.getCurso().getId().equals(curso.getId())));
		assertTrue(progresos.stream()
				.anyMatch(p -> p.getCurso().getId().equals(curso2.getId())));
	}

	@Test
	public void testRankingPorCurso() {
		// Crear progresos para diferentes estudiantes en el mismo curso
		ProgresoEstudiante progreso1 = new ProgresoEstudiante(estudiante1, curso);
		progreso1.setAciertos(8);  // 80% acierto
		progreso1.setTotalRespondidas(10);

		ProgresoEstudiante progreso2 = new ProgresoEstudiante(estudiante2, curso);
		progreso2.setAciertos(6);  // 60% acierto
		progreso2.setTotalRespondidas(10);

		progresoDAO.guardar(progreso1);
		progresoDAO.guardar(progreso2);

		// Obtener ranking
		List<Object[]> ranking = progresoDAO.rankingPorCurso(curso);

		assertEquals(2, ranking.size());

		// Verificar que está ordenado por porcentaje descendente
		Object[] primero = ranking.get(0);
		Object[] segundo = ranking.get(1);

		Estudiante primerEstudiante = (Estudiante) primero[0];
		Double primerPorcentaje = (Double) primero[1];
		Estudiante segundoEstudiante = (Estudiante) segundo[0];
		Double segundoPorcentaje = (Double) segundo[1];

		assertEquals(estudiante1.getId(), primerEstudiante.getId());
		assertEquals(80.0, primerPorcentaje, 0.01);
		assertEquals(estudiante2.getId(), segundoEstudiante.getId());
		assertEquals(60.0, segundoPorcentaje, 0.01);
	}

	@Test
	public void testRankingPorCursoSinRespuestas() {
		// Crear progreso sin respuestas (totalRespondidas = 0)
		ProgresoEstudiante progreso = new ProgresoEstudiante(estudiante1, curso);
		progreso.setAciertos(0);
		progreso.setTotalRespondidas(0);

		progresoDAO.guardar(progreso);

		// El ranking no debe incluir estudiantes sin respuestas
		List<Object[]> ranking = progresoDAO.rankingPorCurso(curso);

		assertTrue(ranking.isEmpty());
	}

	@Test
	public void testActualizarProgreso() {
		// Crear y guardar progreso inicial
		ProgresoEstudiante progreso = new ProgresoEstudiante(estudiante1, curso);
		progreso.setAciertos(5);
		progreso.setTotalRespondidas(8);

		progresoDAO.guardar(progreso);

		// Buscar y modificar
		ProgresoEstudiante progresoEncontrado = 
				progresoDAO.buscarPorEstudianteYCurso(estudiante1, curso);

		progresoEncontrado.setAciertos(10);
		progresoEncontrado.setTotalRespondidas(15);

		// Actualizar
		progresoDAO.guardar(progresoEncontrado);

		// Verificar actualización
		ProgresoEstudiante progresoActualizado = 
				progresoDAO.buscarPorEstudianteYCurso(estudiante1, curso);

		assertEquals(10, progresoActualizado.getAciertos());
		assertEquals(15, progresoActualizado.getTotalRespondidas());
	}
}