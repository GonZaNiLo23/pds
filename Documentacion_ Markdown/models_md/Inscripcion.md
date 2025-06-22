# Clase `Inscripcion`

## Paquete `models`

## Índice

-   [Descripción]	(#descripción)
-   [Atributos]		(#atributos)
-   [Constructores]	(#constructores)
-   [Métodos]		(#métodos)
-   [Relaciones]	(#relaciones)

------------------------------------------------------------------------

## Descripción

La clase `Inscripcion` representa el registro de un estudiante en un
curso, incluyendo información como la fecha de inicio, el progreso del
estudiante y la estrategia de estudio seleccionada.

------------------------------------------------------------------------

## Atributos

  ------------------------------------------------------------------------
  Atributo                           Tipo               Descripción
  ---------------------------------- ------------------ ------------------
  `fechaInicio`                      `Date`             Fecha en la que el
                                                        estudiante se
                                                        inscribe en el
                                                        curso.

  `progreso`                         `String`           Progreso actual
                                                        del estudiante en
                                                        el curso.

  `estrategiaSeleccionada`           `String`           Estrategia de
                                                        estudio elegida
                                                        por el estudiante.

  `estudiante`                       `Estudiante`       Estudiante
                                                        inscrito.

  `curso`                            `Curso`            Curso en el que el
                                                        estudiante se ha
                                                        inscrito.
  ------------------------------------------------------------------------

------------------------------------------------------------------------

## Constructores

### Constructor principal

-   public Inscripcion(Estudiante estudiante, Curso curso, String
    estrategia)

Inicializa una nueva inscripción con el estudiante, curso y estrategia
indicados. El progreso se inicia en `"0%"` y se registra la fecha actual
como fecha de inicio.

------------------------------------------------------------------------

## Métodos

-   void actualizarProgreso(String nuevoProgreso): 
	Actualiza el progreso del estudiante en el curso.

### Getters

-   Long getId() 		: Devuelve el identificador de la Inscripción.
-   Date getFechaInicio()	: Devuelve la Fecha de inicio de la Inscripción.
-   String getProgreso() 	: Devuelve el Progreso de la Inscripción.
-   Estudiante getEstudiante() 	: Devuelve el usuario que realiza la inscripción.
-   Curso getCurso() 		: Devuelve el curso en que se realiza la inscripción.
-   TipoEstrategia getEstrategiaSeleccionada() : Devuelve el tipo de Estrategia seleccionada.

> (todas las funciones tienen visibilidad public)

### Setters

-   public void setProgreso(String progreso) : 
	Establece el Progreso de los usuarios.

------------------------------------------------------------------------

## Relaciones

-   Cada `Inscripcion` se asocia con un único `Estudiante`.
-   Cada `Inscripcion` se asocia con un único `Curso`.
