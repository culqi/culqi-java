import junit.framework.TestCase;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.culqi.model.ResponseCulqi;
import com.fasterxml.jackson.core.type.TypeReference;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CulqiCreateTest extends TestCase {
	
	CulqiCRUD culqiCRUD = new CulqiCRUD();
	
	ObjectMapper mapper = new ObjectMapper();
	
    @Test
    public void test01_createToken() throws Exception {
    	Map<String, Object> res = mapper.readValue(culqiCRUD.createToken().getBody(), new TypeReference<HashMap<String, Object>>(){});
        System.err.println(res);
        assertEquals("token", res.get("object").toString());
    }

    @Test
    public void test02_createTokenEncrypt() throws Exception {
    	Map<String, Object> res = mapper.readValue(culqiCRUD.createTokenEncrypt().getBody(), new TypeReference<HashMap<String, Object>>(){});
        assertEquals("token", res.get("object").toString());
    }

    @Test
    public void test03_createTokenYape() throws Exception {
    	Map<String, Object> res = mapper.readValue(culqiCRUD.createTokenYape().getBody(), new TypeReference<HashMap<String, Object>>(){});
        System.err.println(res);
        assertEquals("token",res.get("object").toString());
    }
    
    @Test
    public void test04_createCharge() throws Exception {
    	ResponseCulqi response = culqiCRUD.createCharge();
    	Map<String, Object> res = mapper.readValue(response.getBody(), new TypeReference<HashMap<String, Object>>(){});
        System.err.println("Response: "+response);
    	if (response.getStatusCode()==200) {
            System.err.println(response);
    		assertEquals("REVIEW",res.get("action_code").toString());
    	}else if (response.getStatusCode()==201) {
            System.err.println(res);
    		assertEquals("charge",res.get("object").toString());
    	}
    }

    @Test
    public void test04_createRecurrentCharge() throws Exception {
        ResponseCulqi response = culqiCRUD.createRecurrentCharge();
        Map<String, Object> res = mapper.readValue(response.getBody(), new TypeReference<HashMap<String, Object>>(){});
        System.err.println("Response: "+response);
        if (response.getStatusCode()==200) {
            System.err.println(response);
            assertEquals("REVIEW",res.get("action_code").toString());
        }else if (response.getStatusCode()==201) {
            System.err.println(res);
            assertEquals("charge",res.get("object").toString());
        }
    }
    
    @Test
    public void test04_createChargeEncrypt() throws Exception {
    	ResponseCulqi response = culqiCRUD.createChargeEncrypt();
    	Map<String, Object> res = mapper.readValue(response.getBody(), new TypeReference<HashMap<String, Object>>(){});
    	if (response.getStatusCode()==200) {
    		assertEquals("REVIEW",res.get("action_code").toString());
    	}else if (response.getStatusCode()==201) {
    		assertEquals("charge",res.get("object").toString());
    	}
    }

    @Test
    public void test04_createRecurrentChargeEncrypt() throws Exception {
        ResponseCulqi response = culqiCRUD.createRecurrentChargeEncrypt();
        Map<String, Object> res = mapper.readValue(response.getBody(), new TypeReference<HashMap<String, Object>>(){});
        if (response.getStatusCode()==200) {
            assertEquals("REVIEW",res.get("action_code").toString());
        }else if (response.getStatusCode()==201) {
            assertEquals("charge",res.get("object").toString());
        }
    }


    @Test
    public void test05_createPlan() throws Exception {
        Map<String, Object> res = mapper.readValue(culqiCRUD.createPlan().getBody(), new TypeReference<HashMap<String, Object>>(){});
        Object id = res.get("id");
        assertTrue(id instanceof String);
    }

    @Test
    public void test05_createPlanEncrypt() throws Exception {
        Map<String, Object> res = mapper.readValue(culqiCRUD.createPlanEncrypt().getBody(), new TypeReference<HashMap<String, Object>>(){});
        Object id = res.get("id");
        assertTrue(id instanceof String);
    }

    @Test
    public void test06_createCustomer() throws Exception {
    	Map<String, Object> res = mapper.readValue(culqiCRUD.createCustomer().getBody(), new TypeReference<HashMap<String, Object>>(){});
        System.err.println(res);
        assertEquals("customer",res.get("object").toString());
    }

    @Test
    public void test06_createCustomerEncrypt() throws Exception {
        Map<String, Object> res = mapper.readValue(culqiCRUD.createCustomerEncrypt().getBody(), new TypeReference<HashMap<String, Object>>(){});
        System.err.println(res);
        assertEquals("customer",res.get("object").toString());
    }

    @Test
    public void test07_createCard() throws Exception {
    	ResponseCulqi response = culqiCRUD.createCard();
    	Map<String, Object> res = mapper.readValue(response.getBody(), new TypeReference<HashMap<String, Object>>(){});
    	if (response.getStatusCode()==200) {
    		assertEquals("REVIEW",res.get("action_code").toString());
    	}else if (response.getStatusCode()==201) {
    		assertEquals("card",res.get("object").toString());
    	}
    }

    @Test
    public void test07_createCardEncrypt() throws Exception {
    	ResponseCulqi response = culqiCRUD.createCardEncrypt();
    	Map<String, Object> res = mapper.readValue(response.getBody(), new TypeReference<HashMap<String, Object>>(){});
    	if (response.getStatusCode()==200) {
    		assertEquals("REVIEW",res.get("action_code").toString());
    	}else if (response.getStatusCode()==201) {
    		assertEquals("card",res.get("object").toString());
    	}
    }

    @Test
    public void test08_createSubscription() throws Exception {
        Map<String, Object> res = mapper.readValue(culqiCRUD.createSubscription().getBody(), new TypeReference<HashMap<String, Object>>(){});
        Object id = res.get("id");
        assertTrue(id instanceof String);
    }
    @Test
    public void test08_createSubscriptionEncrypt() throws Exception {
        Map<String, Object> res = mapper.readValue(culqiCRUD.createSubscriptionEncrypt().getBody(), new TypeReference<HashMap<String, Object>>(){});
        Object id = res.get("id");
        assertTrue(id instanceof String);
    }

    @Test
    public void test09_chargeCapture() throws Exception {
         ResponseCulqi response = culqiCRUD.createCharge();
     	 Map<String, Object> res = mapper.readValue(response.getBody(), new TypeReference<HashMap<String, Object>>(){});
     	 String charge_id = res.get("id").toString();
     	 ResponseCulqi capture = culqiCRUD.init().charge.capture(charge_id);
     	 Map<String, Object> res1 = mapper.readValue(capture.getBody(), new TypeReference<HashMap<String, Object>>(){});
         
     	assertNotSame("charge", res1.get("object").toString());
    }

    @Test
    public void test09_chargeCaptureEncrypt() throws Exception {
        ResponseCulqi response = culqiCRUD.createChargeEncrypt();
        Map<String, Object> res = mapper.readValue(response.getBody(), new TypeReference<HashMap<String, Object>>(){});
        String charge_id = res.get("id").toString();
        ResponseCulqi capture = culqiCRUD.init().charge.capture(charge_id, culqiCRUD.rsaPublicKey, culqiCRUD.rsaId);
        Map<String, Object> res1 = mapper.readValue(capture.getBody(), new TypeReference<HashMap<String, Object>>(){});

        assertNotSame("charge", res1.get("object").toString());
    }

    @Test
    public void test10_createRefund() throws Exception {
    	 Map<String, Object> res = mapper.readValue(culqiCRUD.createRefund().getBody(), new TypeReference<HashMap<String, Object>>(){});
         assertEquals("refund",res.get("object").toString());
    }

    @Test
    public void test10_createRefundEncrypt() throws Exception {
        Map<String, Object> res = mapper.readValue(culqiCRUD.createRefundEncrypt().getBody(), new TypeReference<HashMap<String, Object>>(){});
        assertEquals("refund",res.get("object").toString());
    }
    
    @Test
    public void test11_createOrder() throws Exception {
    	ResponseCulqi response = culqiCRUD.createOrder(false);
        Map<String, Object> res = mapper.readValue(response.getBody(), new TypeReference<HashMap<String, Object>>(){});
        assertEquals("order", res.get("object").toString());
    }
    
    
    @Test
    public void test11_createOrderEncrypt() throws Exception {
    	ResponseCulqi response = culqiCRUD.createOrderEncrypt(false);
        Map<String, Object> res = mapper.readValue(response.getBody(), new TypeReference<HashMap<String, Object>>(){});
        assertEquals("order", res.get("object").toString());
    }

    @Test
    public void test12_confirmOrderType() throws Exception {
    	ResponseCulqi response = culqiCRUD.confirmOrderType();
        Map<String, Object> confirmOrderType = mapper.readValue(response.getBody(), new TypeReference<HashMap<String, Object>>(){});
        System.out.println(confirmOrderType);
        assertEquals("order", confirmOrderType.get("object").toString());
    }
    
}
