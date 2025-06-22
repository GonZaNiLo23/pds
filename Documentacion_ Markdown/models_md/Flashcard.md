# Clase `Flashcard`

## Paquete `models`

## Índice

-   [Descripción]	(#Descripcion)
-   [Atributos]		(#atributos)
-   [Constructores]	(#constructores)
-   [Métodos]		(#métodos)
-   [Relaciones]	(#relaciones)

------------------------------------------------------------------------

## Descripcion

Entidad JPA que representa un ejercicio o pregunta dentro de un curso.
Cada `Flashcard` puede ser de diferentes tipos (completar hueco,
verdadero/falso, pregunta abierta o multiopción).

------------------------------------------------------------------------

## Atributos

  --------------------------------------------------------------------------
  Atributo              Tipo             Descripción
  --------------------- ---------------- -----------------------------------
  `id`                  `Long`           Identificador único de la
                                         flashcard.

  `pregunta`            `String`         Enunciado de la pregunta.

  `respuestaCorrecta`   `String`         Respuesta válida para la flashcard.

  `opciones`            `List<String>`   Lista de opciones posibles.
					 Puede estar vacía para preguntas
					 abiertas.

  `tipo`		`TipoFlashcard`  Tipo de la flashcard (enumerado).

  `curso`		`Curso`		 Curso al que pertenece la flashcard.
  --------------------------------------------------------------------------

## Enumeraciones

### `TipoFlashcard`

Define el tipo de pregunta y la forma en que será presentada al usuario.

-   `COMPLETAR_HUECO`
-   `VERDADERO_FALSO`
-   `PREGUNTA_ABIERTA`
-   `MULTIOPCION`

------------------------------------------------------------------------

## Constructores

### protected Flashcard()

Requerido por JPA.

### Métodos de creación

La clase proporciona métodos estáticos para construir instancias según su tipo:

-   `crearCompletarHueco(String fraseCompleta, String respuesta, List<String> distractores)`
-   `crearVerdaderoFalso(String afirmacion, boolean esVerdadera)`
-   `crearPreguntaAbierta(String pregunta, String respuesta)`
-   `crearMultiopcion(String pregunta, String correcta, List<String> distractores)`

------------------------------------------------------------------------

## Métodos

-   boolean esRespuestaCorrecta(String respuesta): 
	comprueba si una respuesta del usuario es válida.

### Getters y setters

Incluye métodos de acceso a `id`, `pregunta`, `respuestaCorrecta`,
`tipo`, `curso`, y `opciones`.

### Getters

-   Long getId() 			: Devuelve el id de la Flashcard.
-   String getPregunta() 		: Devuelve la pregunta realizada por la Flashcard.
-   String getRespuestaCorrecta() 	: Devuelve la respuesta correcta de la Flashcard.
-   TipoFlashcard getTipo() 		: Devuelve el tipo de ejercicio de la Flashcard.
-   Curso getCurso() 			: Devuelve el curso al que pertenece la Flashcard.
-   List<String> getOpcionesBarajadas() : devuelve las opciones mezcladas aleatoriamente.

### Setters

-   void setCurso(Curso c) : 
	Establece el curso al que pertenece, según el valor de `c`.

------------------------------------------------------------------------

## Relaciones

-   Muchas tarjetas (`Flashcard`) pueden pertenecer a un único curso
    (`Curso`), mediante `@ManyToOne`.
