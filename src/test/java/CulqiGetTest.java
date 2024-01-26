import junit.framework.TestCase;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;



@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CulqiGetTest extends TestCase {
	
	CulqiCRUD culqiCRUD = new CulqiCRUD();
	ObjectMapper mapper = new ObjectMapper();

    // CONSULTAR RECURSOS

    @Test
    public void test01_findToken() throws Exception {
    	Map<String, Object> res = mapper.readValue(culqiCRUD.createToken().getBody(), new TypeReference<HashMap<String, Object>>(){});
        Map<String, Object> tokenFound = mapper.readValue(culqiCRUD.init().token.get(res.get("id").toString()).getBody(), new TypeReference<HashMap<String, Object>>(){});
        assertEquals("token", tokenFound.get("object").toString());
    }

    @Test
    public void test02_findCharge() throws Exception {
    	Map<String, Object> res = mapper.readValue(culqiCRUD.createCharge().getBody(), new TypeReference<HashMap<String, Object>>(){});
        Map<String, Object> chargeFound = mapper.readValue(culqiCRUD.init().charge.get(res.get("id").toString()).getBody(), new TypeReference<HashMap<String, Object>>(){});
        assertEquals("charge", chargeFound.get("object").toString());
    }
    
    @Test
    public void test03_findRefund() throws Exception {
    	Map<String, Object> res = mapper.readValue(culqiCRUD.createRefund().getBody(), new TypeReference<HashMap<String, Object>>(){});
        Map<String, Object> refundFound = mapper.readValue(culqiCRUD.init().refund.get(res.get("id").toString()).getBody(), new TypeReference<HashMap<String, Object>>(){});
        assertEquals("refund", refundFound.get("object").toString());
    }

    @Test
    public void test04_findCustomer() throws Exception {
    	Map<String, Object> res = mapper.readValue(culqiCRUD.createCustomer().getBody(), new TypeReference<HashMap<String, Object>>(){});
        Map<String, Object> customerFound = mapper.readValue(culqiCRUD.init().customer.get(res.get("id").toString()).getBody(), new TypeReference<HashMap<String, Object>>(){});
        assertEquals("customer", customerFound.get("object").toString());
    }

    @Test
    public void test05_findCard() throws Exception {
    	Map<String, Object> res = mapper.readValue(culqiCRUD.createCard().getBody(), new TypeReference<HashMap<String, Object>>(){});
        Map<String, Object> cardFound = mapper.readValue(culqiCRUD.init().card.get(res.get("id").toString()).getBody(), new TypeReference<HashMap<String, Object>>(){});
        assertEquals("card", cardFound.get("object").toString());
    }

    @Test
    public void test06_findPlan() throws Exception {
    	Map<String, Object> res = mapper.readValue(culqiCRUD.createPlan().getBody(), new TypeReference<HashMap<String, Object>>(){});
    	Map<String, Object> planFound = mapper.readValue(culqiCRUD.init().plan.get(res.get("id").toString()).getBody(), new TypeReference<HashMap<String, Object>>(){});
    	Object id = planFound.get("id");
        assertTrue(id instanceof String);
    }

    @Test
    public void test07_findSubscription() throws Exception {
    	Map<String, Object> res = mapper.readValue(culqiCRUD.createSubscription().getBody(), new TypeReference<HashMap<String, Object>>(){});
        Map<String, Object> subscriptionFound = mapper.readValue(culqiCRUD.init().subscription.get(res.get("id").toString()).getBody(), new TypeReference<HashMap<String, Object>>(){});
        Object id = subscriptionFound.get("id");
        assertTrue(id instanceof String);
    }

    @Test
    public void test08_findOrder() throws Exception {
    	Map<String, Object> res = mapper.readValue(culqiCRUD.createOrder(true).getBody(), new TypeReference<HashMap<String, Object>>(){});
        Map<String, Object> orderFound = mapper.readValue(culqiCRUD.init().order.get(res.get("id").toString()).getBody(), new TypeReference<HashMap<String, Object>>(){});
        assertEquals("order", orderFound.get("object").toString());
    }
    
}
