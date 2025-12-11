--------

# Examen de Automatizacion

Este proyecto es un framework de automatización de pruebas móviles (Android) diseñado para validar el flujo de compra de la aplicación **"My Demo App"** de Sauce Labs.

El proyecto utiliza **Appium** con **Cucumber (BDD)** y sigue el patrón de diseño **Page Object Model (POM)** para garantizar la mantenibilidad y escalabilidad del código.

--------

##  Stack

* **Lenguaje:** Java 17
* **Framework de Pruebas:** Cucumber 7 (Gherkin syntax)
* **Motor de Automatización:** Appium Java Client 8.x
* **Runner:** JUnit 4
* **Build Tool:** Maven
* **IDE Recomendado:** IntelliJ IDEA

--------

## Características Principales

1. **Page Object Model (POM):** Lógica de interacción separada de los pasos de prueba (`view` vs `step`).
2. **Manejo Inteligente de Scroll:** Implementación de `UiScrollable` para interactuar con elementos fuera de la pantalla (ej. "Bike Light").
3. **Estrategia "Full Reset":** Configuración robusta en `DesiredCapsFactory` que desinstala y reinstala la app entre escenarios para garantizar un estado limpio (evitando acumulación de datos en el carrito).
4. **Captura de Evidencias:** Sistema de Hooks (`@After`) que toma una captura de pantalla (Screenshot) automáticamente cuando un escenario falla.
5. **Validación de Bugs:** Lógica asertiva diseñada para detectar y reportar errores conocidos de la aplicación (ej. Bug de cantidad en la Camiseta).

--------

## 📂 Estructura del Proyecto

```text
src/test/java/com/test/mobile

├── hooks
│   └── Hook.java                 # Setup, Teardown y Screenshots on Failure
├── runner
│   └── cucumberRunner.java       # Ejecutor de los tests
├── step
│   └── CartSteps.java            # Definición de pasos (Given, When, Then)
└── view
    ├── ProductListPage.java      # Lógica de la lista de productos (con Scroll)
    ├── ProductDetailsPage.java   # Lógica de detalle y agregar al carrito
    └── CartPage.java             # Lógica de validación dentro del carrito
```

```text
src/main/java/com/test/mobile/
├── config
│   ├── MobileDriverManager.java  # Singleton para el driver
│   └── DesiredCapsFactory.java   # Configuración de Capabilities (Android/iOS)

```

--------

## Prerrequisitos

Antes de ejecutar, asegúrate de tener instalado:

1. **Java JDK 17+** y variable `JAVA_HOME` configurada.
2. **Android Studio** y SDK Tools.
3. **Appium Server** (corriendo en puerto `4723`).
4. **Emulador Android** configurado (Recomendado: Pixel 4 o superior, API 30+).
5. **APK de la App:** Ubicado en `src/test/resources/app/android/mda-2.0.2-23.apk`.

--------

## Configuración y Ejecución

### 0. Configurar ruta del APK (Importante)

Para que Appium encuentre la aplicación en tu entorno local, debes actualizar el archivo de propiedades:

1.  Ve al archivo: `src/test/resources/config/android.properties`
2.  Edita la **línea 3** (`app=`) y coloca la ruta absoluta o relativa donde se encuentra el APK en tu PC.

<!-- end list -->

```properties
# Ejemplo en android.properties
app=C:/Usuarios/TuUsuario/Descargas/mda-2.0.2-23.apk
# O si está dentro del proyecto:
app=src/test/resources/app/android/mda-2.0.2-23.apk
```

### 1. Iniciar Appium Server

Abre una terminal o Appium Desktop y ejecuta:

```bash
appium -p 4723
```

### 2. Iniciar el Emulador

Abre tu dispositivo virtual desde Android Studio (AVD Manager). Asegúrate de que esté desbloqueado.

### 3. Ejecutar los Tests

Puedes ejecutar los tests directamente desde IntelliJ haciendo clic derecho en `cucumberRunner.java` -> **Run**, o mediante línea de comandos con Maven:

```bash
mvn clean test
```

--------

## Reporte de Bugs Detectados

Durante la ejecución de la suite `@Regresion`, se valida el comportamiento de la aplicación. Actualmente, el framework detecta el siguiente comportamiento anómalo:

| Producto | Comportamiento Esperado | Comportamiento Actual | Resultado del Test |
|:---------|:------------------------|:----------------------|:-------------------|
| **Sauce Labs Backpack** | Agregar 1 unidad. | Agrega 1 unidad. | ✅ **PASSED** |
| **Sauce Labs Bolt T-Shirt** | Agregar 1 unidad. | **Agrega 10 unidades** (Bug de la App). | ❌ **FAILED** (Correcto) |
| **Sauce Labs Bike Light** | Agregar 2 unidades. | Agrega 2 unidades (Requiere Scroll). | ✅ **PASSED** |

> **Nota:** El fallo en el escenario de la "Bolt T-Shirt" es intencional y confirma que la automatización está detectando correctamente el defecto de software.

--------

## Evidencias

Las capturas de pantalla de los errores se adjuntan automáticamente al reporte de Cucumber al finalizar la ejecución.

--------

**Autor:** Leonardo Reascos
**Fecha:** Diciembre 2025

--------
