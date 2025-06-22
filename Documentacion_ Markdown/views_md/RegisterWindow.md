# Clase `RegisterWindow`

##Paquete `views`  


## Índice

-   [Dependencias]	(#Dependencias)
-   [Descripción]	(#Descripción)
-   [Atributos]		(#Atributos)
-   [Constructor]	(#Constructor)
-   [Métodos]		(#Métodos)
-   [Funcionalidad]	(#Funcionalidad)


---

## Dependencias

- `javax.swing.*`		: Para la construcción de la interfaz de usuario.
- `javax.persistence.*`		: Para operaciones con JPA.
- `models.Estudiante`		: Representación de la entidad estudiante que se persiste.


---

## Descripción General

La clase `RegisterWindow` extiende `JFrame` y proporciona una interfaz gráfica para que nuevos
usuarios se registren en el sistema. Incluye validación básica, verificación de duplicados 
(no permitidos) en la base de datos y persistencia utilizando JPA.


---

## Atributos

  --------------------------------------------------------------------------------------------
  Atributo                  Tipo                        Descripción
  ------------------------- --------------------------- --------------------------------------
  `emf`              	    `EntityManagerFactory`      Productor JPA para la conexión con la
							base de datos.

  `em`        		    `EntityManager`             Gestor de entidades JPA para 
							operaciones de persistencia.

  `nombreField`		    `JTextField`		Campo de entrada para el nombre del 								estudiante.

  `emailField`		    `JTextField`		Campo de entrada para el correo
							electrónico del estudiante. 

  `passField`		    `JPasswordField`		Campo de entrada para la contraseña del 							estudiante.
  ------------------------------------------------------------------------


---

## Constructores

### `public RegisterWindow()`

	Inicializa la interfaz de usuario y configura la ventana de registro.


---

## Métodos Privados

### `private void initUI()`

	Configura todos los componentes de la interfaz gráfica, su disposición y acciones.


### `private void registrar()`

	Valida los campos del formulario, comprueba la existencia del correo en la base de datos
	y registra un nuevo estudiante si es válido.

	Pasos clave:
	- Verifica campos vacíos.
	- Comprueba si ya existe un estudiante con el mismo correo (en cuyo caso no es válido).
	- Introduce un nuevo estudiante en la base de datos con JPA.


### `private void msg(String mensaje, int tipo)`

	Muestra un mensaje emergente (`JOptionPane`) con el texto y tipo especificado.


---

## Métodos Sobrescritos

### `@Override public void dispose()`

	Cierra el `EntityManager` y la `EntityManagerFactory` al cerrar la ventana. Llama a 	`super.dispose()` para liberar recursos de Swing.


---

## Método Principal

### `public static void main(String[] args)`

	Ejecuta la aplicación mostrando la ventana de registro utilizando
	`SwingUtilities.invokeLater`.


---

## Funcionalidad

Se nos muestra la pantalla de registro (registro.png) donde el usuario debe introducir sus
credenciales en los campos {nombre, correo, contraseña}, y por último confirmar el registro
clickeando en 'Registrarse'. 


