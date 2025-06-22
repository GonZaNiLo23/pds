# Clase `Main`

##Paquete `app`


## Índice

-   [Dependencias]	(#Dependencias)
-   [Descripción]	(#descripción)
-   [Métodos]		(#métodos)
-   [Consideraciones]	(#Consideraciones)


------------------------------------------------------------------------

## Dependencias

- `LoginWindow` (paquete `views`): Ventana gráfica para el inicio de sesión.
- `CursoDAO` (paquete `dao`): Clase DAO encargada de gestionar la persistencia de los cursos.
- `javax.swing.*`: Librería Swing para interfaces gráficas.


------------------------------------------------------------------------

## Descripción

La clase `Main` representa el punto de entrada principal de la aplicación. 
Su función es inicializar la interfaz gráfica de usuario y gestionar la configuración
y el cierre adecuado de recursos.


---

## Métodos

### `public static void main(String[] args)`

Este método se ejecuta al iniciar la aplicación. Realiza las siguientes tareas:

1. Establece el "Look and Feel" del sistema:
	
	Adapta la interfaz gráfica para que coincida con el aspecto visual del sistema
	operativo del usuario.

2. Lanza la ventana de login:
	
	Utiliza `SwingUtilities.invokeLater(...)` para mostrar de forma segura la ventana
	de inicio de sesión (`LoginWindow`) en el hilo de eventos de Swing.

3. Registra un hook de apagado: 

	Añade un *shutdown hook* para cerrar correctamente la factoría de persistencia
	mediante `CursoDAO.cerrarFactory()` cuando se cierre la aplicación.


---

## Consideraciones

- Thread-safe: 
	
	Se asegura el uso correcto del hilo de eventos para Swing usando
	`SwingUtilities.invokeLater(...)`.

- Cierre controlado:
	
	El *hook* de apagado garantiza una correcta liberación de recursos
	de persistencia al cerrar la aplicación.


---