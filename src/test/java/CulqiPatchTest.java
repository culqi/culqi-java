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

    public Culqi init() {
        Culqi culqi = new Culqi();
        culqi.public_key = "pk_test_vzMuTHoueOMlgUPj";
        culqi.secret_key = "sk_test_UTCQSGcXW8bCyU59";
        return culqi;
    }

    protected Map<String, Object> updateplan() throws Exception {
        Map<String, Object> plan = new HashMap<String, Object>();
        Map<String, Object> metadata = new HashMap<String, Object>();
        metadata.put("oder_id", "899");
        plan.put("metadata", metadata);
        return init().plan.update(plan, "pln_test_pLFzcWkwj33xFGF1");
    }

    @Test
    public void test1UpdatePlan() throws Exception {
        assertEquals("plan", updateplan().get("object").toString());
    }

}
