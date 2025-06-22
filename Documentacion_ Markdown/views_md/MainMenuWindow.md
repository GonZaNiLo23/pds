
# Clase `MainMenuWindow`

## Paquete `views`  

  
## Índice

-   [Dependencias]	(#Dependencias)
-   [Descripción]	(#Descripción)
-   [Atributos]		(#Atributos)
-   [Constructor]	(#Constructor)
-   [Métodos]		(#Métodos)
-   [Funcionalidad]	(#Funcionalidad)


---

## 🔗 Dependencias

- `models.Estudiante`
- `CrearCursoWindow`, `ListadoCursosWindow`, `GlobalStatsWindow`, `LoginWindow`
- `javax.swing.*`


---

## Descripción

Ventana principal que se muestra al estudiante tras iniciar sesión. Proporciona acceso a las funcionalidades principales del sistema: creación de cursos, visualización de cursos, estadísticas y cierre de sesión (principal.png).


---

## Atributos

- **BTN_SIZE** (`Dimension`): Dimensiones estándar para los botones del menú.
- **estudianteActual** (`Estudiante`): Usuario que ha iniciado sesión. Su información se muestra en la ventana y se pasa a otras vistas.


---

## Constructor

### `MainMenuWindow(Estudiante estudianteActual)`
	
	Inicializa la interfaz de usuario con el nombre del estudiante en el título y
	agrega los botones funcionales.


---

## Métodos Privados

### `nuevoBoton(String texto, ActionListener al)`
Crea y configura un botón con el texto y el comportamiento especificado.

---

## Métodos de Acción

### `abrirCrearCurso()`
Abre la ventana para crear un nuevo curso (`CrearCursoWindow`).

### `abrirMisCursos()`
Abre la ventana de cursos del usuario (`ListadoCursosWindow`).

### `abrirStats()`
Abre la ventana de estadísticas globales (`GlobalStatsWindow`).

### `cerrarSesion()`
Cierra la sesión actual y vuelve a mostrar la ventana de login (`LoginWindow`).

---

## Método `main` de Prueba

Permite ejecutar esta ventana de forma independiente para pruebas rápidas.


---

## Funcionalidad de la Interfaz

- `JLabel` con el título del sistema.
- `JButton` para:
  - Crear curso
  - Ver mis cursos
  - Estadísticas globales
  - Cerrar sesión

