
# Clase `CursoDAO`

## Paquete `dao`


## Índice

-   [Dependencias]	(#Dependencias)
-   [Descripción]	(#descripción)
-   [Atributos]		(#atributos)
-   [Métodos]		(#métodos)


------------------------------------------------------------------------

## Dependencias

import models.Curso;
import models.Estudiante;
import javax.persistence.*;
import java.util.List;


------------------------------------------------------------------------

## Descripción
La clase `CursoDAO` proporciona acceso a la base de datos para la entidad `Curso`, gestionando operaciones CRUD y consultas específicas relacionadas con los cursos de estudiantes.


------------------------------------------------------------------------

## Atributos

  ------------------------------------------------------------------------
  Atributo                  Tipo                        Descripción
  ------------------------- --------------------------- ------------------
- `emf`			    `EntityManagerFactory`     Permite gestionar 
						       conexiones con la base
						       de datos usando JPA.
  ------------------------------------------------------------------------


---

## Métodos

### `void guardar(Curso c)`
Guarda un curso nuevo o actualiza uno existente en la base de datos.

- Si `c.getId()` es `null`, usa `persist()`.
- Si no, usa `merge()` para actualizarlo.


### `List<Curso> buscarCursosDelUsuario(Estudiante estudiante)`
Devuelve una lista de cursos en los que el estudiante es el creador o en los que está inscrito (pero no es el creador). Optimizado con una única consulta JPQL.


### `List<Curso> buscarCursosPublicosDisponibles(Estudiante estudiante)`
Consulta los cursos públicos disponibles creados por otros usuarios en los que el estudiante no está inscrito.

- Utiliza subconsulta para excluir los cursos ya inscritos.


### `List<Curso> buscarTodos()`

Devuelve todos los cursos ordenados por fecha de creación descendente.


### `static void cerrarFactory()`

Cierra el `EntityManagerFactory`. Debe llamarse al cerrar la aplicación para liberar recursos, especialmente cuando se utiliza una base de datos H2.


---

## Notas

- La clase está pensada para ser usada como DAO tradicional, encapsulando la lógica de persistencia.
- Se recomienda utilizarla en combinación con servicios o controladores que gestionen la lógica de negocio.
