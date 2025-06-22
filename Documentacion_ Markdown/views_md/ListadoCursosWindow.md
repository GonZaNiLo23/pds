## Clase `ListadoCursosWindow`

## Paquete `views`


## Índice
-   [Dependencias]	(#Dependencias)
-   [Descripción]	(#Descripción)
-   [Atributos]		(#Atributos)
-   [Constructor]	(#Constructor)
-   [Métodos]		(#Métodos)
-   [Consideraciones]	(#Consideraciones)


---

## Dependencias

- `CursoDAO`, `InscripcionDAO`
- `models.Curso`, `models.Estudiante`
- `BuscarCursosWindow`
- `PracticaWindow`
- `CrearCursoWindow`
- `RankingWindow`


---

## Descripción

Ventana principal que muestra todos los cursos relacionados con un estudiante: propios, inscritos y disponibles. Permite al usuario realizar acciones como practicar, editar, desinscribirse o acceder al ranking de un curso.


---

## Atributos

- `Estudiante estudianteActual`		: Usuario actualmente autenticado.
- `CursoDAO cursoDAO`			: DAO para acceder a cursos.
- `InscripcionDAO inscripcionDAO`	: DAO para gestionar inscripciones.
- `DefaultListModel<Curso> model`	: Modelo de la lista de cursos.
- `JList<Curso> cursosList`		: Lista gráfica de cursos.
- `Dimension BTN`			: Dimensión estándar para los botones.


---

## Constructor

public ListadoCursosWindow(Estudiante est)

- Inicializa la interfaz y carga los cursos del estudiante.


---

## Métodos

### `initUI()`

	- Construye y configura todos los componentes gráficos de la ventana.
	- Incluye panel superior con ayuda y botón de búsqueda.
	- Añade botones de acción: **Practicar**, **Editar**, **Desinscribir**, **Ranking**, 	**Cerrar**.
	- Personaliza el renderizado de cada curso (colores y estilos según el estado del curso: 	propio, inscrito, disponible).


### `cargarCursos()`
	
	- Recupera y muestra los cursos relacionados con el estudiante.
	- Muestra mensaje si la lista está vacía.


### `abrirVentanaBusqueda()`

	- Abre una ventana para buscar e inscribirse a nuevos cursos públicos.


### `desinscribir()`

	- Permite al usuario desinscribirse de un curso ajeno.
	- Confirma la acción y actualiza la lista.


### `sel()`

	- Devuelve el curso actualmente seleccionado en la lista.

	
### `practicar()`

	- Abre la ventana de práctica del curso seleccionado.


### `editar()`

	- Abre la ventana de edición del curso, si es propiedad del usuario.
	

### `ranking()`
	
	- Abre la ventana del ranking de un curso.
	

### `info(String mensaje)`
	
	- Muestra una ventana emergente con advertencia.


### `info(String mensaje, int tipo)`

	- Muestra una ventana emergente con tipo de mensaje (info, error, etc.).


---

## Métodos públicos

### `void actualizarLista()`

	- Refresca la lista de cursos (se usa desde otras ventanas, como la de búsqueda).

---

### Consideraciones

	- Cursos en **verde**: cursos propios.
	- Cursos en **azul**: cursos en los que el usuario está inscrito.
	- Cursos en **gris**: disponibles pero no inscritos.
	- Indicadores de visibilidad: Puede ser `[PÚBLICO]` o `[PRIVADO]`.


---