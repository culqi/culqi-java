import junit.framework.TestCase;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CulqiDeleteTest extends TestCase {
	
	CulqiCRUD culqiCRUD = new CulqiCRUD();
	
	ObjectMapper mapper = new ObjectMapper();

    
    // ELIMINAR RECURSOS

    @Test
    public void test01_deleteSubscription() throws Exception {
    	Map<String, Object> res = mapper.readValue(culqiCRUD.createSubscription().getBody(), new TypeReference<HashMap<String, Object>>(){});
        Map<String, Object> subscriptionDeleted = mapper.readValue(culqiCRUD.init().subscription.delete(res.get("id").toString()).getBody(), new TypeReference<HashMap<String, Object>>(){});
        assertTrue(Boolean.valueOf(subscriptionDeleted.get("deleted").toString()));
    }

    @Test
    public void test02_deletePlan() throws Exception {
    	Map<String, Object> res = mapper.readValue(culqiCRUD.createPlan().getBody(), new TypeReference<HashMap<String, Object>>(){});
        Map<String, Object> planDeleted = mapper.readValue(culqiCRUD.init().plan.delete(res.get("id").toString()).getBody(), new TypeReference<HashMap<String, Object>>(){});
        assertTrue(Boolean.valueOf(planDeleted.get("deleted").toString()));
    }

    @Test
    public void test03_deleteCard() throws Exception {
    	Map<String, Object> res = mapper.readValue(culqiCRUD.createCard().getBody(), new TypeReference<HashMap<String, Object>>(){});
        Map<String, Object> cardDeleted = mapper.readValue(culqiCRUD.init().card.delete(res.get("id").toString()).getBody(), new TypeReference<HashMap<String, Object>>(){});
        assertTrue(Boolean.valueOf(cardDeleted.get("deleted").toString()));
    }

    @Test
    public void test04_deleteCustomer() throws Exception {
    	Map<String, Object> res = mapper.readValue(culqiCRUD.createCustomer().getBody(), new TypeReference<HashMap<String, Object>>(){});
        Map<String, Object> customerDeleted =  mapper.readValue(culqiCRUD.init().customer.delete(res.get("id").toString()).getBody(), new TypeReference<HashMap<String, Object>>(){});
        assertTrue(Boolean.valueOf(customerDeleted.get("deleted").toString()));
    }

    @Test
    public void test05_deleteOrder() throws Exception {
    	Map<String, Object> res = mapper.readValue(culqiCRUD.createOrder(true).getBody(), new TypeReference<HashMap<String, Object>>(){});
        Map<String, Object> orderDeleted = mapper.readValue(culqiCRUD.init().order.delete(res.get("id").toString()).getBody(), new TypeReference<HashMap<String, Object>>(){});
        assertTrue(Boolean.valueOf(orderDeleted.get("deleted").toString()));
    }

}
