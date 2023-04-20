import junit.framework.TestCase;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;



@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CulqiPatchTest extends TestCase {

	CulqiCRUD culqiCRUD = new CulqiCRUD();


	@Test
    public void test01_updateToken() throws Exception {
        assertEquals("token", culqiCRUD.updateToken().get("object").toString());
    }
	
	@Test
    public void test02_updateCharge() throws Exception {
        assertEquals("charge", culqiCRUD.updateCharge().get("object").toString());
    }
	 
	@Test
    public void test03_updateRefund() throws Exception {
        assertEquals("refund", culqiCRUD.updateCharge().get("object").toString());
    }
	
	@Test
    public void test04_updateCustomer() throws Exception {
        assertEquals("customer", culqiCRUD.updateCustomer().get("object").toString());
    }
	
	@Test
    public void test05_updateCard() throws Exception {
        assertEquals("card", culqiCRUD.updateCard().get("object").toString());
    }
	
	@Test
    public void test06_updatePlan() throws Exception {
        assertEquals("plan", culqiCRUD.updatePlan().get("object").toString());
    }
	
	@Test
    public void test07_updateSubscription() throws Exception {
        assertEquals("subscription", culqiCRUD.updateSubscription().get("object").toString());
    }
	
	@Test
    public void test08_updateOrder() throws Exception {
        assertEquals("order", culqiCRUD.updateOrder().get("object").toString());
    }

}
