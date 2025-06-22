# Documentación técnica: `FlashcardWindow.java`

## Paquete `views`

---

## Índice
-   [Dependencias]	(#Dependencias)
-   [Descripción]	(#Descripción)
-   [Atributos]		(#Atributos)
-   [Constructor]	(#Constructor)
-   [Métodos]		(#Métodos)
-   [Interacciones]	(#Interacciones)


---

## Dependencias

- `models.Flashcard`
- `javax.swing.*`
- `java.awt.*`
- `java.util.*`


---

## Descripción

`FlashcardWindow` es una clase abstracta que extiende `JPanel` y representa una interfaz gráfica genérica para practicar con una única flashcard. Soporta todos los tipos de flashcards definidos en `Flashcard.TipoFlashcard`, y proporciona lógica para mostrar opciones, recoger respuestas, evaluar su corrección y mostrar feedback automático antes de cargar la siguiente pregunta.

---

## Atributos principales

- Flashcard flashcard		: La flashcard que se está mostrando en esta ventana.
- JLabel preguntaLabel		: Etiqueta para mostrar el enunciado de la pregunta.
- List<JButton> opcionesButtons	: Lista de botones de opción para preguntas no abiertas.
- JLabel resultadoLabel		: Etiqueta para mostrar si la respuesta fue correcta o incorrecta.
- JTextField respuestaField	: Campo de entrada para preguntas abiertas.  
- Timer timerNext		: Temporizador para avanzar automáticamente tras 5 segundos.


---

## Constructor

public FlashcardWindow(Flashcard flashcard)

	Inicializa la interfaz gráfica para una flashcard dada. Construye los paneles de
	cabecera, centro y pie.


---

## Métodos principales

### `private void construirCabecera()`
	
	Construye la sección superior con el enunciado de la pregunta.


### `private void construirCentro()`
	
	Genera la zona central de la UI:
		- Muestra un `JTextField` si es una pregunta abierta.
		- Muestra botones por opción en los otros casos.


### `private void construirPie()`

	Añade una etiqueta para el feedback tras responder.


## Método abstracto

### `protected abstract void onRespuesta(boolean correcta)`
Este método debe ser implementado por la clase que hereda. Se invoca automáticamente 5 segundos después de responder.


## Método de test manual

Incluye un `main()` que permite probar la ventana individualmente con una flashcard de ejemplo tipo multiopción. Cierra la ventana tras mostrar el feedback.


---

## Interacciónes

### `private JButton createOptionButton(String opcion)`
Crea y estiliza un botón para una opción dada. Añade efecto hover y lógica para verificar la respuesta al hacer clic.

### `private void verificarRespuesta(String respuesta)`
Desactiva la UI, evalúa si la respuesta es correcta, actualiza visualmente y programa el callback `onRespuesta` tras 5 segundos.

### `private void pintarFeedback(boolean correcta, String respuestaDada)`
Actualiza la etiqueta de resultado con mensajes y colores según la respuesta. También colorea los botones según la corrección.


---
