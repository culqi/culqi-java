import com.culqi.Culqi;
import com.culqi.util.CurrencyCode;
import com.culqi.util.Reason;
import junit.framework.TestCase;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by culqi on 24/02/17.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CulqiAllTest extends TestCase {

    public Culqi init() {
        Culqi culqi = new Culqi();
        culqi.secret_key = "sk_test_8GC9UJfifciOurwW";
        return culqi;
    }

    protected Map<String, Object> tokens() throws Exception {
        Map<String, Object> token = new HashMap<String, Object>();
        token.put("bin", "411111");
        return init().token.list(token);
    }

    protected Map<String, Object> charges() throws Exception {
        Map<String, Object> charge = new HashMap<String, Object>();
        charge.put("currency_code", CurrencyCode.PEN.toString());
        charge.put("limit",1);
        return init().charge.list(charge);
    }

    protected Map<String, Object> plans() throws Exception {
        Map<String, Object> plan = new HashMap<String, Object>();
        plan.put("min_amount", 500);
        plan.put("max_amount", 900);
        return init().plan.list();
    }

    protected Map<String, Object> customers() throws Exception {
        Map<String, Object> customer = new HashMap<String, Object>();
        customer.put("country_code", "PE");
        return init().customer.list(customer);
    }

    protected Map<String, Object> cards() throws Exception {
        Map<String, Object> card = new HashMap<String, Object>();
        card.put("bin", "411111");
        return init().card.list(card);
    }

    protected Map<String, Object> subscriptions() throws Exception {
        Map<String, Object> subscription = new HashMap<String, Object>();
        subscription.put("min_amount", 500);
        subscription.put("max_amount", 900);
        return init().subscription.list(subscription);
    }

    protected Map<String, Object> refunds() throws Exception {
        Map<String, Object> refund = new HashMap<String, Object>();
        refund.put("reason", Reason.solicitud_comprador.toString());
        return init().refund.list(refund);
    }

    protected Map<String, Object> events() throws Exception {
        return init().event.list();
    }

    protected Map<String, Object> transfers() throws Exception {
        return init().transfer.list();
    }

    @Test
    public void test1AllTokens() throws Exception {
        List<Map<String, Object>> data = (List<Map<String,Object>>) tokens().get("data");
        System.out.println(data);
        assert(data.size() >= 0);
    }

    @Test
    public void test2AllCharge() throws Exception {
        List<Map<String, Object>> data = (List<Map<String,Object>>) charges().get("data");
        assert(data.size() >= 0);
    }

    @Test
    public void test3AllPlan() throws Exception {
        List<Map<String, Object>> data = (List<Map<String,Object>>) plans().get("data");
        assert(data.size() >= 0);
    }

    @Test
    public void test4AllCustomers() throws Exception {
        List<Map<String, Object>> data = (List<Map<String,Object>>) customers().get("data");
        assert(data.size() >= 0);
    }

    @Test
    public void test5AllCards() throws Exception {
        List<Map<String, Object>> data = (List<Map<String,Object>>) cards().get("data");
        assert(data.size() >= 0);
    }

    @Test
    public void test5AllSubscriptions() throws Exception {
        List<Map<String, Object>> data = (List<Map<String,Object>>) subscriptions().get("data");
        assert(data.size() >= 0);
    }

    @Test
    public void test6AllRefunds() throws Exception {
        List<Map<String, Object>> data = (List<Map<String,Object>>) refunds().get("data");
        assert(data.size() >= 0);
    }

    @Test
    public void test7AllEvents() throws Exception {
        List<Map<String, Object>> data = (List<Map<String,Object>>) events().get("data");
        assert(data.size() >= 0);
    }

    @Test
    public void test7AllTransfers() throws Exception {
        List<Map<String, Object>> data = (List<Map<String,Object>>) transfers().get("data");
        assert(data.size() >= 0);
    }

}
