# Clase `LoginWindow`

## Paquete `views`
  
##Tipo: Interfaz gráfica (Swing)  


## Índice
-   [Dependencias]	(#Dependencias)
-   [Descripción]	(#Descripción)
-   [Atributos]		(#Atributos)
-   [Constructor]	(#Constructor)
-   [Métodos]		(#Métodos)
-   [Funcionalidad]	(#Funcionalidad)


---

## Dependencias

- `javax.swing.*`				: Componentes de interfaz gráfica.
- `javax.persistence.*`				: JPA para persistencia.
- `models.Estudiante`				: Entidad que representa al usuario.
- `views.RegisterWindow`, `views.MainMenuWindow`: Otras ventanas del sistema.


---

## Descripción

La clase `LoginWindow` es una ventana Swing que permite a los usuarios autenticarse o registrarse en el sistema. Gestiona la conexión con la base de datos mediante JPA, y actúa como pantalla inicial de la aplicación.


---

## Atributos

- `EntityManagerFactory emf`:
	
	Generador de entidades para manejar la persistencia.

- `EntityManager em`:

	 Administrador de entidades que interactúa con la base de datos.

- `JTextField emailField`: 
	
	Campo de entrada para el correo electrónico.

- `JPasswordField passField`:

	Campo de entrada para la contraseña.


---

## Constructor

### `LoginWindow()`

	Inicializa la interfaz gráfica mediante el método `initUI()`.


---

## Métodos Privados

### `initUI()`

	Configura los componentes visuales de la ventana:
	 {etiquetas, campos de texto, botones y listeners}.


### `autenticar()`

	Realiza la autenticación del usuario:
	- Obtiene email y contraseña de los campos.
	- Consulta la base de datos mediante JPA.
	- Si las credenciales son válidas, abre la ventana de menú principal (`MainMenuWindow`).
	- Si las credenciales no son válidas muestra un mensaje de error al usuario.

	
### `msg(String m, int t)`

	Muestra un mensaje de diálogo utilizando `JOptionPane`.


---

## Métodos Sobrescritos

### `dispose()`

	Cierra correctamente los recursos de JPA (`EntityManager` y `EntityManagerFactory`)
	antes de liberar la ventana.

---

## Método `main`

	Permite ejecutar la ventana de forma independiente:

	public static void main(String[] args) {
	    SwingUtilities.invokeLater(() -> new LoginWindow().setVisible(true));
	}


---

##Funcionalidad

Una vez que se nos muestra la pantalla de LogIn (Imagen: login.png) podemos alguna de las siguientes
acciones:

1.-Iniciar Sesión: Para ello debemos introducir correctamente las credenciales (correo y contraseña).

2.-Registrarse: En caso de querer darse de alta en el sistema, podemos hacer uso de esta opción.




