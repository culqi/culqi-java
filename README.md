# Culqi Java

[![Code Climate](https://codeclimate.com/github/culqi/culqi-java/badges/gpa.svg)](https://codeclimate.com/github/culqi/culqi-java)
[![Build Status](https://travis-ci.org/culqi/culqi-java.svg?branch=master)](https://travis-ci.org/culqi/culqi-java)

Biblioteca de CULQI para el lenguaje Java, pagos simples en tu sitio web. Consume el Culqi API.

| Versión actual|Culqi API|
|----|----|
| forjava1.6 (2017-05-03) |[v2](https://culqi.com/api/)|

## Requisitos

- Java 1.6+
- Credenciales de comercio en Culqi (1).

## Ejemplos


#### Inicialización

```java
Culqi culqi = new Culqi();
culqi.public_key = "{LLAVE PUBLICA}";
culqi.secret_key =  "{LLAVE SECRETA}"
```

#### Crear Token

```java
Map<String, Object> token = new HashMap<String, Object>();
token.put("card_number", "4111111111111111");
token.put("cvv", "123");
token.put("email", "wm@wm.com");
token.put("expiration_month", 9);
token.put("expiration_year", 2020);
Map<String, Object> token_created = culqi.token.create(token);

```

#### Crear Cargo

```java
Map<String, Object> charge = new HashMap<String, Object>();
Map<String, Object> metadata = new HashMap<String, Object>();
metadata.put("oder_id", "124");
charge.put("amount",1000);
charge.put("capture", true);
charge.put("currency_code",CurrencyCode.PEN);
charge.put("description","Venta de prueba");
charge.put("email","test@culqi.com");
charge.put("installments", 0);
charge.put("metadata", metadata);
charge.put("source_id", token_created.get("id").toString());
Map<String, Object> charge_created = culqi.charge.create(charge);

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
Map<String, Object> plan_created =  culqi.plan.create(plan);
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
Map<String, Object> customer_created = culqi.customer.create(customer);
```

#### Crear Tarjeta

```java
Map<String, Object> card = new HashMap<String, Object>();
card.put("customer_id",customer_created.get("id").toString());
card.put("token_id",token_created.get("id").toString());
Map<String, Object> card_created = culqi.card.create(card);
```


#### Crear Suscripción

```java
Map<String, Object> subscription = new HashMap<String, Object>();
subscription.put("card_id",card_created.get("id").toString());
subscription.put("plan_id",plan_created.get("id").toString());
Map<String, Object> suscription_created = culqi.subscription.create(subscription);
```

#### Crear Devolución

```java
Map<String, Object> refund = new HashMap<String, Object>();
refund.put("amount",900);
refund.put("charge_id",charge_created.get("id").toString());
refund.put("reason",Reason.solicitud_comprador);
Map<String, Object> refund_created = culqi.refund.create(refund);
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
mvn install:install-file -Dfile={dir}/culqi-java-forjava1.6.jar  -DgroupId=com.culqi -DartifactId=culqi-java -Dversion={version} -Dpackaging=jar
```


Luego agregas la siguiente dependencia en el pom.xml

```xml
<dependency>
    <groupId>com.culqi</groupId>
    <artifactId>culqi-java</artifactId>
    <version>forjava1.6</version>
</dependency>
```

## Autor

Willy Aguirre ([@marti1125](https://github.com/marti1125) - Team Culqi)

## Licencia

El código fuente de culqi-java está distribuido bajo MIT License, revisar el archivo [LICENSE](https://github.com/culqi/culqi-java/blob/master/LICENSE).
