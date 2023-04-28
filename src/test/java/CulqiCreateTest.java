import junit.framework.TestCase;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import java.util.Map;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CulqiCreateTest extends TestCase {
	
	CulqiCRUD culqiCRUD = new CulqiCRUD();
	
	
    @Test
    public void test01_createToken() throws Exception {
        assertEquals("token", culqiCRUD.createToken().get("object").toString());
    }

    @Test
    public void test02_createTokenEncrypt() throws Exception {
        assertEquals("token", culqiCRUD.createTokenEncrypt().get("object").toString());
    }

    @Test
    public void test03_createTokenYape() throws Exception {
        assertEquals("token", culqiCRUD.createTokenYape().get("object").toString());
    }
    
    @Test
    public void test04_createCharge() throws Exception {
        assertEquals("charge", culqiCRUD.createCharge().get("object").toString());
    }
    
    @Test
    public void test04_createChargeEncrypt() throws Exception {
        assertEquals("charge", culqiCRUD.createChargeEncrypt().get("object").toString());
    }


    @Test
    public void test05_createPlan() throws Exception {
        assertEquals("plan", culqiCRUD.createPlan().get("object").toString());
    }

    @Test
    public void test06_createCustomer() throws Exception {
        assertEquals("customer", culqiCRUD.createCustomer().get("object").toString());
    }

    @Test
    public void test07_createCard() throws Exception {
        assertEquals("card", culqiCRUD.createCard().get("object").toString());
    }

    @Test
    public void test08_createSubscription() throws Exception {
        assertEquals("subscription", culqiCRUD.createSubscription().get("object").toString());
    }

    @Test
    public void test09_chargeCapture() throws Exception {
        Map<String, Object> capture = culqiCRUD.init().charge.capture(culqiCRUD.createCharge().get("id").toString());
        assertNotSame("charge", capture.get("object").toString());
    }

    @Test
    public void test10_createRefund() throws Exception {
        assertEquals("refund", culqiCRUD.createRefund().get("object").toString());
    }
    
    @Test
    public void test11_createOrderEncrypt() throws Exception {
        Map<String, Object> order = culqiCRUD.createOrderEncrypt(false);
        System.out.println(order);
        assertEquals("order", order.get("object").toString());
    }
    
    @Test
    public void test11_createOrder() throws Exception {
        Map<String, Object> order = culqiCRUD.createOrder(true);
        System.out.println(order);
        assertEquals("order", order.get("object").toString());
    }

    @Test
    public void test12_confirmOrderType() throws Exception {
        Map<String, Object> confirmOrderType = culqiCRUD.confirmOrderType();
        System.out.println(confirmOrderType);
        assertEquals("order", confirmOrderType.get("object").toString());
    }
}
