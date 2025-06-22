
# Clase `BuscarCursosWindow`

## Paquete `views`


## Índice
-   [Dependencias]	(#Dependencias)
-   [Descripción]	(#Descripción)
-   [Atributos]		(#Atributos)
-   [Constructor]	(#Constructor)
-   [Métodos]		(#Métodos)
-   [Consideraciones]	(#Consideraciones)


--

## Dependencias

- dao.CursoDAO;
- dao.InscripcionDAO;
- models.Curso;
- models.Curso.TipoEstrategia;
- models.Estudiante;
- models.Inscripcion;
- javax.swing.*;
- java.awt.*;
- java.util.List;

---

## Descripción general

La clase `BuscarCursosWindow` representa una ventana Swing (inscripción.png) que permite a un estudiante visualizar los cursos públicos disponibles e inscribirse en uno de ellos. Es parte de la interfaz gráfica de usuario (GUI) de la aplicación.

---

## Atributos

| Atributo               | Tipo                       | Descripción |
|------------------------|----------------------------|-------------|
| `estudianteActual`     | `Estudiante`               | Estudiante que ha iniciado sesión. |
| `ventanaPadre`         | `ListadoCursosWindow`      | Ventana principal que invoca esta ventana. |
| `cursoDAO`             | `CursoDAO`                 | DAO para la gestión de cursos. |
| `inscripcionDAO`       | `InscripcionDAO`           | DAO para registrar inscripciones. |
| `model`                | `DefaultListModel<Curso>`  | Modelo de datos para la lista de cursos. |
| `cursosList`           | `JList<Curso>`             | Lista visual de cursos públicos. |

---

## Constructores

### `BuscarCursosWindow(Estudiante estudiante, ListadoCursosWindow padre)`

	Inicializa la ventana y carga la lista de cursos públicos disponibles para el estudiante 	proporcionado.


---

## Métodos privados

### `initUI()`

	Configura la interfaz gráfica: tamaño, disposición de componentes, estilo y
	listeners de botones.


### `cargarCursosDisponibles()`

	Consulta los cursos públicos a los que el estudiante aún no está inscrito y
	los muestra en la lista.


### `inscribirse()`

	Gestiona el proceso de inscripción del estudiante en el curso seleccionado.
	Crea una nueva `Inscripcion`, la guarda en la base de datos y actualiza la
	interfaz principal.


---

## Comportamiento de la interfaz

- **Botón "Inscribirse"**: 
	Inscribe al estudiante en el curso seleccionado.

- **Botón "Cerrar"**: 
	Cierra la ventana sin realizar ninguna acción adicional.

- **Lista de cursos**: 
	Muestra cada curso público con su nombre, cantidad de flashcards y nombre del creador.


---

## Detalles de implementación

- La clase hereda de `JFrame` y está serializada.
- Utiliza `CursoDAO` para obtener cursos públicos disponibles.
- Usa `InscripcionDAO` para registrar una inscripción.

---

## Consideraciones

- Si no hay cursos públicos disponibles, se muestra un mensaje informativo.
- Si el usuario intenta inscribirse sin seleccionar un curso, se muestra una advertencia.
- Después de una inscripción exitosa, la lista de la ventana principal se actualiza y la
  ventana se cierra.
