import com.culqi.Culqi;
import com.culqi.util.*;
import junit.framework.TestCase;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Created by culqi on 1/4/17.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CulqiTest extends TestCase {

    Culqi culqi = new Culqi("pk_test_vzMuTHoueOMlgUPj","sk_test_UTCQSGcXW8bCyU59");

    protected Result token_id() throws Exception {
        Result resultToken = culqi.createToken("4111111111111111","PEN", "123",9,2020,"q352454534",
                "Aguirre","waguirre@me.com", "Willy");
        return resultToken;
    }

    protected Result charge_id() throws Exception {
        Result resultCharge = culqi.createCharge("Avenida Lima 1232", "LIMA", 1000, "PE", "PEN", "waguirre@me.com",
                "Willy", 0, "Aguirre", "", 3333339, "Venta de prueba", token_id().getId());
        return resultCharge;
    }

    protected Result plan_alias() throws Exception {
        Result resultAlias = culqi.createPlan("plan-"+new Util().ramdonString(), 1000, "PEN", "day", 2,10,
                "Plan "+new Util().ramdonString(), 30);
        return resultAlias;
    }

    protected Result subscription() throws Exception {
        Result resultSubscription = culqi.createSubscription("Avenida Lima 123213", "LIMA", "PE", "waguirre@me.com", "Aguirre",
                "Willy", 1234567789, plan_alias().getMessage(), token_id().getId());
        return resultSubscription;
    }

    protected Result refund() throws Exception {
        Result resultRefund = culqi.createRefund(900, charge_id().getId(), "give me my money back!");
        return resultRefund;
    }

    @Test
    public void test1ValidCreateToken() throws Exception {
         assertEquals("token", token_id().getObject());
    }

    @Test
    public void test2ValidCreateCharge() throws Exception {
        assertEquals("charge", charge_id().getObject());
    }

    @Test
    public void test3ValidCreatePlan() throws Exception {
        assertEquals("plan", plan_alias().getObject());
    }

    @Test
    public void test4ValidCreateSubscription() throws Exception {
        assertEquals("subscription", subscription().getObject());
    }

    @Test
    public void test5ValidCreateRefund() throws Exception {
        assertEquals("refund", refund().getObject());
    }

}
