package models;

import models.Curso.TipoEstrategia;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

class InscripcionTest {

    Estudiante estudiante;
    Curso curso;
    Inscripcion inscripcion;

    @BeforeEach
    void setup() {
        estudiante = new Estudiante("Juan", "juan@mail.com", "pwd123");
        curso = new Curso("Java Básico", "Curso de Java", TipoEstrategia.SECUENCIAL, Set.of(), estudiante, true);
        inscripcion = new Inscripcion(estudiante, curso, TipoEstrategia.SECUENCIAL);
    }

    @Test
    void testCreacionInscripcion() {
        assertNotNull(inscripcion.getFechaInicio(), "La fecha de inicio no debe ser nula");
        assertEquals("0%", inscripcion.getProgreso(), "El progreso inicial debe ser '0%'");
        assertEquals(TipoEstrategia.SECUENCIAL, inscripcion.getEstrategiaSeleccionada());
        assertEquals(estudiante, inscripcion.getEstudiante());
        assertEquals(curso, inscripcion.getCurso());
    }

    @Test
    void testActualizarProgreso() {
        inscripcion.actualizarProgreso("50%");
        assertEquals("50%", inscripcion.getProgreso());
    }
}
