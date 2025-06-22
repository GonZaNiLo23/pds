# Flashcards App – PROCESOS DE DESARROLLO SOFTWARE

## 📚 Descripción del Proyecto

**Flashcards App** es una aplicación de escritorio de aprendizaje interactivo diseñada para permitir a los usuarios crear, gestionar y practicar cursos de flashcards de manera eficiente y personalizada. Los usuarios pueden registrarse como **Estudiantes** para acceder y practicar cursos, mientras que también pueden crear sus propios cursos y flashcards. La aplicación ofrece múltiples estrategias de aprendizaje configurables (secuencial, aleatorio y repetición espaciada), seguimiento detallado de progreso y un sistema de gamificación mediante rankings.

## 🎯 Objetivo

El objetivo principal es proporcionar una plataforma robusta y escalable que facilite el proceso de memorización y aprendizaje mediante flashcards, con un enfoque en la personalización de la experiencia educativa. La aplicación busca optimizar la retención de conocimientos a través de técnicas científicamente respaldadas como la repetición espaciada, mientras fomenta la motivación mediante elementos de gamificación y competencia saludable entre usuarios.

---

## 👥 Integrantes del grupo

| Alumno | Grupo |
| ------ | ----- |
| **Pedro José Aguilar Garre** | G 3.1 |
| **Felipe Antonio Molina Ayala** | G 2.2 |
| **Gonzalo Nicolás López** | G 2.2 |

**👨‍🏫 Profesor responsable:** *Antonio López Martínez-Carrasco*

---

## 📋 Documentación del análisis

* 📄 [Casos de uso](docs/casos-de-uso.md)  
* 🖼️ [Modelo de dominio](docs/modelo-dominio.png)
* 📚 [Algoritmo](SistemaCursos/)
* 📋 [Documentación](docs/documentacion/)
* 🎯 [README](README.md)

---

## ⚙️ Funcionalidad implementada

### Funcionalidades principales

| Módulo | Descripción |
| ------ | ----------- |
| **🔐 Autenticación** | Sistema completo de registro y login con validación de datos |
| **📚 Gestión de cursos** | Crear, buscar, listar e inscribirse a cursos de flashcards |
| **🃏 Flashcards** | CRUD completo de tarjetas con pregunta y respuesta |
| **🎲 Estrategias de práctica** | Tres modalidades: secuencial, aleatoria y repetición espaciada |
| **📊 Barra de progreso** | Indicador visual que avanza según la estrategia seleccionada |
| **📈 Estadísticas** | Panel completo de estadísticas personales y globales |

### 🌟 Funcionalidades extra

| Característica | Descripción |
| -------------- | ----------- |
| **🏆 Ranking global de usuarios** | Sistema de clasificación por curso que muestra el porcentaje de aciertos de cada estudiante |

---

## 💻 Requisitos del sistema

- **Java 17** o superior
- **Maven 3.6+** para gestión de dependencias
- **Base de datos compatible con H2** (incluida en el proyecto)
- **Sistema operativo**: Windows, macOS o Linux

---

## 🚀 Cómo ejecutar la aplicación

### 1. Clonar el repositorio

```bash
git clone https://github.com/GonZaNiLo23/pds.git
cd .\pds\SistemaCursos\
```

### 2. Configuración del proyecto

1. **Abrir el proyecto** en tu IDE favorito (IntelliJ IDEA, Eclipse, NetBeans, etc.)
2. **Verificar Java 17**: Asegúrate de que el proyecto esté configurado con Java 17 o superior
3. **Dependencias Maven**: El IDE debería reconocer automáticamente el proyecto Maven y descargar las dependencias necesarias

### 3. Ejecutar la aplicación

1. **Localizar la clase principal**: Busca `Main.java` en el directorio `src/main/java/app/`
2. **Ejecutar**: Hacer clic derecho → "Run Main.main()" (o equivalente según tu IDE)
3. **Inicio**: La aplicación se iniciará mostrando la ventana de login

> **📝 Nota importante**: En la primera ejecución, la aplicación creará automáticamente la base de datos H2 como archivo `flashcardsdb.mv.db` en el directorio raíz del proyecto. El proyecto ya incluye una base de datos con cursos de ejemplo para facilitar las pruebas.

## 📁 Estructura del proyecto

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
## 🎮 Características destacadas del ranking

El **sistema de ranking por curso** es una funcionalidad extra:

- **📊 Clasificación automática**: Ordena automáticamente a los estudiantes por porcentaje de aciertos
- **🔄 Actualización en tiempo real**: Se actualiza después de cada sesión de práctica
- **🎯 Motivación competitiva**: Fomenta la participación activa y el esfuerzo continuo
- **📈 Progreso visible**: Permite a los estudiantes ver su evolución respecto a otros participantes
- **🏅 Reconocimiento**: Destaca a los mejores estudiantes de cada curso

