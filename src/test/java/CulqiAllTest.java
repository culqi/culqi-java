import junit.framework.TestCase;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;
import java.util.Map;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CulqiAllTest extends TestCase {

	CulqiCRUD culqiCRUD = new CulqiCRUD();

    @Test
    public void test01_allTokens() throws Exception {
        List<Map<String, Object>> data = (List<Map<String, Object>>) culqiCRUD.tokens().get("data");
        assert(data.size() >= 0);
    }

    @Test
    public void test02_allOrders() throws Exception {
        List<Map<String, Object>> data = (List<Map<String, Object>>) culqiCRUD.orders().get("data");
        assert (data.size() >= 0);
    }
    
    @Test
    public void test03_allCharge() throws Exception {
        List<Map<String, Object>> data = (List<Map<String,Object>>) culqiCRUD.charges().get("data");
        assert(data.size() >= 0);
    }

    @Test
    public void test04_allPlan() throws Exception {
        List<Map<String, Object>> data = (List<Map<String,Object>>) culqiCRUD.plans().get("data");
        assert(data.size() >= 0);
    }

    @Test
    public void test05_allCustomers() throws Exception {
        List<Map<String, Object>> data = (List<Map<String,Object>>) culqiCRUD.customers().get("data");
        assert(data.size() >= 0);
    }

    @Test
    public void test05_allCards() throws Exception {
        List<Map<String, Object>> data = (List<Map<String,Object>>) culqiCRUD.cards().get("data");
        assert(data.size() >= 0);
    }

    @Test
    public void test06_allSubscriptions() throws Exception {
        List<Map<String, Object>> data = (List<Map<String,Object>>) culqiCRUD.subscriptions().get("data");
        assert(data.size() >= 0);
    }

    @Test
    public void test07_allRefunds() throws Exception {
        List<Map<String, Object>> data = (List<Map<String,Object>>) culqiCRUD.refunds().get("data");
        assert(data.size() >= 0);
    }

    @Test
    public void test08_allEvents() throws Exception {
        List<Map<String, Object>> data = (List<Map<String,Object>>) culqiCRUD.events().get("data");
        assert(data.size() >= 0);
    }

    @Test
    public void test09_allTransfers() throws Exception {
        List<Map<String, Object>> data = (List<Map<String,Object>>) culqiCRUD.transfers().get("data");
        assert(data.size() >= 0);
    }

}
