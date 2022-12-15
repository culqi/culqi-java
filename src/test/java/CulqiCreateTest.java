import com.culqi.Culqi;
import com.culqi.util.CurrencyCode;
import com.culqi.util.Reason;
import com.culqi.util.Util;
import junit.framework.TestCase;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by culqi on 1/4/17.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CulqiCreateTest extends TestCase {

    public Culqi init() {
        Culqi culqi = new Culqi();
        culqi.public_key = "";
        culqi.secret_key = "";
        return culqi;
    }

    protected Map<String, Object> token() throws Exception {
        Map<String, Object> token = new HashMap<String, Object>();
        Calendar date = new GregorianCalendar();
        int year = date.get(Calendar.YEAR);  
        token.put("card_number", "5200000000001096");
        token.put("cvv", "111");
        token.put("email", "test@culqi.com");
        token.put("expiration_month", 7);
        token.put("expiration_year", year + 1);
        return init().token.create(token);
    }

    @Test
    public void test1ValidCreateToken() throws Exception {
        assertEquals("token", token().get("object").toString());
    }

    protected Map<String, Object> tokenYape() throws Exception {
        Map<String, Object> token = new HashMap<String, Object>();
         Map<String, Object> metadata = new HashMap<String, Object>();
        metadata.put("dni", "71702935"); 
        token.put("number_phone", "900000001");
        token.put("otp", "425251");
        token.put("amount", 700);
        token.put("metadata", metadata);
        return init().token.createYape(token);
    }

    @Test
    public void test1ValidCreateTokenYape() throws Exception {
        System.out.println(tokenYape().get("id"));
        assertEquals("token", tokenYape().get("object").toString());
    }

    protected Map<String, Object> order(Boolean confirm) throws Exception {
        Map<String, Object> order = new HashMap<String, Object>();
        Map<String, Object> metadata = new HashMap<String, Object>();
        Map<String, Object> client_details = new HashMap<String, Object>();
        metadata.put("dni", "70000000");
        client_details.put("first_name", "prueba firstname");
        client_details.put("last_name", "prueba lastname");
        client_details.put("email", "prueba@gmail.com");
        client_details.put("phone_number", "51945145222");
        order.put("amount", 60000);
        order.put("currency_code", CurrencyCode.PEN);
        order.put("description", "Venta de prueba");
        order.put("order_number", "pedido-" + new Util().ramdomNumber());
        order.put("client_details", client_details);
        order.put("metadata", metadata);
        order.put("expiration_date", (System.currentTimeMillis() / 1000) + 24 * 60 * 60);
        order.put("confirm", confirm);
        return init().order.create(order);
    }
    
    @Test
    public void test2ValidCreateOrder() throws Exception {
        Map<String, Object> order = order(true);
        System.out.println(order);
        assertEquals("order", order.get("object").toString());
    }

    protected Map<String, Object> confirmOrderType() throws Exception {
        Map<String, Object> order = new HashMap<String, Object>();
        String[] order_types = { "cuotealo", "cip" };
        order.put("order_id", order(false).get("id").toString());
        order.put("order_types", order_types);

        System.out.println(order);

        return init().order.confirm_order_type(order);
    }
    
    @Test
    public void test2ValidConfirmOrderType() throws Exception {
        Map<String, Object> confirmOrderType = confirmOrderType();
        System.out.println(confirmOrderType);
        assertEquals("order", confirmOrderType.get("object").toString());
    }


    protected Map<String, Object> charge() throws Exception {
        Map<String, Object> charge = new HashMap<String, Object>();
        Map<String, Object> metadata = new HashMap<String, Object>();
        metadata.put("oder_id", "1234");
        charge.put("amount",1000);
        charge.put("capture",true);
        charge.put("currency_code",CurrencyCode.PEN);
        charge.put("description","Venta de prueba");
        charge.put("email","test@culqi.com");
        charge.put("installments", 0);
        charge.put("metadata", metadata);
        charge.put("source_id", token().get("id").toString());
        return init().charge.create(charge);
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
        plan.put("currency_code",CurrencyCode.PEN);
        plan.put("interval","dias");
        plan.put("interval_count",30);
        plan.put("limit", 4);
        plan.put("metadata", metadata);
        plan.put("name", "plan-"+new Util().ramdonString());
        plan.put("trial_days", 15);
        return init().plan.create(plan);
    }

    @Test
    public void test3ValidCreatePlan() throws Exception {
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
        return init().customer.create(customer);
    }

    @Test
    public void test4ValidCreateCustomer() throws Exception {
        assertEquals("customer", customer().get("object").toString());
    }

    protected Map<String, Object> card() throws Exception {
        Map<String, Object> card = new HashMap<String, Object>();
        card.put("customer_id",customer().get("id").toString());
        card.put("token_id",token().get("id").toString());
        return init().card.create(card);
    }

    @Test
    public void test5ValidCreateCard() throws Exception {
        assertEquals("card", card().get("object").toString());
    }

    protected Map<String, Object> subscription() throws Exception {
        Map<String, Object> subscription = new HashMap<String, Object>();
        subscription.put("card_id",card().get("id").toString());
        subscription.put("plan_id",plan().get("id").toString());
        return init().subscription.create(subscription);
    }

    @Test
    public void test6ValidCreateSubscription() throws Exception {
        assertEquals("subscription", subscription().get("object").toString());
    }

    @Test
    public void test7ChargeCapture() throws Exception {
        Map<String, Object> capture = init().charge.capture(charge().get("id").toString());
        assertNotSame("charge", capture.get("object").toString());
    }

    protected Map<String, Object> refund() throws Exception {
        Map<String, Object> refund = new HashMap<String, Object>();
        refund.put("amount",500);
        refund.put("charge_id",charge().get("id").toString());
        refund.put("reason", Reason.solicitud_comprador);
        return init().refund.create(refund);
    }

    @Test
    public void test7ValidCreateRefund() throws Exception {
        assertEquals("refund", refund().get("object").toString());
    }

    // CONSULTAR RECURSOS

    @Test
    public void test8FindToken() throws Exception {
        Map<String, Object> tokenFound = init().token.get(token().get("id").toString());
        assertEquals("token", tokenFound.get("object").toString());
    }

    @Test
    public void test9FindOrder() throws Exception {
        Map<String, Object> orderFound = init().order.get(order(true).get("id").toString());
        assertEquals("order", orderFound.get("object").toString());
    }
    
    @Test
    public void test9FindCharge() throws Exception {
        Map<String, Object> chargeFound = init().charge.get(charge().get("id").toString());
        assertEquals("charge", chargeFound.get("object").toString());
    }

    @Test
    public void test10FindCustomer() throws Exception {
        Map<String, Object> customerFound = init().customer.get(customer().get("id").toString());
        assertEquals("customer", customerFound.get("object").toString());
    }

    @Test
    public void test11FindCard() throws Exception {
        Map<String, Object> cardFound = init().card.get(card().get("id").toString());
        assertEquals("card", cardFound.get("object").toString());
    }

    @Test
    public void test12FindPlan() throws Exception {
        Map<String, Object> planFound = init().plan.get(plan().get("id").toString());
        assertEquals("plan", planFound.get("object").toString());
    }

    @Test
    public void test13FindSubscription() throws Exception {
        Map<String, Object> subscriptionFound = init().subscription.get(subscription().get("id").toString());
        assertEquals("subscription", subscriptionFound.get("object").toString());
    }

    @Test
    public void test14FindRefund() throws Exception {
        Map<String, Object> refundFound = init().refund.get(refund().get("id").toString());
        assertEquals("refund", refundFound.get("object").toString());
    }

    // ELIMINAR RECURSOS

    @Test
    public void test15DeleteSubscription() throws Exception {
        Map<String, Object> subscriptionDeleted = init().subscription.delete(subscription().get("id").toString());
        assertTrue(Boolean.valueOf(subscriptionDeleted.get("deleted").toString()));
    }

    @Test
    public void test16DeletePlan() throws Exception {
        Map<String, Object> planDeleted = init().plan.delete(plan().get("id").toString());
        assertTrue(Boolean.valueOf(planDeleted.get("deleted").toString()));
    }

    @Test
    public void test17DeleteCard() throws Exception {
        Map<String, Object> cardDeleted = init().card.delete(card().get("id").toString());
        assertTrue(Boolean.valueOf(cardDeleted.get("deleted").toString()));
    }

    @Test
    public void test18DeleteCustomer() throws Exception {
        Map<String, Object> customerDeleted = init().customer.delete(customer().get("id").toString());
        assertTrue(Boolean.valueOf(customerDeleted.get("deleted").toString()));
    }

    @Test
    public void test19DeleteOrder() throws Exception {
        Map<String, Object> orderDeleted = init().order.delete(order(true).get("id").toString());
        assertTrue(Boolean.valueOf(orderDeleted.get("deleted").toString()));
    }

}
