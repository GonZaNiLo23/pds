## Clase `CrearFlashcardWindow`

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

import models.Flashcard;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;


---

## Descripción

Ventana gráfica para crear flashcards interactivas de distintos tipos: completar hueco, verdadero/falso, pregunta abierta o opción múltiple.


---

## Atributos

- `JComboBox<String> tipoFlashcardCombo`	: Selector del tipo de flashcard.
- `JPanel contenidoPanel`			: Contenedor que cambia dinámicamente según el tipo
						  seleccionado.
- `JTextField preguntaField`			: Campo de texto para la pregunta o afirmación.
- `JTextField respuestaCorrectaField`		: Campo de texto para la respuesta correcta.
- `List<JTextField> opcionesIncorrectasFields`	: Lista para opciones incorrectas (completar hueco).
- `List<JTextField> distractorFields`		: Lista para distractores (multiopción).
- `JRadioButton verdaderoRadio`, `falsoRadio`	: Botones para seleccionar si la afirmación es 
						  verdadera o falsa.
- `Consumer<Flashcard> onFlashcardCreada`	: Acción a ejecutar cuando se crea la flashcard.

---

## Constructores

- public CrearFlashcardWindow(Consumer<Flashcard> onFlashcardCreada)
- public CrearFlashcardWindow(int numeroActual, int total, Consumer<Flashcard> onFlashcardCreada)

	Crea e inicializa la interfaz de creación de flashcards, permitiendo un callback al crearlas.


---

## Métodos (privados)

### `void actualizarTipoFlashcard()`
Actualiza dinámicamente el contenido de la ventana según el tipo de flashcard seleccionado.

### `private void crearFlashcard()`
Lee los datos del formulario, valida el contenido, crea una instancia de `Flashcard` y la entrega al consumidor (`onFlashcardCreada`).

### Paneles por tipo:
- `mostrarPanelCompletarHueco()`
- `mostrarPanelVerdaderoFalso()`
- `mostrarPanelPreguntaAbierta()`
- `mostrarPanelMultiopcion()`

### Validaciones:
- `boolean validarCompletarHueco()`
- `boolean validarMultiopcion()`

### Utilidades:
- `void mostrarError(String msg)`

---

## Consideraciones

- La clase usa **Swing** para la interfaz gráfica.
- Es compatible con Java 11.
- Utiliza `switch` clásico para gestionar tipos de flashcard.
- Se adapta a distintos tipos mediante recarga dinámica del formulario.
- Realiza validación básica de los campos antes de crear las flashcards.


### Flashcard soportadas

1. **Completar hueco**  
2. **Verdadero/Falso**  
3. **Pregunta abierta**  
4. **Opción múltiple**


---