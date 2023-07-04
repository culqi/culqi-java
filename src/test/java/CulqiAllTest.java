import junit.framework.TestCase;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CulqiAllTest extends TestCase {

	CulqiCRUD culqiCRUD = new CulqiCRUD();

	ObjectMapper mapper = new ObjectMapper();
	
    @Test
    public void test01_allTokens() throws Exception {
    	Map<String, Object> res = mapper.readValue(culqiCRUD.tokens().getBody(), new TypeReference<HashMap<String, Object>>(){});
        List<Map<String, Object>> data = (List<Map<String, Object>>) res.get("data");
        assert(data.size() >= 0);
    }

    @Test
    public void test02_allOrders() throws Exception {
    	Map<String, Object> res = mapper.readValue(culqiCRUD.orders().getBody(), new TypeReference<HashMap<String, Object>>(){});
        List<Map<String, Object>> data = (List<Map<String, Object>>) res.get("data");
        assert (data.size() >= 0);
    }
    
    @Test
    public void test03_allCharge() throws Exception {
    	Map<String, Object> res = mapper.readValue(culqiCRUD.charges().getBody(), new TypeReference<HashMap<String, Object>>(){});
        List<Map<String, Object>> data = (List<Map<String,Object>>) res.get("data");
        assert(data.size() >= 0);
    }

    @Test
    public void test04_allPlan() throws Exception {
    	Map<String, Object> res = mapper.readValue(culqiCRUD.plans().getBody(), new TypeReference<HashMap<String, Object>>(){});
        List<Map<String, Object>> data = (List<Map<String,Object>>) res.get("data");
        assert(data.size() >= 0);
    }

    @Test
    public void test05_allCustomers() throws Exception {
    	Map<String, Object> res = mapper.readValue(culqiCRUD.customers().getBody(), new TypeReference<HashMap<String, Object>>(){});
        List<Map<String, Object>> data = (List<Map<String,Object>>) res.get("data");
        assert(data.size() >= 0);
    }

    @Test
    public void test05_allCards() throws Exception {
    	Map<String, Object> res = mapper.readValue(culqiCRUD.cards().getBody(), new TypeReference<HashMap<String, Object>>(){});
        List<Map<String, Object>> data = (List<Map<String,Object>>) res.get("data");
        assert(data.size() >= 0);
    }

    @Test
    public void test06_allSubscriptions() throws Exception {
    	Map<String, Object> res = mapper.readValue(culqiCRUD.subscriptions().getBody(), new TypeReference<HashMap<String, Object>>(){});
        List<Map<String, Object>> data = (List<Map<String,Object>>) res.get("data");
        assert(data.size() >= 0);
    }

    @Test
    public void test07_allRefunds() throws Exception {
    	Map<String, Object> res = mapper.readValue(culqiCRUD.refunds().getBody(), new TypeReference<HashMap<String, Object>>(){});
        List<Map<String, Object>> data = (List<Map<String,Object>>) res.get("data");
        assert(data.size() >= 0);
    }

    @Test
    public void test08_allEvents() throws Exception {
    	Map<String, Object> res = mapper.readValue(culqiCRUD.events().getBody(), new TypeReference<HashMap<String, Object>>(){});
        List<Map<String, Object>> data = (List<Map<String,Object>>) res.get("data");
        assert(data.size() >= 0);
    }

    @Test
    public void test09_allTransfers() throws Exception {
    	Map<String, Object> res = mapper.readValue(culqiCRUD.transfers().getBody(), new TypeReference<HashMap<String, Object>>(){});
        List<Map<String, Object>> data = (List<Map<String,Object>>) res.get("data");
        assert(data.size() >= 0);
    }

}
