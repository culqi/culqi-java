# Culqi Java

[![License](https://poser.pugx.org/culqi/culqi-php/license)](https://packagist.org/packages/culqi/culqi-php)
[![Code Climate](https://codeclimate.com/github/culqi/culqi-java/badges/gpa.svg)](https://codeclimate.com/github/culqi/culqi-java)
[![Build Status](https://travis-ci.org/culqi/culqi-java.svg?branch=master)](https://travis-ci.org/culqi/culqi-java)

Biblioteca de CULQI para el lenguaje Java, pagos simples en tu sitio web. Consume el Culqi API.

| Versión actual|Culqi API|
|----|----|
| 1.1.1 (2017-01-17) |[v2](https://beta.culqi.com)|

## Requisitos

- Java 1.7+
- Credenciales de comercio en Culqi (1).

## Ejemplos

#### Imports

```java
import com.culqi.Culqi;
import com.culqi.model.*;
import com.culqi.util.*;
```

#### Inicialización

```java
Security culqi = new Culqi().init("pk_test_vzMuTHoueOMlgUPj","sk_test_UTCQSGcXW8bCyU59");
```

#### Crear Token

```java
protected Map<String, Object> token() throws Exception {
    Token token = new Token();
    token.setCard_number("4111111111111111");
    token.setCurrency_code("PEN");
    token.setCvv("123");
    token.setExpiration_month(9);
    token.setExpiration_year(2020);
    token.setFingerprint("q352454534");
    token.setFirst_name("Willy");
    token.setLast_name("Aguirre");
    token.setEmail("waguirre@me.com");
    return token.create(culqi);
}

System.out.println( token().get("id").toString() );
```

#### Crear Cargo

```java
protected Map<String, Object> charge() throws Exception {
    Charge charge = new Charge();
    charge.setAddress("Avenida Lima 1232");
    charge.setAddress_city("LIMA");
    charge.setAmount(1000);
    charge.setCountry_code("PE");
    charge.setCurrency_code("PEN");
    charge.setEmail("waguirre@me.com");
    charge.setFirst_name("Willy");
    charge.setInstallments(0);
    charge.setLast_name("Aguirre");
    charge.setMetadata("");
    charge.setPhone_number(3333339);
    charge.setProduct_description("Venta de prueba");
    charge.setToken_id(token().get("id").toString());
    return charge.create(culqi);
}

System.out.println( charge().get("id").toString() );
```

#### Crear Plan

```java
rotected Map<String, Object> plan() throws Exception {
    Plan plan = new Plan();
    plan.setAlias("plan-"+new Util().ramdonString());
    plan.setAmount(1000);
    plan.setCurrency_code("PEN");
    plan.setInterval("day");
    plan.setInterval_count(2);
    plan.setLimit(10);
    plan.setName("Plan "+new Util().ramdonString());
    plan.setTrial_days(30);
    return plan.create(culqi);
}

System.out.println( plan().get("alias").toString() );
```

#### Crear Suscripción

```java
protected Map<String, Object> subscription() throws Exception {
    Subscription subscription = new Subscription();
    subscription.setAddress("Avenida Lima 123213");
    subscription.setAddress_city("LIMA");
    subscription.setCountry_code("PE");
    subscription.setEmail("waguirre@me.com");
    subscription.setLast_name("Aguirre");
    subscription.setFirst_name("Willy");
    subscription.setPhone_number(1234567789);
    subscription.setPlan_alias(plan().get("alias").toString());
    subscription.setToken_id(token().get("id").toString());
    return subscription.create(culqi);
}

System.out.println( subscription().get("id").toString() );
```

#### Crear Devolución

```java
protected Map<String, Object> refund() throws Exception {
    Refund refund = new Refund();
    refund.setAmount(900);
    refund.setCharge_id(charge().get("id").toString());
    refund.setReason("give me my money back!");
    return refund.create(culqi);
}

System.out.println( refund().get("id").toString() );
```

## Changelog

Todos los cambios en las versiones de esta biblioteca están listados en [CHANGELOG](CHANGELOG).

## Dependencias para el desarrollo

- [Lombok](https://projectlombok.org/features/index.html)
- [Apache HTTP Components](https://hc.apache.org/)
- [Jackson Core Databind](https://github.com/FasterXML/jackson-databind/wiki)

## Testing

Debes tener instalado Maven para poder ejecutar los tests

```bash
mvn test
```

Puede ejecutar estos unitarios independientemente

```bash
mvn test -D test=CulqiTest#test1ValidCreateToken
mvn test -D test=CulqiTest#test2ValidCreateCharge
mvn test -D test=CulqiTest#test3ValidCreatePlan
mvn test -D test=CulqiTest#test4ValidCreateSubscription
mvn test -D test=CulqiTest#test5ValidCreateRefund
```

## Autor

Willy Aguirre ([@marti1125](https://github.com/marti1125) - Team Culqi)

## Licencia

El código fuente de culqi-java está distribuido bajo MIT License, revisar el archivo [LICENSE](https://github.com/culqi/culqi-java/blob/master/LICENSE).
