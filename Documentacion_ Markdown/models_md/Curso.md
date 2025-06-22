# Clase `Curso`

## Paquete `models`

## Índice

-   [Descripción]	(#descripción)
-   [Atributos]		(#atributos)
-   [Enumerados]	(#Enumerados)
-   [Constructores]	(#constructores)
-   [Métodos]		(#métodos)
-   [Relaciones]	(#relaciones)

------------------------------------------------------------------------

## Descripción

La clase `Curso` es una entidad JPA mapeada a la tabla `cursos` que
representa cada uno de los cursos en el sistema de estudio que los
usuarios pueden realizar dentro del sistema. Contiene información básica
de un curso, como nombre, descripción, fecha de creación, visibilidad,
estrategia de estudio, creador y tarjetas asociadas.

------------------------------------------------------------------------

## Atributos

  --------------------------------------------------------------------------
  Atributo               Tipo                             Descripción
  ---------------------- -------------------------------- ------------------
  `id`                   `Long`                           Identificador
                                                          único del curso.

  `nombre`               `String`                         Nombre del curso
                                                          (obligatorio, máx.
                                                          120 caracteres).

  `descripcion`          `String`                         Descripción (máx.
                                                          500 caracteres).

  `fechaCreacion`        `Date`                           Fecha y hora de
                                                          alta del curso. Se
                                                          inicializa
                                                          automáticamente.

  `tipoEstrategia`       `TipoEstrategia`                 Estrategia de
                                                          aprendizaje del
                                                          curso. Por
                                                          defecto:
                                                          `SECUENCIAL`.

  `creador`              `Estudiante`                     Estudiante creador
                                                          del curso.
                                                          Relación
                                                          `@ManyToOne`.

  `flashcards`           `Set<Flashcard>`                 Colección de
                                                          tarjetas asociadas
                                                          al curso. Relación
                                                          `@OneToMany`.

  `esPublico`            `boolean`                        Indica si el curso
                                                          es público. Por
                                                          defecto es `true`.
  --------------------------------------------------------------------------

------------------------------------------------------------------------

## Enumeración TipoEstrategia

Hay 3 tipos de Estrategia de Aprendizaje:

public enum TipoEstrategia { SECUENCIAL, REPETICION, ALEATORIO }

Cada una define el método con el que se presentan las tarjetas:

-   `SECUENCIAL`: en orden.
-   `REPETICION`: según repetición espaciada.
-   `ALEATORIO`: en orden aleatorio.

------------------------------------------------------------------------

## Constructores

### protected Curso()

Constructor protegido sin argumentos. Requerido por JPA.

### public Curso(String nombre, String descripcion,
                 TipoEstrategia tipo, Set<Flashcard> fc,
                 Estudiante creador, boolean esPublico)

Constructor principal. Inicializa un nuevo curso con los parámetros
dados. Si `tipo` es `null`, se asigna `SECUENCIAL`. Añade las flashcards
al curso.

------------------------------------------------------------------------

## Métodos

### void addFlashcard(Flashcard f)

Asocia una flashcard al curso y actualiza la relación bidireccional.

### Getters y setters

Incluye métodos para acceder y modificar todos los atributos: `nombre`,
`descripcion`, `tipoEstrategia`, `creador`, `flashcards`, `esPublico`.

  ------------------------------------------------------------------------------------
  Método                   Tipo de retorno               Descripción
  ------------------------ ----------------------------- -----------------------------
  `getId()`                `Long`                        Devuelve el identificador del
                                                         curso.

  `getNombre()`            `String`                      Devuelve el nombre del curso.

  `getDescripcion()`       `String`                      Devuelve la decripcion del
                                                         curso.

  `getFechaCreacion()`     `Date`                        Devuelve la fecha de creación
                                                         del curso.

  `getTipoEstrategia()`    `TipoEstrategia`              Devuelve el Tipo de
                                                         Estrategia seguida.

  `getCreador()`           `Estudiante`                  Devuelve el Usuario que creó
                                                         el curso.

  `getFlashcards()`        `List`<Flashcards>`           Devuelve la lista de
                                                         Flashcards del curso.
  ------------------------------------------------------------------------------------

  ------------------------------------------------------------------------------
  Método                                  Descripción
  --------------------------------------- --------------------------------------
  `setNombre(String n)`                   Establece el nombre del Curso según el
                                          valor de n.

  `setDescripcion(String d)`              Establece la descripción del curso
                                          según el valor de d.

  `setTipoEstrategia(TipoEstrategia t)`   Establece el tipo de estrategia
                                          seguida según `t`

  `setCreador(Estudiante e)`              Establece al usuario `e` como creador
                                          del curso.
  ------------------------------------------------------------------------------

------------------------------------------------------------------------

## Relaciones

-   **`@ManyToOne` con `Estudiante`**: un curso tiene un único creador.
-   **`@OneToMany` con `Flashcard`**: un curso puede tener múltiples
    tarjetas. Se aplican:
    -   `cascade = ALL`
    -   `orphanRemoval = true`
    -   `fetch = EAGER`

------------------------------------------------------------------------
