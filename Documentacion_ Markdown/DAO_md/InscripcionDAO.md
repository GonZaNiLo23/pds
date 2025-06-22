# Documentación: `InscripcionDAO`

##Paquete `dao`  


## Índice

-   [Dependencias]	(#Dependencias)
-   [Descripción]	(#descripción)
-   [Atributos]		(#atributos)
-   [Métodos]		(#métodos)


------------------------------------------------------------------------

##Dependencias

- `javax.persistence`
- `models.Curso`
- `models.Estudiante`
- `models.Inscripcion`  


------------------------------------------------------------------------

## Descripción 

La clase `InscripcionDAO` actúa como un objeto de acceso a datos (DAO) para gestionar operaciones de persistencia relacionadas con la entidad `Inscripcion`. Utiliza JPA (`Java Persistence API`) para interactuar con la base de datos.


---

## Atributos

  ------------------------------------------------------------------------
  Atributo                  Tipo                        Descripción
  ------------------------- --------------------------- ------------------
  `emf`                     `EntityManagerFactory`      Permite obtener `EntityManager`
							y gestionar la unidad de persistencia
							`flashcards-jpa`.
  ------------------------------------------------------------------------


---

## Métodos públicos

### `void guardar(Inscripcion inscripcion)`

Guarda una inscripción en la base de datos.  
- Si la inscripción es nueva (`id == null`), se realiza un `persist`.
- Si ya existe, se hace un `merge`.


### `List<Inscripcion> buscarPorEstudiante(Estudiante estudiante)`

Recupera todas las inscripciones de un estudiante concreto, ordenadas por fecha de inicio descendente.


### `boolean estaInscrito(Estudiante estudiante, Curso curso)`

Devuelve `true` si el estudiante está inscrito en el curso especificado, `false` en caso contrario.  
Consulta la base de datos mediante un `COUNT`.


### `void eliminar(Estudiante estudiante, Curso curso)`

Elimina la inscripción del estudiante en el curso dado (si existe).


### `static void cerrarFactory()`

Cierra la fábrica de `EntityManager` si está abierta.  
**Importante:** Debe llamarse al cerrar la aplicación para liberar recursos.


---

## Notas de implementación


- Utiliza `EntityManager` de forma local en cada método, lo cual favorece la limpieza de recursos.
- Todas las operaciones de modificación (`guardar`, `eliminar`) están envueltas en transacciones.
- Utiliza consultas JPQL con `@Query` multi-línea (`""" ... """`) para mejorar la legibilidad.


---
