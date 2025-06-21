# Identificación de Casos de Uso

| Actor   | Objetivo                             |
|---------|--------------------------------------|
| Usuario | Iniciar Curso                        |
| Usuario | Crear Curso (*)                      |
| Usuario | Publicar Curso (**)                  |
| Usuario | Guardar Estado del Curso             |
| Usuario | Reanudar Curso                       |
| Sistema | Guardar Estadísticas de Uso          |
| Usuario | Accede al Sistema                    |
| Usuario | Consultar Ranking de un Curso        |

> (*) El curso se guarda en el Sistema.  
> (**) Equivale a Compartir Curso

---

# Desarrollo de Casos de Uso (CDU's)

## Caso de Uso: “Iniciar Curso”

- **Actor Principal**: Usuario
- **Actores Involucrados y Objetivos**:

| Actor   | Objetivo                    |
|---------|-----------------------------|
| Usuario | Iniciar un Curso de la App  |

- **Precondiciones**:
  - El usuario tiene que haber iniciado sesión en el sistema
  - El curso elegido debe haber sido creado por otro usuario

- **Postcondiciones**:
  - El usuario está dado de alta en el curso

- **Flujo Básico**:
  1. Usuario busca la lista de cursos disponibles
  2. Usuario selecciona un curso de los disponibles
  3. Sistema guarda datos de registro y configuración de aprendizaje

- **Flujo Alternativo**:
  - 1a. Usuario elige un curso que ya ha iniciado anteriormente:
    - Se aplica CDU "Reanudar Curso"

- **Requisitos Especiales**: Ninguno  
- **Lista de Tecnología y Variaciones de Datos**: Ninguno  
- **Cuestiones Pendientes**: Ninguno

---

## Caso de Uso: “Crear Curso”

- **Actor Principal**: Usuario
- **Actores Involucrados y Objetivos**:

| Actor   | Objetivo                      |
|---------|-------------------------------|
| Usuario | Crear un Curso (y su contenido) |

- **Precondiciones**: Ninguna  
- **Postcondiciones**: Se ha guardado el nuevo curso en el sistema

- **Flujo Básico**:
  1. El Usuario selecciona la opción "Crear Curso"
  2. El Usuario selecciona "Añadir" para crear e introducir una nueva flashcard
  3. El sistema crea una nueva Flashcard
  4. El Usuario elige el Tipo de Ejercicio
  5. El Usuario completa el contenido de la Flashcard
  6. El Usuario finaliza la creación del ejercicio
  7. El Usuario repite los pasos del 2 al 6 hasta terminar
  8. Usuario elige la estrategia de aprendizaje
  9. El Usuario guarda el curso

- **Flujo Alternativo**: Ninguno  
- **Requisitos Especiales**:
  - El usuario interactúa con el sistema a través de la interfaz gráfica

- **Lista de Tecnología y Variaciones de Datos**: Ninguno  
- **Cuestiones Pendientes**: Ninguno

---

## Caso de Uso: “Publicar Curso”

- **Actor Principal**: Usuario
- **Actores Involucrados y Objetivos**:

| Actor   | Objetivo        |
|---------|-----------------|
| Usuario | Compartir Curso |

- **Precondiciones**: Ninguna  
- **Postcondiciones**: El curso creado debe ser público

- **Flujo Básico**:
  1. Un Usuario crea un curso nuevo propio
  2. El Sistema incluye el Curso en la biblioteca interna del sistema

- **Flujo Alternativo**: Ninguno  
- **Requisitos Especiales**: Ninguno  
- **Lista de Tecnología y Variaciones de Datos**:
  - El curso estará definido en un fichero JSON o YAML

- **Cuestiones Pendientes**: Ninguno

---

## Caso de Uso: “Guardar Estado del Curso”

- **Actor Principal**: Usuario
- **Actores Involucrados y Objetivos**:

| Actor   | Objetivo               |
|---------|------------------------|
| Usuario | Guardar estado del curso |

- **Precondiciones**:
  - El usuario tiene que comenzar/reanudar el curso

- **Postcondiciones**: Ninguna

- **Flujo Básico**:
  1. Usuario accede al sistema
  2. Usuario inicia o reanuda curso
  3. Usuario guarda estado del curso
  4. Sistema guarda datos de estado del curso

- **Flujo Alternativo**: Ninguno  
- **Requisitos Especiales**: Ninguno  
- **Lista de Tecnología y Variaciones de Datos**: Ninguno  
- **Cuestiones Pendientes**: Ninguno

---

