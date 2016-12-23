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

        String charge_id = culqi.createCharge("Avenida Lima 1232", "LIMA", 1000, "PE", "PEN", "123", "wmuro@me.com",
                "William", 0, "Muro", "", order_id, 3333339, "Venta de prueba", token_id);


        System.out.println("Charge " + charge_id);

        /*Charges charges = new Charges();
        Result resultCharge = charges.createCharge(secure,charge);
        String chargeID = resultCharge.getId();
        System.out.println("Charge " + resultCharge.getMessage());

        Plan plan = new Plan();
        plan.setAlias("plan-"+UUID.randomUUID().toString());
        plan.setAmount(1000);
        plan.setCurrency_code("PEN");
        plan.setInterval("day");
        plan.setInterval_count(2);
        plan.setLimit(10);
        plan.setName("Plan "+UUID.randomUUID().toString());
        plan.setTrial_days(30);

        Plans plans = new Plans();
        Result resultPlan = plans.createPlan(secure,plan);
        String planAlias = resultPlan.getMessage();
        System.out.println("Plan Alias: " + planAlias);

        Subscription subscription = new Subscription();
        subscription.setAddress("Avenida Lima 123213");
        subscription.setAddress_city("LIMA");
        subscription.setCountry_code("PE");
        subscription.setEmail("wmuro@me.com");
        subscription.setLast_name("Muro");
        subscription.setFirst_name("William");
        subscription.setPhone_number(1234567789);
        subscription.setPlan_alias(planAlias);
        subscription.setToken_id(tokenID);

        Subscriptions subscriptions = new Subscriptions();
        System.out.println(subscriptions.createSubscription(secure, subscription));

        Refund refund = new Refund();
        refund.setAmount(900);
        refund.setCharge_id(chargeID);
        refund.setReason("bought an incorrect product");

        Refunds refunds = new Refunds();
        System.out.println(refunds.createRefund(secure,refund).getMessage());*/

    }

}
