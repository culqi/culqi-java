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
    public void test02_updateCharge() throws Exception {
		Map<String, Object> res = mapper.readValue(culqiCRUD.updateCharge().getBody(), new TypeReference<HashMap<String, Object>>(){});
        assertEquals("charge", res.get("object").toString());
    }
	 
	@Test
    public void test03_updateRefund() throws Exception {
		Map<String, Object> res = mapper.readValue(culqiCRUD.updateRefund().getBody(), new TypeReference<HashMap<String, Object>>(){});
        assertEquals("refund", res.get("object").toString());
    }
	
	@Test
    public void test04_updateCustomer() throws Exception {
		Map<String, Object> res = mapper.readValue(culqiCRUD.updateCustomer().getBody(), new TypeReference<HashMap<String, Object>>(){});
        assertEquals("customer", res.get("object").toString());
    }
	
	@Test
    public void test05_updateCard() throws Exception {
		Map<String, Object> res = mapper.readValue(culqiCRUD.updateCard().getBody(), new TypeReference<HashMap<String, Object>>(){});
        assertEquals("card", res.get("object").toString());
    }
	
	@Test
    public void test06_updatePlan() throws Exception {
        Map<String, Object> res = mapper.readValue(culqiCRUD.updatePlan().getBody(),
                new TypeReference<HashMap<String, Object>>() {
                });
        // Validamos que tiene que retornar el servicio un id en formato string para
        // comprobar que este actualizado el plan correctamente
        Object id = res.get("id");
        assertTrue(id instanceof String);
    }
	
	@Test
    public void test07_updateSubscription() throws Exception {
        Map<String, Object> res = mapper.readValue(culqiCRUD.updateSubscription().getBody(),
                new TypeReference<HashMap<String, Object>>() {
                });
        Object id = res.get("id");
        assertTrue(id instanceof String);
    }
	
	@Test
    public void test08_updateOrder() throws Exception {
		Map<String, Object> res = mapper.readValue(culqiCRUD.updateOrder().getBody(), new TypeReference<HashMap<String, Object>>(){});
        assertEquals("order", res.get("object").toString());
    }

}
