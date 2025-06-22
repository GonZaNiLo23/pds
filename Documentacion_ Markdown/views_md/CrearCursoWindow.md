# Documentación de la clase `CrearCursoWindow`

## Paquete `views`  

---

## Índice
-   [Dependencias]	(#Dependencias)
-   [Descripción]	(#Descripción)
-   [Atributos]		(#Atributos)
-   [Constructor]	(#Constructor)
-   [Métodos]		(#Métodos)
-   [Diálogos]		(#Diálogos)
-  [Elementos UI]	(#Elementos UI)
-   [Consideraciones]	(#Consideraciones)


---

## Dependencias

- `models.Curso`
- `models.Flashcard`
- `models.Estudiante`
- `models.Curso.TipoEstrategia`
- `dao.CursoDAO`
- `views.CrearFlashcardWindow`


---

##Descripción
 
Ventana gráfica para crear o editar un curso (crear-curso.png). Permite ingresar nombre, descripción, visibilidad, estrategia de aprendizaje y flashcards asociadas. Utiliza Swing como interfaz gráfica y persiste los datos mediante `CursoDAO`.

---

## Atributos

- `CursoDAO cursoDAO`: DAO para acceder a la base de datos de cursos.
- `Curso cursoExist`: Curso existente a editar. Si es `null`, se está creando un nuevo curso.
- `Estudiante creador`: Usuario que crea o edita el curso.
- `JTextField nombreField`: Campo de entrada para el nombre del curso.
- `JTextArea descripcionArea`: Área de texto para la descripción del curso.
- `JComboBox<TipoEstrategia> estrategiaCombo`: Combo para elegir la estrategia.
- `JCheckBox publicoCheckBox`: Checkbox para marcar el curso como público o privado.
- `DefaultListModel<Flashcard> modelFlash`: Modelo para la lista de flashcards.

---

## Constructor

public CrearCursoWindow(Curso curso, Estudiante creador)

Inicializa la interfaz y carga datos si se pasa un curso existente.

---

## Métodos privados

### `initUI()`
Construye todos los elementos de la interfaz gráfica y configura el layout.

### `cargarCurso()`
Carga los datos del curso existente (nombre, descripción, estrategia, visibilidad y flashcards) en los campos del formulario.

### `guardarCurso()`
Valida el formulario, actualiza o crea el objeto `Curso`, guarda los datos con `CursoDAO` y muestra mensajes al usuario.

---

## Diálogos auxiliares

- `mostrarInfo(String m)`: Muestra un mensaje de información.
- `mostrarError(String m)`: Muestra un mensaje de error.

---

## Elementos destacados de la UI

- **Lista de flashcards**: Muestra las preguntas en una lista con botón para añadir (`CrearFlashcardWindow`) o eliminar.
- **Combo de estrategias**: Basado en el enum `TipoEstrategia`.
- **Validaciones**: Verifica que haya nombre y al menos una flashcard antes de guardar.

---

## Consideraciones

- El curso solo se guarda si pasa la validación del formulario.
- Si se edita un curso, se actualizan todos sus datos y se reemplazan las flashcards.
- La clase usa `GridBagLayout` y `BorderLayout` para estructurar la interfaz.

