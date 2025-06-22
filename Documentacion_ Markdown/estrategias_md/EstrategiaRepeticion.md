# Clase `EstrategiaRepeticion`

##Paquete: `estrategias`

## Índice

-   [Dependencias]	(#Dependencias)
-   [Descripción]	(#descripción)
-   [Atributos]		(#atributos)
-   [Métodos]		(#métodos)

------------------------------------------------------------------------

## Dependencias

- `java.util.ArrayDeque`
- `models.Flashcard`
- `java.util.ArrayList`
- `java.util.Deque`
- `java.util.List`

------------------------------------------------------------------------

## Descripcion

`EstrategiaRepeticion` es una implementación de la interfaz `EstrategiaAprendizaje`.
Esta estrategia consiste en realizar una ronda inicial secuencial con todas las 
flashcards y, posteriormente, repasar únicamente aquellas en las que se ha fallado,
repitiendo hasta que se respondan correctamente.


------------------------------------------------------------------------

## Atributos

  ------------------------------------------------------------------------------------
  Atributo            Tipo                 Descripción
  ------------------- -------------------- -------------------------------------------
  `cola`              `Deque<Flashcard>`   Cola de trabajo con las *flashcards* de la
                                           ronda actual.

  `pendientes`        `List<Flashcard>`    Lista de *flashcards* falladas en la ronda
                                           actual.

  `modoRepeticion`    `boolean`            Indica si se está en modo de repetición.

  `totalFlashcards`   `int`                Número total de *flashcards* al inicio.

  `correctas`         `int`                Número de respuestas correctas registradas.
  ------------------------------------------------------------------------------------

------------------------------------------------------------------------

## Métodos públicos

### `void inicializar(List<Flashcard> lista)`

Inicializa la estrategia con una lista de *flashcards* en orden secuencial. 
Limpia la cola y las falladas anteriores, y Establece `modoRepeticion` en `false`.


### `boolean hayMasFlashcards()`

Indica si quedan flashcards por presentar al usuario. Devuelve `true` si quedan
elementos en la cola o hay fallos por repasar.


### `Flashcard siguienteFlashcard()`

Devuelve la siguiente *flashcard* a presentar. Si la cola está vacía pero hay
fallos pendientes, se activa el `modoRepeticion`.


### `void registrarRespuesta(Flashcard f, boolean correcta)`

Registra si la respuesta del usuario ha sido correcta.
-   Si es incorrecta, la *flashcard* se añade a la lista de fallos.
-   Si es correcta, incrementa el contador de aciertos.


### `double obtenerProgreso()`

Calcula el progreso del usuario como proporción de respuestas correctas
respecto al total.

------------------------------------------------------------------------

## Métodos adicionales para la interfaz de usuario

### `boolean estaEnModoRepeticion()`

Devuelve `true` si la estrategia está actualmente en la fase de repaso
de fallos.


### `int getCantidadPendientes()`

Devuelve el número de *flashcards* aún pendientes de responder
correctamente.
