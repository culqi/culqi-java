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

#### Imports

```java
import com.culqi.Culqi;
import com.culqi.util.Result;
import com.culqi.util.Util;
```

#### Inicialización
| Parametros | Tipo de dato |
|----|----|
| COD_ECOMERCE | String |
| API_KEY | String |

```java
Culqi culqi = new Culqi("pk_test_vzMuTHoueOMlgUPj","sk_test_UTCQSGcXW8bCyU59");
```

#### Crear Token
| Parametros | Tipo de dato |
|----|----|
| card_number | String |
| currency_code | String |
| cvv | String |
| expiration_month | int |
| expiration_year | int |
| fingerprint | String |
| last_name | String |
| email | String |
| first_name | String |

```java
Result resultToken = culqi.createToken("4111111111111111","PEN","123",9,2020,
                     "q352454534","Aguirre","waguirre@me.com", "Willy");

System.out.println(resultToken.getId());
```

#### Crear Cargo
| Parametros | Tipo de dato |
|----|----|
| address | String |
| address_city | String |
| amount | int |
| country_code | String |
| currency_code | String |
| email | String |
| first_name | String |
| installments | int |
| last_name | String |
| metadata | String |
| phone_number | int |
| product_description | String |
| token_id | String |

```java
Result resultCharge = culqi.createCharge("Avenida Lima 1232", "LIMA", 1000,
                      "PE", "PEN", "waguirre@me.com","Willy", 0, "Aguirre", "",
                      3333339, "Venta de prueba", resultToken.getId());

System.out.println("Charge " + resultCharge.getId());
```

#### Crear Plan
| Parametros | Tipo de dato |
|----|----|
| alias | String |
| amount | int |
| currency_code | String |
| interval | String |
| interval_count | int |
| limit | int |
| name | String |
| trial_days | int |

```java
Result resultAlias = culqi.createPlan("plan-"+new Util().ramdonString(), 1000,
                    "PEN", "day", 2,10,"Plan "+new Util().ramdonString(), 30);

System.out.println("Plan Alias: " + resultAlias.getMessage());
```

#### Crear Suscripción
| Parametros | Tipo de dato |
|----|----|
| address | String |
| address_city | String |
| country_code | String |
| email | String |
| first_name | String |
| last_name | String |
| phone_number | int |
| plan_alias | String |
| token_id | String |

```java
Result resultSubscription = culqi.createSubscription("Avenida Lima 123213",
                            "LIMA", "PE", "waguirre@me.com", "Aguirre",
        "Willy", 1234567789, resultAlias.getMessage(), resultToken.getId());

System.out.println(resultSubscription.getId());
```

#### Crear Devolución
| Parametros | Tipo de dato |
|----|----|
| amount | int |
| charge_id | String |
| reason | String |

```java
Result resultRefund = culqi.createRefund(900, resultCharge.getId(), "bought an incorrect product");

System.out.println(resultRefund.getMessage());
```

#### Clase Result

Se utiliza como filtro y guarda el ID, Mensaje, Object(Token, Charge, Error, etc...), Status(201,401,500) de cada petición al API de Culqi.

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

## Autor

Willy Aguirre ([@marti1125](https://github.com/marti1125) - Team Culqi)

## Licencia

El código fuente de culqi-java está distribuido bajo MIT License, revisar el archivo [LICENSE](https://github.com/culqi/culqi-java/blob/master/LICENSE).
