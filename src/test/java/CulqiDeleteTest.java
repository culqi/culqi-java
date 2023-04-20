import junit.framework.TestCase;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import java.util.Map;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CulqiDeleteTest extends TestCase {
	
	CulqiCRUD culqiCRUD = new CulqiCRUD();

    
    // ELIMINAR RECURSOS

    @Test
    public void test01_deleteSubscription() throws Exception {
        Map<String, Object> subscriptionDeleted = culqiCRUD.init().subscription.delete(culqiCRUD.createSubscription().get("id").toString());
        assertTrue(Boolean.valueOf(subscriptionDeleted.get("deleted").toString()));
    }

    @Test
    public void test02_deletePlan() throws Exception {
        Map<String, Object> planDeleted = culqiCRUD.init().plan.delete(culqiCRUD.createPlan().get("id").toString());
        assertTrue(Boolean.valueOf(planDeleted.get("deleted").toString()));
    }

    @Test
    public void test03_deleteCard() throws Exception {
        Map<String, Object> cardDeleted = culqiCRUD.init().card.delete(culqiCRUD.createCard().get("id").toString());
        assertTrue(Boolean.valueOf(cardDeleted.get("deleted").toString()));
    }

    @Test
    public void test04_deleteCustomer() throws Exception {
        Map<String, Object> customerDeleted = culqiCRUD.init().customer.delete(culqiCRUD.createCustomer().get("id").toString());
        assertTrue(Boolean.valueOf(customerDeleted.get("deleted").toString()));
    }

    @Test
    public void test05_deleteOrder() throws Exception {
        Map<String, Object> orderDeleted = culqiCRUD.init().order.delete(culqiCRUD.createOrder(true).get("id").toString());
        assertTrue(Boolean.valueOf(orderDeleted.get("deleted").toString()));
    }

}
