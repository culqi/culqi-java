# Culqi Java

[![License](https://poser.pugx.org/culqi/culqi-php/license)](https://packagist.org/packages/culqi/culqi-php)
[![Latest Unstable Version](https://poser.pugx.org/culqi/culqi-php/v/unstable)](https://packagist.org/packages/culqi/culqi-php)
[![Build Status](https://travis-ci.org/culqi/culqi-java.svg?branch=master)](https://travis-ci.org/culqi/culqi-java)

Biblioteca de CULQI para el lenguaje Java, pagos simples en tu sitio web. Consume el Culqi API.

| Versión actual|Culqi API|
|----|----|
| 0.1.0 (2017-01-04) |[v2](https://beta.culqi.com)|

## Requisitos

- Java 1.7+
- Credenciales de comercio en Culqi (1).

## Ejemplo

```java
import com.culqi.Culqi;
import com.culqi.util.Result;
import com.culqi.util.Util;

public class Main {

    public static void main(String[] args) throws Exception {

        Culqi culqi = new Culqi("pk_test_vzMuTHoueOMlgUPj","sk_test_UTCQSGcXW8bCyU59");
        Result resultToken = culqi.createToken("4111111111111111","PEN", "123",9,2020,"q352454534",
                "Aguirre","waguirre@me.com", "Willy");

        System.out.println(resultToken.getId());

        Result resultCharge = culqi.createCharge("Avenida Lima 1232", "LIMA", 1000, "PE", "PEN", "123", "waguirre@me.com",
                "Willy", 0, "Aguirre", "", new Util().ramdomNumber(), 3333339, "Venta de prueba", resultToken.getId());

        System.out.println("Charge " + resultCharge.getId());

        Result resultAlias = culqi.createPlan("plan-"+new Util().ramdonString(), 1000, "PEN", "day", 2,10,
                "Plan "+new Util().ramdonString(), 30);

        System.out.println("Plan Alias: " + resultAlias.getMessage());

        Result resultSubscription = culqi.createSubscription("Avenida Lima 123213", "LIMA", "PE", "waguirre@me.com", "Aguirre",
                "Willy", 1234567789, resultAlias.getMessage(), resultToken.getId());

        System.out.println(resultSubscription.getId());

        Result resultRefund = culqi.createRefund(900, resultCharge.getId(), "bought an incorrect product");

        System.out.println(resultRefund.getMessage());

    }

}
```
## Changelog

Todos los cambios en las versiones de esta biblioteca están listados en [CHANGELOG](CHANGELOG).

## Dependencias para el desarrollo

- [Lombok](https://projectlombok.org/features/index.html)
- [Apache HTTP Components](https://hc.apache.org/)
- [Jackson Core Databind](https://github.com/FasterXML/jackson-databind/wiki)

## Build

```bash
mvn package
```

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

El orden de los test

## Autor

Willy Aguirre ([@marti1125](https://github.com/marti1125) - Team Culqi)

## Licencia

El código fuente de culqi-java está distribuido bajo MIT License, revisar el archivo [LICENSE](https://github.com/culqi/culqi-java/blob/master/LICENSE).
