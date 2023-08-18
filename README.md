# Culqi Java

[![Code Climate](https://codeclimate.com/github/culqi/culqi-java/badges/gpa.svg)](https://codeclimate.com/github/culqi/culqi-java)
[![Build Status](https://travis-ci.org/culqi/culqi-java.svg?branch=master)](https://travis-ci.org/culqi/culqi-java)

Nuestra Biblioteca JAVA oficial de CULQI, es compatible con la [v2.0](https://culqi.com/api/) del Culqi API, con el cual tendrás la posibilidad de realizar cobros con tarjetas de débito y crédito, Yape, PagoEfectivo, billeteras móviles y Cuotéalo con solo unos simples pasos de configuración.

Nuestra biblioteca te da la posibilidad de capturar el `status_code` de la solicitud HTTP que se realiza al API de Culqi, así como el `response` que contiene el cuerpo de la respuesta obtenida.


| Versión actual|Culqi API|
|----|----|
| 2.0.0  |[v2.0](https://culqi.com/api/)|


## Requisitos

- Java 1.7+
- Afiliate [aquí](https://afiliate.culqi.com/).
- Si vas a realizar pruebas obtén tus llaves desde [aquí](https://integ-panel.culqi.com/#/registro), si vas a realizar transacciones reales obtén tus llaves desde [aquí](https://panel.culqi.com/#/registro).

> Recuerda que para obtener tus llaves debes ingresar a tu CulqiPanel > Desarrollo > ***API Keys***.

![alt tag](http://i.imgur.com/NhE6mS9.png)

> Recuerda que las credenciales son enviadas al correo que registraste en el proceso de afiliación.

* Para encriptar el payload debes generar un id y llave RSA  ingresando a CulqiPanel > Desarrollo  > RSA Keys.

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

## Configuracion

Para empezar a enviar peticiones al API de Culqi debes configurar tu llave pública (pk), llave privada (sk).
Para habilitar encriptación de payload debes configurar tu rsa_id y rsa_public_key.

```java
culqi.public_key = "pk_test_889113cd74ecfc55";
culqi.secret_key = "sk_test_LoSAl6rqTInlzPSJ";
String rsaPublicKey = "-----BEGIN PUBLIC KEY-----\n"
			+ "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDADka0Pt4SuWlHRA6kcJIwDde\n"
			+ "o67OYBEgQDEelmmixs9AlB/1bv446XOOE8eTJSridll2ZAn2nze7Gl2vQs0yW+4A\n"
			+ "XmszJwugM0lxTDiPdTXdbrA4VXiXDG29VLQCAxt1+/c7bE84hMS6cymWgEjYoa6I\n"
			+ "xX8u0ncLyiRUdZC2cwIDAQAB\n"
			+ "-----END PUBLIC KEY-----";
	
Sring rsaId = "5243bad7-1d88-49c0-9699-f8ae156da58f"; 
	
JsonData jsondata = new JsonData();
```

### Encriptar payload

Para encriptar el payload necesitas pasar como parámetro el rsaPublicKey y rsaId.

Ejemplo

```java
protected Map<String, Object> createTokenEncrypt() throws Exception {
    return init().token.create(jsondata.jsonToken(), rsaPublicKey, rsaId);
}
```


## Ejemplos

#### Crear Token

```java
 protected Map<String, Object> createToken() throws Exception {
    return init().token.create(jsondata.jsonToken());
 }
```

#### Crear Cargo

```java
protected Map<String, Object> createCharge() throws Exception {
   String source_id = createToken().get("id").toString();
   return init().charge.create(jsondata.jsonCharge(source_id));
}
```

#### Crear Plan

```java
protected Map<String, Object> createPlan() throws Exception {
   return init().plan.create(jsondata.jsonPlan());
}
```

#### Crear Cliente

```java
 protected Map<String, Object> createCustomer() throws Exception {
    return init().customer.create(jsondata.jsonCustomer());
 }
```

#### Crear Tarjeta

```java
protected Map<String, Object> createCard() throws Exception {
   String customer_id = createCustomer().get("id").toString();
   String token_id = createToken().get("id").toString();
   return init().card.create(jsondata.jsonCard(customer_id,token_id));
}
```


#### Crear Suscripción

```java
 protected Map<String, Object> createSubscription() throws Exception {
    String card_id = createCard().get("id").toString();
    String plan_id = createPlan().get("id").toString();
    return init().subscription.create(jsondata.jsonSubscription(card_id, plan_id));
 }
```

#### Crear Devolución

```java
protected Map<String, Object> createRefund() throws Exception {
   String charge_id = createCharge().get("id").toString();
   return init().refund.create(jsondata.jsonRefund(charge_id));
}
```

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

### Ejemplo Prueba Token

```java
@Test
public void test01_createToken() throws Exception {
    culqiCRUD.createToken().get("object").toString();
    assertEquals("token", culqiCRUD.createToken().get("object").toString());
}
```

### Ejemplo Prueba Cargo
```java
@Test
public void test04_createCharge() throws Exception {
    assertEquals("charge", culqiCRUD.createCharge().get("object").toString());
}
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

## Documentación

- [Demo Checkout V4 + Culqi 3DS](https://github.com/culqi/culqi-java-demo-checkoutv4-culqi3ds)
- [okhttp3](http://square.github.io/okhttp/)
- [Jackson Core Databind](https://github.com/FasterXML/jackson-databind/wiki)


## Changelog

Todos los cambios en las versiones de esta biblioteca están listados en [CHANGELOG](CHANGELOG).

## Autor

Team Culqi

## Licencia

El código fuente de culqi-java está distribuido bajo MIT License, revisar el archivo [LICENSE](https://github.com/culqi/culqi-java/blob/master/LICENSE).