## Caso de Uso: “Reanudar Curso”

- **Actor Principal**: Usuario
- **Actores Involucrados y Objetivos**:

| Actor   | Objetivo        |
|---------|-----------------|
| Usuario | Reanudar Curso  |

- **Precondiciones**: El usuario debe haber iniciado antes el curso  
- **Postcondiciones**: Al terminar el curso quedará en el estado que se dejó

- **Flujo Básico**:
  1. El Usuario accede al sistema
  2. El Usuario accede al listado de sus cursos
  3. El usuario reanuda el curso
  4. El Usuario elige la estrategia de aprendizaje
  5. El Usuario finaliza el curso
  6. El Usuario sale del curso

- **Flujos Alternativos**:
  - 1a. No ha iniciado el curso nunca antes:
    - El Usuario debe seleccionar otro curso para reanudar o iniciar uno nuevo
  - 3a. El curso reanudado ya está completado:
    - El sistema ofrece posibilidad de reiniciar curso al Usuario
  - 4a. El Usuario interrumpe el curso:
    - CDU: "Guardar Estado del Curso"
  - 5a. El Usuario repite el curso:
    - El Usuario comienza el curso desde el principio

- **Requisitos Especiales**: Ninguno  
- **Lista de Tecnología y Variaciones de Datos**: Ninguno  
- **Cuestiones Pendientes**: Ninguno

---

## Caso de Uso: “Guardar Estadísticas de Uso”

- **Actor Principal**: Sistema
- **Actores Involucrados y Objetivos**:

| Actor   | Objetivo                                           |
|---------|----------------------------------------------------|
| Sistema | Guardar las estadísticas de uso de cada usuario    |

- **Precondiciones**: Se debe avanzar en el curso  
- **Postcondiciones**: No especificadas

- **Flujo Básico**:
  1. Usuario Inicia/Reanuda curso
  2. Usuario avanza en el curso
  3. Sistema actualiza y guarda automáticamente los datos de uso del usuario
  4. Se repiten los pasos 2 y 3 si es el caso

- **Flujo Alternativo**: Ninguno  
- **Requisitos Especiales**: Ninguno  
- **Lista de Tecnología y Variaciones de Datos**:
  - Se almacena información relacionada con la fecha de uso y los aciertos

- **Cuestiones Pendientes**: Ninguno

---

## Caso de Uso: “Accede al Sistema”

- **Actor Principal**: Usuario
- **Actores Involucrados y Objetivos**:

| Actor   | Objetivo          |
|---------|-------------------|
| Usuario | Utilizar el Sistema |

- **Precondiciones**:
  - El usuario debe estar previamente registrado en el Sistema

- **Postcondiciones**: Ninguna

- **Flujo Básico**:
  1. Usuario Inicia Sesión en el Sistema introduciendo sus credenciales
  2. Usuario lleva a cabo acciones dentro del sistema
  3. Usuario Cierra Sesión en el Sistema

- **Flujos Alternativos**:
  - 1a. El Usuario no está registrado en el Sistema:
    - Usuario se registra en el sistema con sus datos
    - 1ab. El Usuario utiliza un correo ya existente:
      - Sistema indica que el correo introducido no es válido
      - Se repite paso 1a

- **Requisitos Especiales**:
  - El Usuario debe disponer de una dirección de Email
  - La contraseña debe cumplir unas normas

- **Lista de Tecnología y Variaciones de Datos**:
  - Datos requeridos: Nombre, Email, Contraseña
  - Cierre de sesión desde interfaz gráfica (botón)

- **Cuestiones Pendientes**: Ninguno

---

## Caso de Uso: “Consultar Ranking de un Curso”

- **Actor Principal**: Usuario
- **Actores Involucrados y Objetivos**:

| Actor   | Objetivo                                                                 |
|---------|--------------------------------------------------------------------------|
| Usuario | Observar posiciones de los usuarios según porcentaje de acierto en curso |

- **Precondiciones**: El Usuario debe haber participado en el curso  
- **Postcondiciones**: Ninguna

- **Flujo Básico**:
  1. Usuario accede al sistema exitosamente
  2. Usuario accede al listado de cursos
  3. Usuario accede al ranking de un curso
  4. El Sistema procesa la orden y muestra el ranking

- **Flujo Alternativo**: Ninguno  
- **Requisitos Especiales**: Ninguno  
- **Lista de Tecnología y Variaciones de Datos**:
  - Información mostrada: `{Posición; Alumno; % acierto}`

- **Cuestiones Pendientes**: Ninguno
