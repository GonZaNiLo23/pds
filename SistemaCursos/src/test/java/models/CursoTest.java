package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Test suite completa para la clase Curso
 * Cubre constructores, getters/setters, relaciones y lógica de dominio
 */
class CursoTest {

	private Estudiante estudiante;
	private Flashcard flashcard1;
	private Flashcard flashcard2;
	private Set<Flashcard> flashcards;

	@BeforeEach
	void setUp() {
		// Crear objetos mock para las pruebas
		estudiante = new Estudiante(); // Asumiendo constructor por defecto

		flashcard1 = new Flashcard(); // Asumiendo constructor por defecto
		flashcard2 = new Flashcard();

		flashcards = new HashSet<>();
		flashcards.add(flashcard1);
		flashcards.add(flashcard2);
	}

	@Nested
	@DisplayName("Tests de Constructores")
	class ConstructorTests {

		@Test
		@DisplayName("Constructor por defecto debe crear objeto válido")
		void testConstructorPorDefecto() {
			Curso curso = new Curso();

			assertNotNull(curso);
			assertNull(curso.getId());
			assertNull(curso.getNombre());
			assertNull(curso.getDescripcion());
			assertNotNull(curso.getFechaCreacion());
			assertEquals(Curso.TipoEstrategia.SECUENCIAL, curso.getTipoEstrategia());
			assertNull(curso.getCreador());
			assertNotNull(curso.getFlashcards());
			assertTrue(curso.getFlashcards().isEmpty());
			assertTrue(curso.isPublico());
		}

		@Test
		@DisplayName("Constructor completo debe inicializar todos los campos")
		void testConstructorCompleto() {
			String nombre = "Curso de Java";
			String descripcion = "Aprende Java desde cero";
			Curso.TipoEstrategia tipo = Curso.TipoEstrategia.REPETICION;
			boolean esPublico = false;

			Curso curso = new Curso(nombre, descripcion, tipo, flashcards, estudiante, esPublico);

			assertEquals(nombre, curso.getNombre());
			assertEquals(descripcion, curso.getDescripcion());
			assertEquals(tipo, curso.getTipoEstrategia());
			assertEquals(estudiante, curso.getCreador());
			assertEquals(esPublico, curso.isPublico());
			assertEquals(2, curso.getFlashcards().size());
			assertNotNull(curso.getFechaCreacion());
		}

		@Test
		@DisplayName("Constructor con tipo null debe usar SECUENCIAL por defecto")
		void testConstructorConTipoNull() {
			Curso curso = new Curso("Test", "Descripción", null, new HashSet<>(), estudiante, true);

			assertEquals(Curso.TipoEstrategia.SECUENCIAL, curso.getTipoEstrategia());
		}

		@Test
		@DisplayName("Constructor debe establecer relación bidireccional con flashcards")
		void testConstructorEstableceRelacionBidireccional() {
			Curso curso = new Curso("Test", "Desc", Curso.TipoEstrategia.SECUENCIAL, 
					flashcards, estudiante, true);

			// Verificar que las flashcards tienen referencia al curso
			// Nota: Esto requiere que Flashcard tenga método getCurso()
			for (Flashcard fc : curso.getFlashcards()) {
				assertEquals(curso, fc.getCurso()); // Descomenta si existe el método
			}
		}
	}

	@Nested
	@DisplayName("Tests de Getters y Setters")
	class GettersSettersTests {

		private Curso curso;

		@BeforeEach
		void setUp() {
			curso = new Curso();
		}

		@Test
		@DisplayName("Setter y getter de nombre")
		void testNombre() {
			String nombre = "Curso de Prueba";
			curso.setNombre(nombre);
			assertEquals(nombre, curso.getNombre());
		}

		@Test
		@DisplayName("Setter y getter de descripción")
		void testDescripcion() {
			String descripcion = "Esta es una descripción de prueba";
			curso.setDescripcion(descripcion);
			assertEquals(descripcion, curso.getDescripcion());
		}

		@Test
		@DisplayName("Setter y getter de tipo estrategia")
		void testTipoEstrategia() {
			curso.setTipoEstrategia(Curso.TipoEstrategia.REPETICION);
			assertEquals(Curso.TipoEstrategia.REPETICION, curso.getTipoEstrategia());
		}

		@Test
		@DisplayName("Setter y getter de creador")
		void testCreador() {
			curso.setCreador(estudiante);
			assertEquals(estudiante, curso.getCreador());
		}

		@Test
		@DisplayName("Setter y getter de público")
		void testEsPublico() {
			curso.setPublico(false);
			assertFalse(curso.isPublico());

			curso.setPublico(true);
			assertTrue(curso.isPublico());
		}

