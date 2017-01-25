import com.culqi.Culqi;
import com.culqi.model.*;
import com.culqi.query.ChargeQuery;
import com.culqi.util.Util;
import junit.framework.TestCase;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;
import java.util.Map;

/**
 * Created by culqi on 1/4/17.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CulqiTest extends TestCase {

    Security culqi = new Culqi().init("pk_test_vzMuTHoueOMlgUPj","sk_test_UTCQSGcXW8bCyU59");

    Token token = new Token();

    Charge charge = new Charge();

    Subscription subscription = new Subscription();

    protected Map<String, Object> token() throws Exception {
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

    @Test
    public void test1ValidCreateToken() throws Exception {
        assertEquals("token", token().get("object").toString());
    }

    @Test
    public void test2ValidFindToken() throws Exception {
        Map<String, Object>  findToken = token.get(culqi,token().get("id").toString());
        assertNotNull("Existe ", findToken);
    }

    protected Map<String, Object> charge() throws Exception {
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

    @Test
    public void test3ValidCreateCharge() throws Exception {
        assertEquals("charge", charge().get("object").toString());
    }

    @Test
    public void test4VListCharges() throws Exception {
        ChargeQuery chargeQuery = new ChargeQuery();
        chargeQuery.setMax_amount(500);
        chargeQuery.setMin_amount(0);

        List<Map<String, Object>> result = charge.list(culqi, chargeQuery);
        boolean sizeData = false;
        if(result.size() > 0){
            sizeData = true;
        }

        assertTrue(sizeData);
    }

    protected Map<String, Object> plan() throws Exception {
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

    @Test
    public void test5ValidCreatePlan() throws Exception {
        assertEquals("plan", plan().get("object").toString());
    }

    protected Map<String, Object> subscription() throws Exception {
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

    @Test
    public void test6ValidCreateSubscription() throws Exception {
        System.out.println(charge().get("id").toString());
        assertEquals("subscription", subscription().get("object").toString());
    }

    protected Map<String, Object> refund() throws Exception {
        Refund refund = new Refund();
        refund.setAmount(900);
        refund.setCharge_id(charge().get("id").toString());
        refund.setReason("give me my money back!");
        return refund.create(culqi);
    }

    /*@Test
    public void test7ValidCreateRefund() throws Exception {
        assertEquals("refund", refund().get("object").toString());
    }*/

}
