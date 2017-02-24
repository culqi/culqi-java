import com.culqi.Culqi;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by culqi on 24/02/17.
 */
public class CulqiAllTest extends TestCase {

    public Culqi init() {
        Culqi culqi = new Culqi();
        culqi.secret_key = "sk_test_UTCQSGcXW8bCyU59";
        return culqi;
    }

    protected Map<String, Object> tokens() throws Exception {
        Map<String, Object> token = new HashMap<String, Object>();
        token.put("limit", 50);
        return init().token.list(token);
    }

    protected Map<String, Object> charges() throws Exception {
        Map<String, Object> charge = new HashMap<String, Object>();
        charge.put("limit", 50);
        return init().charge.list(charge);
    }

    protected Map<String, Object> plans() throws Exception {
        Map<String, Object> plan = new HashMap<String, Object>();
        plan.put("limit", 50);
        return init().plan.list(plan);
    }

    protected Map<String, Object> customers() throws Exception {
        Map<String, Object> customer = new HashMap<String, Object>();
        customer.put("limit", 50);
        return init().customer.list(customer);
    }

    protected Map<String, Object> cards() throws Exception {
        Map<String, Object> card = new HashMap<String, Object>();
        card.put("limit", 50);
        return init().card.list(card);
    }

    protected Map<String, Object> subscriptions() throws Exception {
        Map<String, Object> subscription = new HashMap<String, Object>();
        subscription.put("limit", 50);
        return init().subscription.list(subscription);
    }

    @Test
    public void test1AllTokens() throws Exception {
        List<Map<String, Object>> data = (List<Map<String,Object>>) tokens().get("data");
        boolean valid = false;
        if(data.size() >= 0){
            valid = true;
        }
        assertTrue(valid);
    }

    @Test
    public void test2AllCharge() throws Exception {
        List<Map<String, Object>> data = (List<Map<String,Object>>) charges().get("data");
        boolean valid = false;
        if(data.size() >= 0){
            valid = true;
        }
        assertTrue(valid);
    }

    @Test
    public void test3AllPlan() throws Exception {
        List<Map<String, Object>> data = (List<Map<String,Object>>) plans().get("data");
        boolean valid = false;
        if(data.size() >= 0){
            valid = true;
        }
        assertTrue(valid);
    }

    @Test
    public void test4AllCustomers() throws Exception {
        List<Map<String, Object>> data = (List<Map<String,Object>>) customers().get("data");
        boolean valid = false;
        if(data.size() >= 0){
            valid = true;
        }
        assertTrue(valid);
    }

    @Test
    public void test5AllCards() throws Exception {
        List<Map<String, Object>> data = (List<Map<String,Object>>) cards().get("data");
        boolean valid = false;
        if(data.size() >= 0){
            valid = true;
        }
        assertTrue(valid);
    }

    @Test
    public void test5AllSubscriptions() throws Exception {
        List<Map<String, Object>> data = (List<Map<String,Object>>) subscriptions().get("data");
        boolean valid = false;
        if(data.size() >= 0){
            valid = true;
        }
        assertTrue(valid);
    }


}