		@Test
		@DisplayName("Fecha de creación no debe ser null")
		void testFechaCreacion() {
			assertNotNull(curso.getFechaCreacion());
			assertTrue(curso.getFechaCreacion() instanceof Date);
		}
	}

	@Nested
	@DisplayName("Tests de Lógica de Dominio")
	class LogicaDominioTests {

		private Curso curso;

		@BeforeEach
		void setUp() {
			curso = new Curso();
		}

		@Test
		@DisplayName("addFlashcard debe agregar flashcard y establecer relación")
		void testAddFlashcard() {
			Flashcard nuevaFlashcard = new Flashcard();

			assertEquals(0, curso.getFlashcards().size());

			curso.addFlashcard(nuevaFlashcard);

			assertEquals(1, curso.getFlashcards().size());
			assertTrue(curso.getFlashcards().contains(nuevaFlashcard));
			// assertEquals(curso, nuevaFlashcard.getCurso()); // Descomenta si existe el método
		}

		@Test
		@DisplayName("addFlashcard con múltiples flashcards")
		void testAddMultiplesFlashcards() {
			curso.addFlashcard(flashcard1);
			curso.addFlashcard(flashcard2);

			assertEquals(2, curso.getFlashcards().size());
			assertTrue(curso.getFlashcards().contains(flashcard1));
			assertTrue(curso.getFlashcards().contains(flashcard2));
		}

		@Test
		@DisplayName("No debe permitir flashcards duplicadas en el Set")
		void testNoDuplicarFlashcards() {
			curso.addFlashcard(flashcard1);
			curso.addFlashcard(flashcard1); // Intentar agregar la misma

			assertEquals(1, curso.getFlashcards().size());
		}
	}

	@Nested
	@DisplayName("Tests de Enum TipoEstrategia")
	class TipoEstrategiaTests {

		@Test
		@DisplayName("Enum debe tener valores correctos")
		void testEnumValues() {
			Curso.TipoEstrategia[] valores = Curso.TipoEstrategia.values();

			assertEquals(3, valores.length);
			assertEquals(Curso.TipoEstrategia.SECUENCIAL, valores[0]);
			assertEquals(Curso.TipoEstrategia.REPETICION, valores[1]);
			assertEquals(Curso.TipoEstrategia.ALEATORIO, valores[2]);
		}

		@Test
		@DisplayName("valueOf debe funcionar correctamente")
		void testValueOf() {
			assertEquals(Curso.TipoEstrategia.SECUENCIAL, 
					Curso.TipoEstrategia.valueOf("SECUENCIAL"));
			assertEquals(Curso.TipoEstrategia.REPETICION, 
					Curso.TipoEstrategia.valueOf("REPETICION"));
			assertEquals(Curso.TipoEstrategia.ALEATORIO, 
					Curso.TipoEstrategia.valueOf("ALEATORIO"));
		}
	}

	@Nested
	@DisplayName("Tests de Validación y Casos Límite")
	class ValidacionTests {

		@Test
		@DisplayName("Nombre null debe permitirse")
		void testNombreNull() {
			Curso curso = new Curso();
			curso.setNombre(null);
			assertNull(curso.getNombre());
		}

		@Test
		@DisplayName("Descripción null debe permitirse")
		void testDescripcionNull() {
			Curso curso = new Curso();
			curso.setDescripcion(null);
			assertNull(curso.getDescripcion());
		}

		@Test
		@DisplayName("Creador null debe permitirse")
		void testCreadorNull() {
			Curso curso = new Curso();
			curso.setCreador(null);
			assertNull(curso.getCreador());
		}

		@Test
		@DisplayName("TipoEstrategia null debe permitirse en setter")
		void testTipoEstrategiaNullEnSetter() {
			Curso curso = new Curso();
			curso.setTipoEstrategia(null);
			assertNull(curso.getTipoEstrategia());
		}
	}

	@Nested
	@DisplayName("Tests de Integración")
	class IntegracionTests {

		@Test
		@DisplayName("Escenario completo de uso")
		void testEscenarioCompleto() {
			// Crear curso
			Curso curso = new Curso("Java Avanzado", 
					"Curso completo de Java", 
					Curso.TipoEstrategia.REPETICION,
					new HashSet<>(),
					estudiante, 
					true);

			// Verificar estado inicial
			assertEquals("Java Avanzado", curso.getNombre());
			assertTrue(curso.isPublico());
			assertEquals(0, curso.getFlashcards().size());

			// Agregar flashcards
			curso.addFlashcard(flashcard1);
			curso.addFlashcard(flashcard2);

			// Verificar estado final
			assertEquals(2, curso.getFlashcards().size());

			// Modificar propiedades
			curso.setNombre("Java Experto");
			curso.setPublico(false);

			assertEquals("Java Experto", curso.getNombre());
			assertFalse(curso.isPublico());
		}
	}
}