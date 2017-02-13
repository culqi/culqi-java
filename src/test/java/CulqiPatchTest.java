import com.culqi.Culqi;
import junit.framework.TestCase;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by culqi on 13/02/17.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CulqiPatchTest extends TestCase {

    Culqi culqi = new Culqi("","sk_test_UTCQSGcXW8bCyU59");

    protected Map<String, Object> updateplan() throws Exception {
        Map<String, Object> plan = new HashMap<String, Object>();
        Map<String, Object> metadata = new HashMap<String, Object>();
        metadata.put("oder_id", "899");
        plan.put("metadata", metadata);
        return culqi.plan.update(plan, "pln_test_pLFzcWkwj33xFGF1");
    }

    @Test
    public void test1UpdatePlan() throws Exception {
        assertEquals("plan", updateplan().get("object").toString());
    }

}
