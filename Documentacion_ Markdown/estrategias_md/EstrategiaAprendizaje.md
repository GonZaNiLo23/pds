# Clase`EstrategiaAprendizaje`

## Paquete `estrategias`


## Índice

-   [Dependencias]	(#Dependencias)
-   [Descripción]	(#descripción)
-   [Métodos]		(#métodos)


------------------------------------------------------------------------

## Dependencias

- `java.util.List`
- `models.Flashcard`


------------------------------------------------------------------------

## Descripción

`EstrategiaAprendizaje` es una interfaz que define el comportamiento que
deben seguir las estrategias de aprendizaje para la presentación y
evaluación de flashcards. Las implementaciones pueden aplicar diferentes
lógicas (secuencial, aleatoria, repetición) para gestionar el flujo de estudio.


------------------------------------------------------------------------

## Métodos

### `void inicializar(List<Flashcard> flashcards)`

Inicializa la estrategia con una lista de flashcards.

- Parámetros:
	-`flashcards`: Lista de tarjetas que serán utilizadas en la
        estrategia.

------------------------------------------------------------------------

### `Flashcard siguienteFlashcard()`

Devuelve la siguiente flashcard a presentar según la estrategia
implementada.


------------------------------------------------------------------------

### `void registrarRespuesta(Flashcard flashcard, boolean correcta)`

Registra el resultado de la respuesta del usuario respecto a una
flashcard.

- Parámetros:
    -`flashcard`: La tarjeta que se respondió.
    -`correcta`: Indica si la respuesta fue correcta (`true`) o no
      (`false`).

------------------------------------------------------------------------

### `boolean hayMasFlashcards()`

Indica si aún quedan flashcards por mostrar.

-   **Retorno**: `true` si hay más tarjetas, `false` en caso contrario.

------------------------------------------------------------------------

### `double obtenerProgreso()`

Devuelve el progreso actual del usuario respecto al total de tarjetas.

-   **Retorno**: Un valor `double` entre `0.0` y `1.0`.

------------------------------------------------------------------------

## Uso típico

Esta interfaz se implementa en estrategias concretas como:

-   Secuencial
-   Repetición espaciada
-   Aleatoria

Cada implementación define su lógica específica para gestionar el orden,
la repetición y la evaluación del aprendizaje.
