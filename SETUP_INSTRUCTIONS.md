**README - SDK de Culqi**

Este repositorio contiene el SDK de Culqi para Java, que facilita la integración de la plataforma de pagos Culqi en aplicaciones Java. Sigue los siguientes pasos para configurar y ejecutar el proyecto:


### Instalar Java
Descarga Spring Tools Suite 4-4.21.0 archivo del siguiente link:

```bash
    https://cdn.spring.io/spring-tools/release/STS4/4.21.0.RELEASE/dist/e4.30/spring-tool-suite-4-4.21.0.RELEASE-e4.30.0-linux.gtk.x86_64.tar.gz
```

### Instalar jdk (Kit de Desarrollo de Java)
Asegúrate de tener instalado el JDK 8 mediante los siguientes comandos:

```bash
sudo apt-get update
sudo apt-get install openjdk-8-jdk
```
### Configurar Variables de Entorno para Java y Maven
Edita el archivo .bashrc con el siguiente comando:

```bash
    #Abre el archivo .bashrc 
    nano ~/.bashrc
```
Añade las siguientes líneas al final del archivo:

```bash
    #Agrega las siguientes líneas al final del archivo:
    JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
    M2_HOME=/usr/share/maven
    PATH=$PATH:$JAVA_HOME/bin:$M2_HOME/bin
```
Guarda los cambios y cierra el editor (Ctrl + o >> Enter >> Ctrl + x).

### Instalar Maven
Instala Maven con el siguiente comando

```bash
sudo apt-get install maven
```


### Como abrir el archvio SDK

Abre Spring Tools Suite y selecciona:
```bash
    FILE >> IMPORT >> MAVEN >> EXISTING MAVEN PROJECTS
```

### Instalar Dependencias
Haz clic derecho en la carpeta raíz del proyecto y selecciona:

```bash
        RUN AS >> MAVEN CLEAN 
        RUN AS >> MAVEN INSTALL
```

### Ejecutar Tests
Haz clic derecho en la carpeta src/test/java y selecciona:

```bash
        RUN AS >> JUnit Test
```
Espera a que se ejecuten todas las pruebas unitarias y, luego, ejecuta cada prueba individualmente según las necesidades.

```bash
CulqiCreateTest:
    test05_createPlan
    test08_createSubscription

CulqiDeleteTest: 
    test01_deleteSubscription
    test02_deletePlan

CulqiGetTest:
    test06_findPlan
    test07_findSubscription

CulqiAllTest:
    test04_allPlan
    test06_allSubscriptions
```
Click derecho en el nombre del test_0** >> RUN

### Donde Encontrar los ejemplos para Pruebas
Dentro de la estructura del proyecto, encontrarás ejemplos de pruebas que puedes utilizar para verificar el funcionamiento del SDK. Sigue estos pasos para acceder y ejecutar los ejemplos:

# Ubicación de los Ejemplos:
* Haz clic derecho en la carpeta src/test/java.
* Abre el archivo CulqiCRUD.java.
* En la linea 27 puedes configurar el secreto:

```bash
    culqi.secret_key = "sk_live_************";
```

* Haz clic derecho en la carpeta src/test/java.
* Abre el archivo JsonData.java.

* Ejemplo de JSON:
En el archivo JsonData.java, encontrarás ejemplos de datos en formato JSON que se utilizan en las pruebas. Puedes modificar estos datos según tus necesidades.

```bash
php examples/plan/02-create-plan.php
    Crear Plan: jsonPlan
    Actualizar Plan: jsonUpdatePlan
    All Plan: jsonPlanFilter

    Crear Subscription: jsonSubscription
    Actualizar Subscription: jsonUpdateSubscription
    All Subscription: jsonListSubscriptions
```
Modifica estos ejemplos según tus necesidades y asegúrate de tener configuradas correctamente tus credenciales de Culqi antes de ejecutar las pruebas.


### Extension Java (Opcional)
Añade las siguientes extensiones a tu entorno de desarrollo para mejorar la productividad:
```bash
Project Manager for Java
Language Support for Java(TM) by Red Hat
```

```bash
Author: Brando Javier Carquin Mendocilla ... brando.carquin@culqi.com
```