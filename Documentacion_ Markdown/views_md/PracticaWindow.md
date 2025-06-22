# Clase `PracticaWindow`

## Paquete `views`


---

## Índice
-   [Dependencias]	(#Dependencias)
-   [Descripción]	(#Descripción)
-   [Atributos]		(#Atributos)
-   [Constructor]	(#Constructor)
-   [Métodos]		(#Métodos)
-   [Consideraciones]	(#Consideraciones)


---

## Dependencias

- dao.ProgresoEstudianteDAO;
- estrategias.*;
- models.*;

- javax.swing.*;
- java.awt.*;
- java.util.List;
- java.util.stream.Collectors;


---

## Descripción

`PracticaWindow` es una ventana Swing que permite a un estudiante practicar un curso usando una estrategia de aprendizaje determinada. Integra lógica de progreso y actualiza automáticamente la interfaz y los datos del estudiante.


---

## Atributos

- `curso`		: instancia de `Curso` que se está practicando.
- `estudiante`		: instancia de `Estudiante` que realiza la práctica.
- `progDAO`		: acceso a datos de progreso del estudiante.
- `progreso`		: instancia de `ProgresoEstudiante` correspondiente.
- `estrategia`		: estrategia de aprendizaje usada (secuencial, aleatoria o repetición).
- `contenedor`		: panel principal donde se cargan las flashcards.
- `barra`		: barra de progreso de la práctica.
- `lblEstado`		: etiqueta que muestra estado numérico de progreso.


---

## Constructor

public PracticaWindow(Curso cursoA, Estudiante est)

	Inicializa la práctica, carga el progreso o lo crea, selecciona la estrategia,
 	y gestiona el reinicio si el curso ya fue completado.


---

## Métodos

### `initUI()`

	Inicializa y configura la interfaz gráfica: título, tamaño, disposición y componentes.


### `mostrarSiguiente()`

	Carga y muestra la siguiente `Flashcard` mediante la estrategia activa. Si no hay más
	flashcards, se llama al método `fin()`.


### `actualizarBarra()`

	Actualiza visualmente la barra de progreso y el estado textual (aciertos / total).


### `fin()`

	Muestra un mensaje de curso completado y cierra la ventana.


### `dispose()`

	Cierra el DAO de progreso antes de cerrar la ventana completamente.


---

## Consideraciones

- Utiliza `ProgresoEstudianteDAO` para guardar el avance.
- Gestiona el flujo de estudio automáticamente mostrando nuevas flashcards tras responder.
- Si el curso fue completado previamente, ofrece reiniciarlo.
- Usa `FlashcardWindow` para mostrar la interfaz de interacción por flashcard.


---