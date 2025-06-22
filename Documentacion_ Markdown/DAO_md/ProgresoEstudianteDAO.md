# Clase `ProgresoEstudianteDAO`

## Paquete `dao`


## Índice

-   [Dependencias]	(#Dependencias)
-   [Descripción]	(#descripción)
-   [Atributos]		(#atributos)
-   [Métodos]		(#métodos)


------------------------------------------------------------------------

## Dependencias

-   `models.Curso`
-   `models.Estudiante`
-   `models.ProgresoEstudiante`
-   `javax.persistence.*`


------------------------------------------------------------------------

## Descripción general

La clase `ProgresoEstudianteDAO` implementa un patrón DAO (Data Access
Object) para gestionar operaciones de persistencia sobre la entidad
`ProgresoEstudiante`. Utiliza JPA (`javax.persistence`) para acceder y
manipular los datos almacenados en una base de datos relacional.


------------------------------------------------------------------------

## Atributos

### `private static final EntityManagerFactory emf`

Fábrica de `EntityManager` que se crea a partir de la unidad de
persistencia `"flashcards-jpa"`.

### `private final EntityManager em`

Instancia de `EntityManager` utilizada para interactuar con la base de
datos.

------------------------------------------------------------------------

## Métodos públicos

### `ProgresoEstudiante buscarPorEstudianteYCurso(Estudiante est, Curso curso)`

	Busca un progreso de estudiante específico para un curso dado.\
	**Parámetros:** - `est` -- Estudiante. - `curso` -- Curso.
	
	**Devuelve:** una instancia de `ProgresoEstudiante` o `null` si no se
	encuentra.
	
	
### `List<ProgresoEstudiante> buscarPorEstudiante(Estudiante est)`

	Obtiene todos los progresos registrados para un estudiante, sin importar
	el curso.
	
	**Parámetros:**\
	- `est` -- Estudiante.

	**Devuelve:** una lista de `ProgresoEstudiante`.


### `List<Object[]> rankingPorCurso(Curso curso)`

	Calcula el ranking de estudiantes en un curso basado en su porcentaje de
	aciertos.\
	**Parámetros:** - `curso` -- Curso a consultar.
	
	**Devuelve:** una lista de objetos `Object[]` donde: - Índice `0`:
	instancia de `Estudiante`. - Índice `1`: porcentaje de aciertos
	(`double`).
	
	El resultado está ordenado en orden descendente.


### `void guardar(ProgresoEstudiante p)`

	Guarda o actualiza una entidad `ProgresoEstudiante` en la base de
	datos.\
	**Parámetros:** - `p` -- Instancia de `ProgresoEstudiante` a guardar.
	
	
### `void cerrar()`

	Cierra el `EntityManager` si aún está abierto.

---
