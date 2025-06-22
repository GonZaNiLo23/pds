# Clase `Estudiante`

## Paquete `models`

## Índice

-   [Descripción]	(#descripción)
-   [Atributos]		(#atributos)
-   [Constructores]	(#constructores)
-   [Métodos]		(#métodos)
-   [Relaciones]	(#relaciones)
-   [Restricciones]	(#restricciones)

------------------------------------------------------------------------

## Descripción

Entidad JPA que representa a un usuario o alumno del sistema. Esta clase
contiene los datos básicos del estudiante y está mapeada a la tabla
`estudiantes`. Cada estudiante tiene un identificador, nombre, correo
electrónico y una contraseña cifrada.

------------------------------------------------------------------------

## Atributos

  --------------------------------------------------------------------------
  Atributo     Tipo       Descripción
  ------------ ---------- --------------------------------------------------
  `id`         `Long`     Identificador único del estudiante.

  `nombre`     `String`   Nombre del estudiante (máx. 80 caracteres).

  `email`      `String`   Correo electrónico del estudiante (obligatorio,
                          máx.120 caracteres).

  `password`   `String`   Contraseña cifrada del estudiante (obligatoria,
                          máx. 60 caracteres).
  --------------------------------------------------------------------------

------------------------------------------------------------------------

## Constructores

### protected Estudiante()

Constructor requerido por JPA.

### public Estudiante(String nombre, String email, String password)

Crea una instancia de estudiante con los datos obligatorios.

------------------------------------------------------------------------

## Métodos

### Getters y setters

Incluye métodos para acceder y modificar todos los atributos: `nombre`,
`email`, `password`.

### Getters

-   public Long getId() : Devuelve el id del usuario.
-   public String getNombre() : Devuelve el nombre del usuario.
-   public String getEmail() : Devuelve el email del usuario.
-   public String getPassword() : Devuelve la contraseña del usuario.

### Setters

-   public void setNombre(String n) : Establece el nombre del usuario
    con el valor de `n`.
-   public void setEmail(String e) : Establece el email del usuario con
    el valor de `e`.
-   public void setPassword(String p) : Establece la contraseña del
    usuario con el valor de `p`.

### Métodos sobrescritos

-   `equals(Object o)`: compara dos estudiantes por su `id`.
-   `hashCode()`: genera el hash basado en el `id`.
-   `toString()`: devuelve el nombre del estudiante.

------------------------------------------------------------------------

## Relaciones

Esta clase puede estar relacionada con otras entidades como `Curso` (por
ejemplo, como creador del curso), aunque no contiene referencias
directas a otras entidades.

------------------------------------------------------------------------

## Restricciones

-   El campo `email` debe ser único en la base de datos
    (`@UniqueConstraint`).
-   Todos los atributos son obligatorios (`nullable = false`).
