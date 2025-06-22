# Clase `ProgresoEstudiante`

## Paquete `models`

## Índice

-   [Descripción]	(#descripción)
-   [Atributos]		(#atributos)
-   [Constructores]	(#constructores)
-   [Métodos]		(#Metodos)
-   [Persistencia]	(#Persistencia)

------------------------------------------------------------------------

## Descripción

La clase `ProgresoEstudiante` representa el progreso individual de un
estudiante en un curso determinado. Permite registrar respuestas,
calcular estadísticas de rendimiento, y almacenar qué flashcards han
sido completadas por el usuario. Esta clase se usa en otras
funcionalidades como rankings o guardado de estado del curso.

Se trata de una **entidad persistente (JPA)** que se mapea a la tabla
`progresos` de la base de datos.

------------------------------------------------------------------------

## Atributos

  --------------------------------------------------------------------------------------
  Atributo                  Tipo           Descripción
  ------------------------- -------------- ---------------------------------------------
  `id`                      `Long`         Identificador único del progreso.

  `estudiante`              `Estudiante`   Estudiante propietario del progreso.

  `curso`                   `Curso`        Curso al que pertenece el progreso.

  `totalRespondidas`        `int`          Total de flashcards respondidas.

  `aciertos`                `int`          Total de respuestas correctas.

  `flashcardsCompletadas`   `Set<Long>`    Identificadores de flashcards que el
                                           estudiante ha completado.

  `ultimaActividad`         `Date`         Fecha y hora de la última acción registrada
                                           en el curso.
  --------------------------------------------------------------------------------------

> `flashcardsCompletadas` se almacena como una colección embebida en la
> tabla `progreso_flashcards`.

------------------------------------------------------------------------

## Constructores

### `ProgresoEstudiante()`

Constructor protegido sin argumentos. Requerido por JPA.

### `ProgresoEstudiante(Estudiante e, Curso c)`

Constructor que inicializa el progreso de un estudiante en un curso
nuevo.

------------------------------------------------------------------------

## Métodos públicos

### `void registrarRespuesta(Flashcard f, boolean correcta)`

Registra una respuesta del usuario:
- Incrementa el total de respuestas.
- Incrementa los aciertos si la respuesta es correcta.
- Añade la flashcard al conjunto de completadas.
- Actualiza la fecha de `ultimaActividad`.

------------------------------------------------------------------------

### `void reset()`

Reinicia todo el progreso del estudiante:
- Se reinician los contadores (`totalRespondidas` y `aciertos`).
- Se vacía el conjunto de flashcards completadas.
- Se actualiza la fecha de última actividad.

------------------------------------------------------------------------

### `double obtenerPorcentajeProgreso()`

Calcula el porcentaje de flashcards completadas con respecto al total del curso:
- Si el curso NO tiene flashcards, devuelve `0.0`.
- Si el curso SI tiene flashcards, devuelve el tanto por 1 de respuestas
  completadas con respecto al total en formato.

------------------------------------------------------------------------

## Getters

  ------------------------------------------------------------------------------
  Método                         Tipo de retorno   Descripción
  ------------------------------ ----------------- -----------------------------
  `getId()`                      `Long`            Devuelve el identificador del
                                                   progreso.

  `getTotalRespondidas()`        `int`             Devuelve el número de
                                                   respuestas totales.

  `getAciertos()`                `int`             Devuelve el número de
                                                   aciertos.

  `getFlashcardsCompletadas()`   `Set<Long>`       Devuelve las flashcards
                                                   completadas.

  `getCurso()`                   `Curso`           Devuelve el curso asociado.
  ------------------------------------------------------------------------------

------------------------------------------------------------------------

## Anotaciones de persistencia (JPA)

-   `@Entity`, `@Table(name = "progresos")`: mapeo de la entidad.
-   `@Id`, `@GeneratedValue`: clave primaria autogenerada.
-   `@ManyToOne`: relación con `Estudiante` y `Curso`.
-   `@ElementCollection`: colección de flashcards completadas (IDs).
-   `@Temporal`: fecha de última actividad con precisión de timestamp.
