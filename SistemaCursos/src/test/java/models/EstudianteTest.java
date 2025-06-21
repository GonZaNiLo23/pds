package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EstudianteTest {

    @Test
    void constructorYGetters_funcionanCorrectamente() {
        Estudiante est = new Estudiante("Juan Pérez", "juan@example.com", "secreto123");

        assertEquals("Juan Pérez", est.getNombre());
        assertEquals("juan@example.com", est.getEmail());
        assertEquals("secreto123", est.getPassword());
    }

    @Test
    void setters_modificanDatosCorrectamente() {
        Estudiante est = new Estudiante("A", "a@a.com", "123");
        est.setNombre("Nuevo Nombre");
        est.setEmail("nuevo@email.com");
        est.setPassword("nuevaClave");

        assertEquals("Nuevo Nombre", est.getNombre());
        assertEquals("nuevo@email.com", est.getEmail());
        assertEquals("nuevaClave", est.getPassword());
    }

    @Test
    void equals_y_hashCode_comparanPorId() {
        Estudiante e1 = new Estudiante("A", "a@a.com", "123");
        Estudiante e2 = new Estudiante("B", "b@b.com", "456");

        // Simulamos IDs como si vinieran de la BD
        setIdForTest(e1, 1L);
        setIdForTest(e2, 1L);

        assertEquals(e1, e2);
        assertEquals(e1.hashCode(), e2.hashCode());
    }

    @Test
    void toString_devuelveNombre() {
        Estudiante e = new Estudiante("Lucía", "lucia@dominio.com", "clave");
        assertEquals("Lucía", e.toString());
    }

    @Test
    private void setIdForTest(Estudiante e, Long id) {
        try {
            var field = Estudiante.class.getDeclaredField("id");
            field.setAccessible(true);
            field.set(e, id);
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo modificar el id para test", ex);
        }
    }
    
    @Test
    void setIdForTest_lanzaExcepcionSiElCampoNoExiste() {
        Estudiante e = new Estudiante("X", "x@x.com", "xxx");

        Exception ex = assertThrows(RuntimeException.class, () -> {
            try {
                var field = Estudiante.class.getDeclaredField("noExiste");
                field.setAccessible(true);
                field.set(e, 123L);
            } catch (Exception inner) {
                throw new RuntimeException("Error al acceder al campo", inner);
            }
        });

        assertTrue(ex.getMessage().contains("Error al acceder"));
    }
}
