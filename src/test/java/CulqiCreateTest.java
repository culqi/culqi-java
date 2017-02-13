import com.culqi.Culqi;
import com.culqi.util.Util;
import junit.framework.TestCase;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by culqi on 1/4/17.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CulqiCreateTest extends TestCase {

    Culqi culqi = new Culqi("pk_test_vzMuTHoueOMlgUPj","sk_test_UTCQSGcXW8bCyU59");

    protected Map<String, Object> token() throws Exception {
        Map<String, Object> token = new HashMap<String, Object>();
        token.put("card_number", "4111111111111111");
        token.put("cvv", "123");
        token.put("email", "wm@wm.com");
        token.put("expiration_month", 9);
        token.put("expiration_year", 2020);
        return culqi.token.create(token);
    }

    @Test
    public void test1ValidCreateToken() throws Exception {
        assertEquals("token", token().get("object").toString());
    }

    protected Map<String, Object> charge() throws Exception {
        Map<String, Object> charge = new HashMap<String, Object>();
        Map<String, Object> metadata = new HashMap<String, Object>();
        metadata.put("oder_id", "124");
        charge.put("amount",1000);
        charge.put("capture", true);
        charge.put("currency_code","PEN");
        charge.put("description","Venta de prueba");
        charge.put("email","test@culqi.com");
        charge.put("installments", 0);
        charge.put("metadata", metadata);
        charge.put("source_id", token().get("id").toString());
        return culqi.charge.create(charge);
    }

    @Test
    public void test2ValidCreateCharge() throws Exception {
        assertEquals("charge", charge().get("object").toString());
    }

    protected Map<String, Object> plan() throws Exception {
        Map<String, Object> plan = new HashMap<String, Object>();
        Map<String, Object> metadata = new HashMap<String, Object>();
        metadata.put("oder_id", "124");
        plan.put("amount",1000);
        plan.put("currency_code","PEN");
        plan.put("interval","dias");
        plan.put("interval_count",30);
        plan.put("limit", 4);
        plan.put("metadata", metadata);
        plan.put("name", "plan-"+new Util().ramdonString());
        plan.put("trial_days", 15);
        return culqi.plan.create(plan);
    }

    @Test
    public void test3ValidCreatePlan() throws Exception {
        System.out.println(plan());
        assertEquals("plan", plan().get("object").toString());
    }

    protected Map<String, Object> customer() throws Exception {
        Map<String, Object> customer = new HashMap<String, Object>();
        customer.put("address","Av Lima 123");
        customer.put("address_city","Lima");
        customer.put("country_code","PE");
        customer.put("email","tst"+new Util().ramdonString()+"@culqi.com");
        customer.put("first_name","Test");
        customer.put("last_name","Cuqli");
        customer.put("phone_number",99004356);
        return culqi.customer.create(customer);
    }

    @Test
    public void test4ValidCreateCustomer() throws Exception {
        assertEquals("customer", customer().get("object").toString());
    }

    protected Map<String, Object> card() throws Exception {
        Map<String, Object> card = new HashMap<String, Object>();
        card.put("customer_id",customer().get("id").toString());
        card.put("token_id",token().get("id").toString());
        return culqi.card.create(card);
    }

    @Test
    public void test5ValidCreateCard() throws Exception {
        assertEquals("card", card().get("object").toString());
    }

    protected Map<String, Object> subscription() throws Exception {
        Map<String, Object> subscription = new HashMap<String, Object>();
        subscription.put("card_id",card().get("id").toString());
        subscription.put("plan_id",plan().get("id").toString());
        return culqi.subscription.create(subscription);
    }

    @Test
    public void test6ValidCreateSubscription() throws Exception {
        assertEquals("subscription", subscription().get("object").toString());
    }

    @Test
    public void test7ChargeCapture() throws Exception {
        Map<String, Object> capture = culqi.charge.capture(charge().get("id").toString());
        System.out.println(capture);
        assertNotSame("charge", capture.get("object").toString());
    }

    /*protected Map<String, Object> refund() throws Exception {
        Map<String, Object> refund = new HashMap<String, Object>();
        refund.put("amount",900);
        refund.put("charge_id",charge().get("id").toString());
        refund.put("reason","give me my money back!");
        return culqi.refund.create(refund);
    }

    @Test
    public void test7ValidCreateRefund() throws Exception {
        assertEquals("refund", refund().get("object").toString());
    }*/

}
