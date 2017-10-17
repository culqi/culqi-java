# Culqi Java

[![Code Climate](https://codeclimate.com/github/culqi/culqi-java/badges/gpa.svg)](https://codeclimate.com/github/culqi/culqi-java)
[![Build Status](https://travis-ci.org/culqi/culqi-java.svg?branch=master)](https://travis-ci.org/culqi/culqi-java)

Library of CULQI for the Java language, simple payments in your website. Use the Culqi API.

| Current version |Culqi API|
|----|----|
| 1.1.9 (2017-05-31) |[v2](https://culqi.com/api/)|

## Requirements

- Java 1.7+
- Trade credentials in Culqi (1).

## Installation

Installation using Maven:
Just need to add the following repository in the pom.xml

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

Then add the dependency

```xml
<dependency>
    <groupId>com.github.culqi</groupId>
    <artifactId>culqi-java</artifactId>
    <version>v1.1.9</version>
</dependency>
```

## Examples

#### Initialization

```java
Culqi culqi = new Culqi();
culqi.public_key = "{PUBLIC_KEY}";
culqi.secret_key =  "{SECRET_KEY}"
```

#### Create Token

```java
Map<String, Object> token = new HashMap<String, Object>();
token.put("card_number", "4111111111111111");
token.put("cvv", "123");
token.put("email", "wm@wm.com");
token.put("expiration_month", 9);
token.put("expiration_year", 2020);
Map<String, Object> token_created = culqi.token.create(token);

```

#### Create Charge

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
charge.put("description","Sale Test");
charge.put("email","test@culqi.com");
charge.put("installments", 0);
charge.put("antifraud_details", antifraudDetails);
charge.put("metadata", metadata);
charge.put("source_id", token_created.get("id").toString());
Map<String, Object> charge_created = culqi.charge.create(charge);

```

#### Create Plan

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

#### Create Client

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

#### Create Card

```java
Map<String, Object> card = new HashMap<String, Object>();
card.put("customer_id",customer_created.get("id").toString());
card.put("token_id",token_created.get("id").toString());
Map<String, Object> card_created = culqi.card.create(card);
```


#### Create Subscription

```java
Map<String, Object> subscription = new HashMap<String, Object>();
subscription.put("card_id",card_created.get("id").toString());
subscription.put("plan_id",plan_created.get("id").toString());
Map<String, Object> suscription_created = culqi.subscription.create(subscription);
```

#### Create Refund

```java
Map<String, Object> refund = new HashMap<String, Object>();
refund.put("amount",900);
refund.put("charge_id",charge_created.get("id").toString());
refund.put("reason",Reason.solicitud_comprador);
Map<String, Object> refund_created = culqi.refund.create(refund);
```

## Documentation
Do you need more info about integration `culqi-java` The complete documentation is in [https://culqi.com/docs/](https://culqi.com/docs/)


## Changelog

All changes in the version of this library are listed in [CHANGELOG](CHANGELOG).

## Dependence for the development

- [okhttp3](http://square.github.io/okhttp/)
- [Jackson Core Databind](https://github.com/FasterXML/jackson-databind/wiki)

## Build

```bash
mvn package -DskipTests
```

## Testing

You must have installed Maven to run the tests

```bash
mvn test
```

You can run these units independently

```bash
mvn test -D test=CulqiCreateTest#test1ValidCreateToken
mvn test -D test=CulqiCreateTest#test2ValidCreateCharge
mvn test -D test=CulqiCreateTest#test3ValidCreatePlan
mvn test -D test=CulqiCreateTest#test4ValidCreateCustomer
mvn test -D test=CulqiCreateTest#test5ValidCreateCard
mvn test -D test=CulqiCreateTest#test6ValidCreateSubscription
mvn test -D test=CulqiCreateTest#test7ChargeCapture
```

## How install the Culqi's jar in a Maven project?

```bash
mvn install:install-file -Dfile={dir}/culqi-java-1.1.8.jar  -DgroupId=com.culqi -DartifactId=culqi-java -Dversion={version} -Dpackaging=jar
```

The add the following dependency in the pom.xml

```xml
<dependency>
    <groupId>com.culqi</groupId>
    <artifactId>culqi-java</artifactId>
    <version>{version}</version>
</dependency>
```

## Author

Willy Aguirre ([@marti1125](https://github.com/marti1125) - Team Culqi)

## License

The source code of culqi-java is distribuited under MIT License, check the file [LICENSE](https://github.com/culqi/culqi-java/blob/master/LICENSE).
