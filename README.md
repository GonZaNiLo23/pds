# Flashcards App – PROCESOS DE DESARROLLO SOFTWARE

Aplicación de escritorio en **Java 17 (Swing + Hibernate)** para crear cursos de
flashcards y practicarlos mediante diferentes **estrategias de aprendizaje**  
(secuencial, aleatoria y repetición con espaciamiento). El objetivo es facilitar
la memorización progresiva y el seguimiento del progreso del estudiante.

---

## Integrantes del grupo

| Alumno | Grupo |
| ------ | ----- |
| **Pedro José Aguilar Garre** | G 3.1 |
| **Felipe Antonio Molina Ayala** | G 2.2 |
| **Gonzalo Nicolás López** | G 2.2 |

**Profesor responsable:** *[nombre del profesor]*

---

## Documentación del análisis

* 📄 [Casos de uso](docs/casos-de-uso.md)  
* 🖼️ [Modelo de dominio](docs/modelo-dominio.png)
* 📚 [Algoritmo](SistemaCursos/)

---

## Funcionalidad implementada

| Módulo | Descripción |
| ------ | ----------- |
| **Autenticación** | Registro y login de estudiantes con validación de datos |
| **Gestión de cursos** | Crear, buscar, listar e inscribirse a cursos de flashcards |
| **Flashcards** | CRUD completo de tarjetas con pregunta y respuesta |
| **Estrategias de práctica** | Secuencial, aleatoria y repetición (espaciada) |
| **Barra de progreso** | Avanza según la estrategia seleccionada |
| **Ranking global** | Tabla de clasificación por porcentaje de aciertos |
| **Estadísticas** | Ventana de estadísticas personales y globales |
| **Funcionalidad extra** | Algoritmo de repetición espaciada, importación/exportación CSV de flashcards, tema oscuro |

---

## Requisitos del sistema

- **Java 17** o superior
- **Maven 3.6+** para gestión de dependencias
- **Base de datos compatible con H2** (incluida en el proyecto)

---

## Cómo ejecutar la aplicación

### 1. Clonar el repositorio

```bash
git clone https://github.com/GonZaNiLo23/pds.git
cd .\pds\SistemaCursos\
```

### 2. Configuración del proyecto

- Abrir el proyecto en tu IDE favorito (IntelliJ IDEA, Eclipse, NetBeans, etc.)
- Asegurarse de que el proyecto esté configurado con Java 17 o superior
- El IDE debería reconocer automáticamente el proyecto Maven y descargar las dependencias

### 3. Ejecutar la aplicación

- Localizar la clase principal `Main.java`
- Hacer clic derecho → "Run Main.main()" (o equivalente según el IDE)
- La aplicación se iniciará con la ventana de login

> **Nota**: La primera ejecución creará automáticamente la base de datos H2 como archivo `flashcardsdb.mv.db` en el directorio raíz del proyecto. En este caso ya está creada con cursos de ejemplo.

## Estructura del proyecto

```
pds/
├── Imagenes/                           # Imágenes de las ventanas del programa
├── SistemaCursos/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   ├── app/                # Ejecución del programa
│   │   │   │   ├── dao/                # Acceso a datos
│   │   │   │   ├── estrategias/        # Estrategias de aprendizaje
│   │   │   │   ├── models/             # Entidades JPA (Curso, Flashcard, Estudiante, etc.)
│   │   │   │   └── views/              # Interfaz de usuario (Swing)
│   │   │   └── resources/META-INF/
│   │   │       └── persistence.xml     # Configuración de JPA
│   │   └── test/java/
│   │       ├── dao/
│   │       └── models/
│   ├── flashcardsdb.mv.db              # Base de datos H2 (generada automáticamente)
│   └── pom.xml                         # Configuración de Maven
└── docs/                               # Documentación del proyecto
```
