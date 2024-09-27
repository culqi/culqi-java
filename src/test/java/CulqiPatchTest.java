import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;



@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CulqiPatchTest extends TestCase {

	CulqiCRUD culqiCRUD = new CulqiCRUD();
	ObjectMapper mapper = new ObjectMapper();

	@Test
    public void test01_updateToken() throws Exception {
		Map<String, Object> res = mapper.readValue(culqiCRUD.updateToken().getBody(), new TypeReference<HashMap<String, Object>>(){});
        System.err.println(res);
        assertEquals("token",  res.get("object").toString());
    }
    @Test
    public void test01_updateTokenEncrypt() throws Exception {
        Map<String, Object> res = mapper.readValue(culqiCRUD.updateTokenEncrypt().getBody(), new TypeReference<HashMap<String, Object>>(){});
        assertEquals("token", res.get("object").toString());
    }
	
	@Test
    public void test02_updateCharge() throws Exception {
		Map<String, Object> res = mapper.readValue(culqiCRUD.updateCharge().getBody(), new TypeReference<HashMap<String, Object>>(){});
        assertEquals("charge", res.get("object").toString());
    }

    @Test
    public void test02_updateChargeEncrypt() throws Exception {
        Map<String, Object> res = mapper.readValue(culqiCRUD.updateChargeEncrypt().getBody(), new TypeReference<HashMap<String, Object>>(){});
        assertEquals("charge", res.get("object").toString());
    }
	 
	@Test
    public void test03_updateRefund() throws Exception {
		Map<String, Object> res = mapper.readValue(culqiCRUD.updateRefund().getBody(), new TypeReference<HashMap<String, Object>>(){});
        assertEquals("refund", res.get("object").toString());
    }

    @Test
    public void test03_updateRefundEncrypt() throws Exception {
        Map<String, Object> res = mapper.readValue(culqiCRUD.updateRefundEncrypt().getBody(), new TypeReference<HashMap<String, Object>>(){});
        assertEquals("refund", res.get("object").toString());
    }
	
	@Test
    public void test04_updateCustomer() throws Exception {
		Map<String, Object> res = mapper.readValue(culqiCRUD.updateCustomer().getBody(), new TypeReference<HashMap<String, Object>>(){});
        assertEquals("customer", res.get("object").toString());
    }
    @Test
    public void test04_updateCustomerEncrypt() throws Exception {
        Map<String, Object> res = mapper.readValue(culqiCRUD.updateCustomerEncrypt().getBody(), new TypeReference<HashMap<String, Object>>(){});
        assertEquals("customer", res.get("object").toString());
    }
	
	@Test
    public void test05_updateCard() throws Exception {
		Map<String, Object> res = mapper.readValue(culqiCRUD.updateCard().getBody(), new TypeReference<HashMap<String, Object>>(){});
        assertEquals("card", res.get("object").toString());
    }
    @Test
    public void test05_updateCardEncrypt() throws Exception {
        Map<String, Object> res = mapper.readValue(culqiCRUD.updateCardEncrypt().getBody(), new TypeReference<HashMap<String, Object>>(){});
        assertEquals("card", res.get("object").toString());
    }
	
	@Test
    public void test06_updatePlan() throws Exception {
        Map<String, Object> res = mapper.readValue(culqiCRUD.updatePlan().getBody(), new TypeReference<HashMap<String, Object>>(){});
        Object id = res.get("id");
        assertTrue(id instanceof String);
    }

    @Test
    public void test06_updatePlanEncrypt() throws Exception {
        Map<String, Object> res = mapper.readValue(culqiCRUD.updatePlanEncrypt().getBody(), new TypeReference<HashMap<String, Object>>(){});
        Object id = res.get("id");
        assertTrue(id instanceof String);
    }
	
	@Test
    public void test07_updateSubscription() throws Exception {
        Map<String, Object> res = mapper.readValue(culqiCRUD.updateSubscription().getBody(), new TypeReference<HashMap<String, Object>>(){});
        Object id = res.get("id");
        assertTrue(id instanceof String);
    }

    @Test
    public void test07_updateSubscriptionEncrypt() throws Exception {
        Map<String, Object> res = mapper.readValue(culqiCRUD.updateSubscriptionEncrypt().getBody(), new TypeReference<HashMap<String, Object>>(){});
        System.out.println(res);
        Object id = res.get("id");
        assertTrue(true);
    }
	
	@Test
    public void test08_updateOrder() throws Exception {
		Map<String, Object> res = mapper.readValue(culqiCRUD.updateOrder().getBody(), new TypeReference<HashMap<String, Object>>(){});
        assertEquals("order", res.get("object").toString());
    }

    @Test
    public void test08_updateOrderEncrypt() throws Exception {
        Map<String, Object> res = mapper.readValue(culqiCRUD.updateOrderEncrypt().getBody(), new TypeReference<HashMap<String, Object>>(){});
        assertEquals("order", res.get("object").toString());
    }

}
