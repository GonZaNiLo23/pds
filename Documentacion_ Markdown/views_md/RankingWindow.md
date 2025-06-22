# Clase `RankingWindow`

## Paquete `views`


## Índice
-   [Dependencias]	(#Dependencias)
-   [Descripción]	(#Descripción)
-   [Constructor]	(#Constructor)
-   [Comp.Graficos]	(#Comp.Graficos)
-   [Consideraciones]	(#Consideraciones)


---

## Dependencias

- `javax.swing.*`
- `javax.swing.table.DefaultTableModel`
- `java.awt.*`
- `java.util.List`
- `models.Curso`
- `models.Estudiante`
- `dao.ProgresoEstudianteDAO`


---

## Descripción general

`RankingWindow` es una clase de tipo `JFrame` que representa una ventana en la interfaz gráfica del
usuario (GUI) para mostrar la clasificación de estudiantes en un curso, según su porcentaje de aciertos (ranking.png).


---

## Constructor

- `public RankingWindow(Curso curso)`

	- Crea una nueva ventana que muestra el ranking de estudiantes
	  para el curso especificado.

### Parámetros

	- `curso`: Instancia de `Curso` para la que se desea generar la clasificación.

### Detalles de implementación

- Configura el título de la ventana como "Ranking • [nombre del curso]".
- Recupera los datos de rendimiento de los estudiantes mediante `ProgresoEstudianteDAO`.
- Utiliza un `DefaultTableModel` para representar los datos en una `JTable`.
- La tabla tiene tres columnas:

  - `Pos.`	: Posición en el ranking.
  - `Alumno`	: Nombre del estudiante.
  - `% Acierto`	: Porcentaje de aciertos del estudiante.

- La tabla no permite edición directa por el usuario.
- Se colocan los componentes en un `JScrollPane` y se añaden al `BorderLayout.CENTER` de la ventana.


---

## Componentes gráficos

	- `JFrame`: Ventana principal.
	- `JTable`: Tabla que muestra el ranking.
	- `JScrollPane`: Contenedor con scroll para la tabla.


---

## Consideraciones
	
	El método `dao.rankingPorCurso(curso)` devuelve una lista de objetos que contienen:
  		- Objeto `Estudiante`.
		- Valor `Double` con el porcentaje de aciertos.
