package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ProgresoEstudianteTest {

    Estudiante estudiante;
    Curso curso;
    Flashcard f1, f2;
    ProgresoEstudiante progreso;

    @BeforeEach
    void setup() {
        estudiante = new Estudiante("Ana", "ana@mail.com", "1234");
        curso = new Curso("Curso prueba", "Descripción", Curso.TipoEstrategia.SECUENCIAL, Set.of(), estudiante, true);

        // Crear dos flashcards para el curso
        f1 = Flashcard.crearPreguntaAbierta("Pregunta 1", "Respuesta1");
        f2 = Flashcard.crearPreguntaAbierta("Pregunta 2", "Respuesta2");

        // Agregar flashcards al curso
        curso.addFlashcard(f1);
        curso.addFlashcard(f2);

        progreso = new ProgresoEstudiante(estudiante, curso);

        // Como flashcards no tienen id asignado, asignamos manualmente para test:
        // (En un test real JPA las IDs se generan al persistir)
        // Aquí asignamos para que no sean null:
        setId(f1, 1L);
        setId(f2, 2L);
    }

    private void setId(Flashcard f, Long id) {
        try {
            java.lang.reflect.Field field = Flashcard.class.getDeclaredField("id");
            field.setAccessible(true);
            field.set(f, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void registrarRespuesta_actualizaCamposCorrectamente() {
        progreso.registrarRespuesta(f1, true);
        progreso.registrarRespuesta(f2, false);

        assertEquals(2, progreso.getTotalRespondidas());
        assertEquals(1, progreso.getAciertos());

        Set<Long> completadas = progreso.getFlashcardsCompletadas();
        assertTrue(completadas.contains(f1.getId()));
        assertTrue(completadas.contains(f2.getId()));
    }

    @Test
    void reset_limpiaCamposYActualizaFecha() throws InterruptedException {
        progreso.registrarRespuesta(f1, true);
        Thread.sleep(10); // para asegurar que cambia la fecha

        progreso.reset();

        assertEquals(0, progreso.getTotalRespondidas());
        assertEquals(0, progreso.getAciertos());
        assertTrue(progreso.getFlashcardsCompletadas().isEmpty());
    }

    @Test
    void obtenerPorcentajeProgreso_calculaCorrectamente() {
        // Antes de responder nada, porcentaje es 0
        assertEquals(0.0, progreso.obtenerPorcentajeProgreso());

        // Registrar una flashcard completada
        progreso.registrarRespuesta(f1, true);

        double esperado = 1.0 / curso.getFlashcards().size();
        assertEquals(esperado, progreso.obtenerPorcentajeProgreso());
    }
}
