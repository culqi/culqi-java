/**
 * Created by culqi on 12/21/16.
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import controller.*;
import model.*;
import util.Result;

import java.util.UUID;


public class Main {

    public static void main(String[] args) throws Exception {

        String uuidNumber = String.valueOf(UUID.randomUUID().getMostSignificantBits()%2000);
        int order_id = Integer.parseInt(uuidNumber.replace("-",""));

        Culqi culqi = new Culqi("pk_test_vzMuTHoueOMlgUPj","sk_test_UTCQSGcXW8bCyU59");
        String token_id = culqi.createToken("4111111111111111","PEN", "123",9,2020,"q352454534",
                   "Aguirre","waguirre@me.com", "Willy");

        System.out.println(token_id);

        String charge_id = culqi.createCharge("Avenida Lima 1232", "LIMA", 1000, "PE", "PEN", "123", "waguirre@me.com",
                "Willy", 0, "Aguirre", "", order_id, 3333339, "Venta de prueba", token_id);

        System.out.println("Charge " + charge_id);

        String plan_alias = culqi.createPlan("plan-"+UUID.randomUUID().toString(), 1000, "PEN", "day", 2,10,
                "Plan "+UUID.randomUUID().toString(), 30);

        System.out.println("Plan Alias: " + plan_alias);

        String subscription = culqi.createSubscription("Avenida Lima 123213", "LIMA", "PE", "waguirre@me.com", "Aguirre",
                "Willy", 1234567789, plan_alias, token_id);

        System.out.println(subscription);

        String refund = culqi.createRefund(900, charge_id, "bought an incorrect product");

        System.out.println(refund);

    }

}
