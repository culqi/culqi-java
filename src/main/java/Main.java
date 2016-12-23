/**
 * Created by culqi on 12/21/16.
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import controller.Charges;
import controller.Plans;
import controller.Subscriptions;
import controller.Tokens;
import model.*;

import java.util.UUID;


public class Main {

    public static void main(String[] args) throws Exception {

        String uuidNumber = String.valueOf(UUID.randomUUID().getMostSignificantBits()%2000);
        int order_id = Integer.parseInt(uuidNumber.replace("-",""));

        ObjectMapper mapper = new ObjectMapper();

        Secure secure = new Secure();
        secure.setCOD_ECOMERCE("pk_test_vzMuTHoueOMlgUPj");
        secure.setAPI_KEY("sk_test_UTCQSGcXW8bCyU59");

        Token token = new Token();
        token.setCard_number("4111111111111111");
        token.setCurrency_code("PEN");
        token.setFingerprint("q352454534");
        token.setCvv("123");
        token.setEmail("waguirre@me.com");
        token.setExpiration_month(9);
        token.setExpiration_year(2020);
        token.setLast_name("Aguirre");
        token.setFirst_name("Willy");

        Tokens tokens = new Tokens();
        String tokenID = tokens.create(secure,token);
        System.out.println("tokenID " + tokenID);

        Charge charge = new Charge();
        charge.setAddress("Avenida Lima 1232");
        charge.setAddress_city("LIMA");
        charge.setAmount(1000);
        charge.setCountry_code("PE");
        charge.setCurrency_code("PEN");
        charge.setCvv("123");
        charge.setEmail("wmuro@me.com");
        charge.setFirst_name("William");
        charge.setInstallments(0);
        charge.setLast_name("Muro");
        charge.setMetadata("");
        charge.setOrder_id(order_id);
        charge.setPhone_number(3333339);
        charge.setProduct_description("Venta de prueba");
        charge.setToken_id(tokenID);

        Charges charges = new Charges();
        System.out.println("Charge " + charges.createCharge(secure,charge));

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
        String planID = plans.createPlan(secure,plan);
        System.out.println("Plan Alias: " + planID);

        Subscription subscription = new Subscription();
        subscription.setAddress("Avenida Lima 123213");
        subscription.setAddress_city("LIMA");
        subscription.setCountry_code("PE");
        subscription.setEmail("wmuro@me.com");
        subscription.setLast_name("Muro");
        subscription.setFirst_name("William");
        subscription.setPhone_number(1234567789);
        subscription.setPlan_alias(planID);
        subscription.setToken_id(tokenID);

        Subscriptions subscriptions = new Subscriptions();
        System.out.println(subscriptions.createSubscription(secure, subscription));

    }

}
