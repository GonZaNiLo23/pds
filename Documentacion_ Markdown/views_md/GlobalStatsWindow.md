# Clase GlobalStatsWindow

## Paquete `views`


---

## Índice
-   [Dependencias]	(#Dependencias)
-   [Descripción]	(#Descripción)
-   [Constructor]	(#Constructor)
-   [Componentes]	(#Componentes)


---

## Dependencias

La clase utiliza varias bibliotecas estándar de Java, así como modelos y DAOs personalizados:

- dao.ProgresoEstudianteDAO;
- models.ProgresoEstudiante;
- models.Estudiante;

- javax.swing.*;
- javax.swing.table.DefaultTableModel;
- java.awt.*;
- java.util.List;


---

## Descripción 

`GlobalStatsWindow` es una clase de interfaz gráfica que extiende `JFrame` y muestra estadísticas globales del rendimiento de un estudiante en todos sus cursos. Esta ventana incluye un resumen de rendimiento total y una tabla detallada con datos por curso (estadísticas.png).


## Constructor

- public GlobalStatsWindow(Estudiante est)

### Parámetros

- `est`: instancia del modelo `Estudiante` que representa al alumno cuyas estadísticas se desean visualizar.

### Funcionalidad
- Obtiene las estadísticas del estudiante desde `ProgresoEstudianteDAO`.
- Calcula el total de preguntas respondidas, aciertos y el porcentaje de precisión general.
- Muestra un resumen en un panel superior con etiquetas y una barra de progreso.
- Genera una tabla detallada con estadísticas por curso: {nombre del curso, preguntas respondidas,
  aciertos, precisión y progreso}.

## Componentes de la Interfaz

### Panel superior (`JPanel top`)
- Muestra un resumen con:
  - Total de preguntas respondidas.
  - Total de aciertos.
  - Porcentaje de precisión global.

### Barra de progreso (`JProgressBar bar`)
- Visualiza gráficamente el porcentaje de precisión global.

### Tabla (`JTable tabla`)
- Muestra estadísticas individuales por curso.
- Columnas: Curso, Respondidas, Aciertos, % Precisión, % Progreso.
- La tabla es de solo lectura.
