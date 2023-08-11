# Culqi Java

[![Code Climate](https://codeclimate.com/github/culqi/culqi-java/badges/gpa.svg)](https://codeclimate.com/github/culqi/culqi-java)
[![Build Status](https://travis-ci.org/culqi/culqi-java.svg?branch=master)](https://travis-ci.org/culqi/culqi-java)

Nuestra Biblioteca Culqi-Java oficial, es compatible con la [v2.0](https://culqi.com/api/) del Culqi API, con el cual tendrás la posibilidad de realizar cobros con tarjetas de débito y crédito, Yape, PagoEfectivo, billeteras móviles y Cuotéalo con solo unos simples pasos de configuración.

| Versión actual|Culqi API|
|----|----|
| 3.0.0  |[v2.0](https://culqi.com/api/)|

## Requisitos

- Java 1.7+
- Afiliate [aquí](https://afiliate.culqi.com/).
- Si vas a realizar pruebas obtén tus llaves desde [aquí](https://integ-panel.culqi.com/#/registro), si vas a realizar transacciones reales obtén tus llaves desde [aquí](https://mipanel.culqi.com/#/registro).

> Recuerda que para obtener tus llaves debes ingresar a tu CulqiPanel > Desarrollo > ***API Keys***.

![alt tag](http://i.imgur.com/NhE6mS9.png)

> Recuerda que las credenciales son enviadas al correo que registraste en el proceso de afiliación.

## Instalación

Instalación usando Maven:

Solo necesita agregar el siguiente repositorio en el archivo pom.xml

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

Luego agregar la dependencia:

```xml
<dependency>
    <groupId>com.github.culqi</groupId>
    <artifactId>culqi-java</artifactId>
    <version>v1.1.9</version>
</dependency>
```

## Ejemplos

#### Inicialización

```java
Culqi culqi = new Culqi();
culqi.public_key = "{LLAVE PUBLICA}";
culqi.secret_key =  "{LLAVE SECRETA}"
```

## Encriptar payload

Para encriptar el payload necesitas crear un id RSA y llave RSA, para esto debes ingresa a tu panel y hacer click en la sección “Desarrollo / RSA Keys” de la barra de navegación a la mano izquierda.

Luego declara en variables el id RSA y llave RSA en tu backend, y envialo en las funciones de la librería.

Ejemplo

```java
String rsaPublicKey = "la llave pública RSA";
String rsaId = "el id de tu llave"

return init().token.create(jsondata.jsonToken(), rsaPublicKey, rsaId);
```

#### Crear Token

```java
Map<String, Object> token = new HashMap<String, Object>();
token.put("card_number", "4111111111111111");
token.put("cvv", "123");
token.put("email", "wm@wm.com");
token.put("expiration_month", 9);
token.put("expiration_year", 2020);
ResponseCulqi response = culqi.token.create(token);

```

#### Crear Cargo

```java
Map<String, Object> charge = new HashMap<String, Object>();
Map<String, Object> antifraudDetails = new HashMap<String, Object>();
antifraudDetails.put("address", "Calle Narciso de Colina 421 Miraflores");
antifraudDetails.put("address_city", "LIMA");
antifraudDetails.put("country_code", "PE");
antifraudDetails.put("first_name", "Willy");
antifraudDetails.put("last_name", "Aguirre");
antifraudDetails.put("phone_number", "012767623");
Map<String, Object> metadata = new HashMap<String, Object>();
metadata.put("oder_id", "124");
charge.put("amount",1000);
charge.put("capture", true);
charge.put("currency_code",CurrencyCode.PEN);
charge.put("description","Venta de prueba");
charge.put("email","test@culqi.com");
charge.put("installments", 0);
charge.put("antifraud_details", antifraudDetails);
charge.put("metadata", metadata);
charge.put("source_id", token_created.get("id").toString());
ResponseCulqi response = culqi.charge.create(charge);

```

#### Crear Plan

```java
Map<String, Object> plan = new HashMap<String, Object>();
Map<String, Object> metadata = new HashMap<String, Object>();
metadata.put("oder_id", "124");
plan.put("amount",1000);
plan.put("currency_code",CurrencyCode.PEN);
plan.put("interval","dias");
plan.put("interval_count",30);
plan.put("limit", 4);
plan.put("metadata", metadata);
plan.put("name", "plan-test");
plan.put("trial_days", 15);
ResponseCulqi response =  culqi.plan.create(plan);
```

#### Crear Cliente

```java
Map<String, Object> customer = new HashMap<String, Object>();
customer.put("address","Av Lima 123");
customer.put("address_city","Lima");
customer.put("country_code","PE");
customer.put("email","tst@culqi.com");
customer.put("first_name","Test");
customer.put("last_name","Cuqli");
customer.put("phone_number",99004356);
ResponseCulqi response = culqi.customer.create(customer);
```

#### Crear Tarjeta

```java
Map<String, Object> card = new HashMap<String, Object>();
card.put("customer_id",customer_created.get("id").toString());
card.put("token_id",token_created.get("id").toString());
ResponseCulqi response = culqi.card.create(card);
```


#### Crear Suscripción

```java
Map<String, Object> subscription = new HashMap<String, Object>();
subscription.put("card_id",card_created.get("id").toString());
subscription.put("plan_id",plan_created.get("id").toString());
ResponseCulqi response = culqi.subscription.create(subscription);
```

#### Crear Devolución

```java
Map<String, Object> refund = new HashMap<String, Object>();
refund.put("amount",900);
refund.put("charge_id",charge_created.get("id").toString());
refund.put("reason",Reason.solicitud_comprador);
ResponseCulqi response = culqi.refund.create(refund);
```

## Documentación
¿Necesitas más información para integrar `culqi-java`? La documentación completa se encuentra en [https://culqi.com/docs/](https://culqi.com/docs/)


## Changelog

Todos los cambios en las versiones de esta biblioteca están listados en [CHANGELOG](CHANGELOG).

## Dependencias para el desarrollo

- [okhttp3](http://square.github.io/okhttp/)
- [Jackson Core Databind](https://github.com/FasterXML/jackson-databind/wiki)

## Build

```bash
mvn package -DskipTests
```

## Testing

Debes tener instalado Maven para poder ejecutar los tests

```bash
mvn test
```

Puede ejecutar estos unitarios independientemente

```bash
mvn test -D test=CulqiCreateTest#test1ValidCreateToken
mvn test -D test=CulqiCreateTest#test2ValidCreateCharge
mvn test -D test=CulqiCreateTest#test3ValidCreatePlan
mvn test -D test=CulqiCreateTest#test4ValidCreateCustomer
mvn test -D test=CulqiCreateTest#test5ValidCreateCard
mvn test -D test=CulqiCreateTest#test6ValidCreateSubscription
mvn test -D test=CulqiCreateTest#test7ChargeCapture
```

## ¿Cómo instalar el jar de Culqi en un proyecto Maven? 

```bash
mvn install:install-file -Dfile={dir}/culqi-java-1.1.8.jar  -DgroupId=com.culqi -DartifactId=culqi-java -Dversion={version} -Dpackaging=jar
```


Luego agregas la siguiente dependencia en el pom.xml

```xml
<dependency>
    <groupId>com.culqi</groupId>
    <artifactId>culqi-java</artifactId>
    <version>{version}</version>
</dependency>
```

## Autor

Team Culqi

## Licencia

El código fuente de culqi-java está distribuido bajo MIT License, revisar el archivo [LICENSE](https://github.com/culqi/culqi-java/blob/master/LICENSE).
