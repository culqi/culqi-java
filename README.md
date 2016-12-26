# Culqi-Java

Biblioteca Java oficial de CULQI (BETA), pagos simples en tu sitio web.

**Nota:** Esta biblioteca trabaja con la [v2.0](https://culqi.github.io/api-docs/) (BETA) de Culqi API.


# Dependencias

- [Lombok](https://projectlombok.org/features/index.html)
- [Apache HTTP Components](https://hc.apache.org/)
- [Jackson Core Databind](https://github.com/FasterXML/jackson-databind/wiki)

# Ejemplo

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

# Build

```bash
$ mvn package
```

# Licencia

Copyright 2016 Culqi

Licensed under the MIT License
