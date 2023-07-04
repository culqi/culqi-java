import java.util.HashMap;
import java.util.Map;

import com.culqi.Culqi;
import com.culqi.model.ResponseCulqi;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CulqiCRUD {
			
	String rsaPublicKey = "-----BEGIN PUBLIC KEY-----\n"
			+ "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDuCmwMoEzvBk++m4rZUlZL4pDD\n"
			+ "W++NV1tSjAOJsRv5Ermg3/ygjINNhi1gfMbfSiWloc85tJBZhXzD7JpOd7JxOOg7\n"
			+ "CicgbZKGF/sq2geoVw4+n4j4vUZx0+a1PgStwR+BeZn2I+eAn9xOrHJD6/baJqIO\n"
			+ "/ifGJ1e5jHeQXIR4IwIDAQAB\n"
			+ "-----END PUBLIC KEY-----";
	
	String rsaId = "30b83fd0-8709-4fe4-86c1-fef042c3c2c3"; 
	
	JsonData jsondata = new JsonData();
	
	ObjectMapper mapper = new ObjectMapper();

    public Culqi init() {
        Culqi culqi = new Culqi();
        culqi.public_key = "pk_test_90667d0a57d45c48";
        culqi.secret_key = "sk_test_1573b0e8079863ff";
        return culqi;
    }
    

    protected ResponseCulqi createToken() throws Exception {
        return init().token.create(jsondata.jsonToken());
    }
    
    protected ResponseCulqi updateToken() throws Exception {
        Map<String, Object> res = mapper.readValue(createToken().getBody(), new TypeReference<HashMap<String, Object>>(){});
        String id = res.get("id").toString();
        return init().token.update(jsondata.jsonUpdateToken(), id);
    }

    protected ResponseCulqi createTokenEncrypt() throws Exception {
        return init().token.create(jsondata.jsonToken(), rsaPublicKey, rsaId);
    }

    protected ResponseCulqi createTokenYape() throws Exception {
        return init().token.createYape(jsondata.jsonTokenYape());
    }

    protected ResponseCulqi createOrder(Boolean confirm) throws Exception {
        return init().order.create(jsondata.jsonOrder(confirm));
    }
    
    protected ResponseCulqi createOrderEncrypt(Boolean confirm) throws Exception {
        return init().order.create(jsondata.jsonOrder(confirm), rsaPublicKey, rsaId);
    }
    
    protected ResponseCulqi updateOrder() throws Exception {
    	Map<String, Object> res = mapper.readValue(createOrder(true).getBody(), new TypeReference<HashMap<String, Object>>(){});
        String id = res.get("id").toString();
        return init().order.update(jsondata.jsonUpdateOrder(), id);
    }

    protected ResponseCulqi confirmOrderType() throws Exception {
    	Map<String, Object> res = mapper.readValue(createOrder(false).getBody(), new TypeReference<HashMap<String, Object>>(){});
        String order_id = res.get("id").toString();
        return init().order.confirm_order_type(jsondata.jsonConfirmOrderType(order_id));
    }

    protected ResponseCulqi createCharge() throws Exception {
    	Map<String, Object> res = mapper.readValue(createToken().getBody(), new TypeReference<HashMap<String, Object>>(){});
        String source_id = res.get("id").toString();System.out.println("source_id "+source_id);
        return init().charge.create(jsondata.jsonCharge(source_id));
    }
    
    protected ResponseCulqi createChargeEncrypt() throws Exception {
    	Map<String, Object> res = mapper.readValue(createToken().getBody(), new TypeReference<HashMap<String, Object>>(){});
        String source_id = res.get("id").toString();
        return init().charge.create(jsondata.jsonCharge(source_id), rsaPublicKey, rsaId);
    }
    
    protected ResponseCulqi updateCharge() throws Exception {
    	Map<String, Object> res = mapper.readValue(createCharge().getBody(), new TypeReference<HashMap<String, Object>>(){});
        String id = res.get("id").toString();
        return init().token.update(jsondata.jsonUpdateCharge(), id);
    }
    
    protected ResponseCulqi createPlan() throws Exception {
        return init().plan.create(jsondata.jsonPlan());
    }
    
    protected ResponseCulqi updatePlan() throws Exception {
    	Map<String, Object> res = mapper.readValue(createPlan().getBody(), new TypeReference<HashMap<String, Object>>(){});
        String plan_id = res.get("id").toString();
        return init().plan.update(jsondata.jsonUpdatePlan(), plan_id);
    }

    protected ResponseCulqi createCustomer() throws Exception {
        return init().customer.create(jsondata.jsonCustomer());
    }
    
    protected ResponseCulqi updateCustomer() throws Exception {
    	Map<String, Object> res = mapper.readValue(createCustomer().getBody(), new TypeReference<HashMap<String, Object>>(){});
        String id = res.get("id").toString();
        return init().customer.update(jsondata.jsonUpdateCustomer(), id);
    }

    protected ResponseCulqi createCard() throws Exception {
    	Map<String, Object> res = mapper.readValue(createCustomer().getBody(), new TypeReference<HashMap<String, Object>>(){});
        String customer_id = res.get("id").toString();
        Map<String, Object> res2 = mapper.readValue(createToken().getBody(), new TypeReference<HashMap<String, Object>>(){});
        String token_id = res2.get("id").toString();
        return init().card.create(jsondata.jsonCard(customer_id,token_id));
    }
    
    protected ResponseCulqi updateCard() throws Exception {
    	Map<String, Object> res = mapper.readValue(createCard().getBody(), new TypeReference<HashMap<String, Object>>(){});
        String id = res.get("id").toString();
        return init().card.update(jsondata.jsonUpdateCard(), id);
    }

    protected ResponseCulqi createSubscription() throws Exception {
    	Map<String, Object> res = mapper.readValue(createCard().getBody(), new TypeReference<HashMap<String, Object>>(){});
        String card_id = res.get("id").toString();
        Map<String, Object> res2 = mapper.readValue(createPlan().getBody(), new TypeReference<HashMap<String, Object>>(){});
        String plan_id = res2.get("id").toString();
        return init().subscription.create(jsondata.jsonSubscription(card_id, plan_id));
    }
    
    protected ResponseCulqi updateSubscription() throws Exception {
    	Map<String, Object> res = mapper.readValue(createSubscription().getBody(), new TypeReference<HashMap<String, Object>>(){});
        String id = res.get("id").toString();
        return init().subscription.update(jsondata.jsonUpdateCard(), id);
    }

    protected ResponseCulqi createRefund() throws Exception {
    	Map<String, Object> res = mapper.readValue(createCharge().getBody(), new TypeReference<HashMap<String, Object>>(){});
        String charge_id = res.get("id").toString();
        return init().refund.create(jsondata.jsonRefund(charge_id));
    }
    
    protected ResponseCulqi updateRefund() throws Exception {
    	Map<String, Object> res = mapper.readValue(createRefund().getBody(), new TypeReference<HashMap<String, Object>>(){});
        String id = res.get("id").toString();
        return init().refund.update(jsondata.jsonUpdateRefund(), id);
    }
    
    //Listas
    
    protected ResponseCulqi tokens() throws Exception {
        return init().token.list(jsondata.jsonListTokens());
    }

    protected ResponseCulqi orders() throws Exception {
        return init().order.list();
    }

    protected ResponseCulqi charges() throws Exception {
        return init().charge.list(jsondata.jsonListCharges());
    }

    protected ResponseCulqi plans() throws Exception {
        return init().plan.list();
    }

    protected ResponseCulqi customers() throws Exception {
        return init().customer.list(jsondata.jsonListCustomers());
    }

    protected ResponseCulqi cards() throws Exception {
        return init().card.list(jsondata.jsonListCards());
    }

    protected ResponseCulqi subscriptions() throws Exception {
        return init().subscription.list(jsondata.jsonListSubscriptions());
    }

    protected ResponseCulqi refunds() throws Exception {
        return init().refund.list(jsondata.jsonListRefunds());
    }

    protected ResponseCulqi events() throws Exception {
        return init().event.list();
    }

    protected ResponseCulqi transfers() throws Exception {
        return init().transfer.list();
    }
}
