
# Clase `EstrategiaSecuencial`

##Paquete: `estrategias`

## Índice

-   [Dependencias]	(#Dependencias)
-   [Descripción]	(#descripción)
-   [Atributos]		(#atributos)
-   [Métodos]		(#métodos)


------------------------------------------------------------------------

## Dependencias

- `models.Flashcard`
- `java.util.List`
- `java.util.ArrayList`


-------------------------------------------------------------------------

## Descripción

`EstrategiaSecuencial` es una implementación de la interfaz `EstrategiaAprendizaje` que presenta las flashcards de forma secuencial, en el orden en que se proporcionan. Es una de las estrategias de aprendizaje disponibles en el sistema.

---

## Atributos

  ------------------------------------------------------------------------------------
  Atributo            Tipo                 Descripción
  ------------------- -------------------- -------------------------------------------
  `flashcards`        `List<Flashcard>`    Lista de *flashcards* a presentar.
                                           
  `indiceActual`      `int`   		   Índice de la *flashcard* actual en la lista.
                                           
  `totalRespondidas`  `int`                Contador de *flashcards* respondidas.

  ------------------------------------------------------------------------------------


## Métodos

### `void inicializar(List<Flashcard> flashcards)`

Inicializa la estrategia con una copia de la lista de *flashcards*. Resetea el índice actual y el contador de respondidas.


### `Flashcard siguienteFlashcard()`

Devuelve la *flashcard* actual si hay más por mostrar; en caso contrario, devuelve `null`.


### `void registrarRespuesta(Flashcard flashcard, boolean correcta)`

Registra que se ha respondido una *flashcard*, avanzando el índice y actualizando el contador.


### `boolean hayMasFlashcards()`

Indica si quedan *flashcards* por mostrar.


### `double obtenerProgreso()`

Devuelve el progreso actual como un valor entre `0.0` y `1.0` según la proporción de *flashcards* respondidas.


---

## Notas

- Esta estrategia muestra las tarjetas en orden estricto sin variación.
- No se considera si la respuesta es correcta o no: simplemente avanza.

---
