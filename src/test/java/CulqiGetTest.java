import junit.framework.TestCase;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import java.util.Map;



@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CulqiGetTest extends TestCase {
	
	CulqiCRUD culqiCRUD = new CulqiCRUD();

    // CONSULTAR RECURSOS

    @Test
    public void test01_findToken() throws Exception {
        Map<String, Object> tokenFound = culqiCRUD.init().token.get(culqiCRUD.createToken().get("id").toString());
        assertEquals("token", tokenFound.get("object").toString());
    }

    @Test
    public void test02_findCharge() throws Exception {
        Map<String, Object> chargeFound = culqiCRUD.init().charge.get(culqiCRUD.createCharge().get("id").toString());
        assertEquals("charge", chargeFound.get("object").toString());
    }
    
    @Test
    public void test03_findRefund() throws Exception {
        Map<String, Object> refundFound = culqiCRUD.init().refund.get(culqiCRUD.createRefund().get("id").toString());
        assertEquals("refund", refundFound.get("object").toString());
    }

    @Test
    public void test04_findCustomer() throws Exception {
        Map<String, Object> customerFound = culqiCRUD.init().customer.get(culqiCRUD.createCustomer().get("id").toString());
        assertEquals("customer", customerFound.get("object").toString());
    }

    @Test
    public void test05_findCard() throws Exception {
        Map<String, Object> cardFound = culqiCRUD.init().card.get(culqiCRUD.createCard().get("id").toString());
        assertEquals("card", cardFound.get("object").toString());
    }

    @Test
    public void test06_findPlan() throws Exception {
        Map<String, Object> planFound = culqiCRUD.init().plan.get(culqiCRUD.createPlan().get("id").toString());
        assertEquals("plan", planFound.get("object").toString());
    }

    @Test
    public void test07_findSubscription() throws Exception {
        Map<String, Object> subscriptionFound = culqiCRUD.init().subscription.get(culqiCRUD.createSubscription().get("id").toString());
        assertEquals("subscription", subscriptionFound.get("object").toString());
    }

    @Test
    public void test08_findOrder() throws Exception {
        Map<String, Object> orderFound = culqiCRUD.init().order.get(culqiCRUD.createOrder(true).get("id").toString());
        assertEquals("order", orderFound.get("object").toString());
    }
}
