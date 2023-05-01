import java.util.Map;

import com.culqi.Culqi;

public class CulqiCRUD {
			
	String rsaPublicKey = "-----BEGIN PUBLIC KEY-----\n"
			+ "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDADka0Pt4SuWlHRA6kcJIwDde\n"
			+ "o67OYBEgQDEelmmixs9AlB/1bv446XOOE8eTJSridll2ZAn2nze7Gl2vQs0yW+4A\n"
			+ "XmszJwugM0lxTDiPdTXdbrA4VXiXDG29VLQCAxt1+/c7bE84hMS6cymWgEjYoa6I\n"
			+ "xX8u0ncLyiRUdZC2cwIDAQAB\n"
			+ "-----END PUBLIC KEY-----";
	
	String rsaId = "5243bad7-1d88-49c0-9699-f8ae156da58f"; 
	
	JsonData jsondata = new JsonData();

    public Culqi init() {
        Culqi culqi = new Culqi();
        culqi.public_key = "pk_test_387cc0e60fa9f7d4";
        culqi.secret_key = "sk_test_ff27818fc60ff66a";
        return culqi;
    }
    

    protected Map<String, Object> createToken() throws Exception {
        return init().token.create(jsondata.jsonToken());
    }
    
    protected Map<String, Object> updateToken() throws Exception {
        String id = createToken().get("id").toString();
        return init().token.update(jsondata.jsonUpdateToken(), id);
    }

    protected Map<String, Object> createTokenEncrypt() throws Exception {
        return init().token.create(jsondata.jsonToken(), rsaPublicKey, rsaId);
    }

    protected Map<String, Object> createTokenYape() throws Exception {
        return init().token.createYape(jsondata.jsonTokenYape());
    }

    protected Map<String, Object> createOrder(Boolean confirm) throws Exception {
        return init().order.create(jsondata.jsonOrder(confirm));
    }
    
    protected Map<String, Object> createOrderEncrypt(Boolean confirm) throws Exception {
        return init().order.create(jsondata.jsonOrder(confirm), rsaPublicKey, rsaId);
    }
    
    protected Map<String, Object> updateOrder() throws Exception {
        String id = createOrder(true).get("id").toString();
        return init().order.update(jsondata.jsonUpdateOrder(), id);
    }

    protected Map<String, Object> confirmOrderType() throws Exception {
        String order_id = createOrder(false).get("id").toString();
        return init().order.confirm_order_type(jsondata.jsonConfirmOrderType(order_id));
    }

    protected Map<String, Object> createCharge() throws Exception {
        String source_id = createToken().get("id").toString();
        return init().charge.create(jsondata.jsonCharge(source_id));
    }
    
    protected Map<String, Object> createChargeEncrypt() throws Exception {
    	String source_id = createToken().get("id").toString();
        return init().charge.create(jsondata.jsonCharge(source_id), rsaPublicKey, rsaId);
    }
    
    protected Map<String, Object> updateCharge() throws Exception {
        String id = createCharge().get("id").toString();
        return init().token.update(jsondata.jsonUpdateCharge(), id);
    }
    
    protected Map<String, Object> createPlan() throws Exception {
        return init().plan.create(jsondata.jsonPlan());
    }
    
    protected Map<String, Object> updatePlan() throws Exception {
        String plan_id = createPlan().get("id").toString();
        return init().plan.update(jsondata.jsonUpdatePlan(), plan_id);
    }

    protected Map<String, Object> createCustomer() throws Exception {
        return init().customer.create(jsondata.jsonCustomer());
    }
    
    protected Map<String, Object> updateCustomer() throws Exception {
        String id = createCustomer().get("id").toString();
        return init().customer.update(jsondata.jsonUpdateCustomer(), id);
    }

    protected Map<String, Object> createCard() throws Exception {
        String customer_id = createCustomer().get("id").toString();
        String token_id = createToken().get("id").toString();
        return init().card.create(jsondata.jsonCard(customer_id,token_id));
    }
    
    protected Map<String, Object> updateCard() throws Exception {
        String id = createCard().get("id").toString();
        return init().card.update(jsondata.jsonUpdateCard(), id);
    }

    protected Map<String, Object> createSubscription() throws Exception {
        String card_id = createCard().get("id").toString();
        String plan_id = createPlan().get("id").toString();
        return init().subscription.create(jsondata.jsonSubscription(card_id, plan_id));
    }
    
    protected Map<String, Object> updateSubscription() throws Exception {
        String id = createSubscription().get("id").toString();
        return init().subscription.update(jsondata.jsonUpdateCard(), id);
    }

    protected Map<String, Object> createRefund() throws Exception {
        String charge_id = createCharge().get("id").toString();
        return init().refund.create(jsondata.jsonRefund(charge_id));
    }
    
    protected Map<String, Object> updateRefund() throws Exception {
        String id = createRefund().get("id").toString();
        return init().refund.update(jsondata.jsonUpdateRefund(), id);
    }
    
    //Listas
    
    protected Map<String, Object> tokens() throws Exception {
        return init().token.list(jsondata.jsonListTokens());
    }

    protected Map<String, Object> orders() throws Exception {
        return init().order.list();
    }

    protected Map<String, Object> charges() throws Exception {
        return init().charge.list(jsondata.jsonListCharges());
    }

    protected Map<String, Object> plans() throws Exception {
        return init().plan.list();
    }

    protected Map<String, Object> customers() throws Exception {
        return init().customer.list(jsondata.jsonListCustomers());
    }

    protected Map<String, Object> cards() throws Exception {
        return init().card.list(jsondata.jsonListCards());
    }

    protected Map<String, Object> subscriptions() throws Exception {
        return init().subscription.list(jsondata.jsonListSubscriptions());
    }

    protected Map<String, Object> refunds() throws Exception {
        return init().refund.list(jsondata.jsonListRefunds());
    }

    protected Map<String, Object> events() throws Exception {
        return init().event.list();
    }

    protected Map<String, Object> transfers() throws Exception {
        return init().transfer.list();
    }
}
