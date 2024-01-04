import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import com.culqi.util.CurrencyCode;
import com.culqi.util.Reason;
import com.culqi.util.Util;

public class JsonData {

	protected Map<String, Object> jsonToken() throws Exception {
		Map<String, Object> token = new HashMap<String, Object>();
		Calendar date = new GregorianCalendar();
		int year = date.get(Calendar.YEAR);
		token.put("card_number", "4111111111111111");
		token.put("cvv", "123");
		token.put("email", "prueba_paul@culqi.com");
		token.put("expiration_month", "09");
		token.put("expiration_year", "2025");
		return token;
	}
	
	protected Map<String, Object> jsonUpdateToken() throws Exception {
        Map<String, Object> token = new HashMap<String, Object>();
        Map<String, Object> metadata = new HashMap<String, Object>();
        metadata.put("dni", "89941525");
        token.put("metadata", metadata);
        return token;
    }
	
	protected Map<String, Object> jsonListTokens() throws Exception {
        Map<String, Object> token = new HashMap<String, Object>();
        token.put("bin", "411111");
        return token;
    }

	protected Map<String, Object> jsonTokenYape() throws Exception {
		Map<String, Object> token = new HashMap<String, Object>();
		Map<String, Object> metadata = new HashMap<String, Object>();
		metadata.put("dni", "71702935");
		token.put("number_phone", "900000001");
		token.put("otp", "425251");
		token.put("amount", 700);
		token.put("metadata", metadata);
		return token;
	}

	protected Map<String, Object> jsonOrder(Boolean confirm) throws Exception {
		Map<String, Object> order = new HashMap<String, Object>();
		Map<String, Object> metadata = new HashMap<String, Object>();
		Map<String, Object> client_details = new HashMap<String, Object>();
		metadata.put("dni", "70000000");
		client_details.put("first_name", "prueba firstname");
		client_details.put("last_name", "prueba lastname");
		client_details.put("email", "prueba_paul2@gmail.com");
		client_details.put("phone_number", "51945145222");
		order.put("amount", 60000);
		order.put("currency_code", CurrencyCode.PEN);
		order.put("description", "Venta de prueba");
		order.put("order_number", "pedido-" + new Util().ramdomNumber());
		order.put("client_details", client_details);
		order.put("metadata", metadata);
		order.put("expiration_date", (System.currentTimeMillis() / 1000) + 24 * 60 * 60);
		order.put("confirm", confirm);
		return order;
	}
	
	protected Map<String, Object> jsonUpdateOrder() throws Exception {
        Map<String, Object> order = new HashMap<String, Object>();
        Map<String, Object> metadata = new HashMap<String, Object>();
        metadata.put("dni", "89941525");
        order.put("metadata", metadata);
        return order;
    }
	
	 protected Map<String, Object> jsonConfirmOrderType(String orderId) throws Exception {
	        Map<String, Object> order = new HashMap<String, Object>();
	        String[] order_types = { "cuotealo", "cip" };
	        order.put("order_id", orderId);
	        order.put("order_types", order_types);
	        return order;
	    }

	protected Map<String, Object> jsonCharge(String source_id) throws Exception {
		Map<String, Object> charge = new HashMap<String, Object>();
		Map<String, Object> metadata = new HashMap<String, Object>();
		metadata.put("oder_id", "1234");
		charge.put("amount", 300);
		charge.put("capture", false);
		charge.put("currency_code", CurrencyCode.PEN);
		charge.put("description", "Venta de prueba");
		charge.put("email", "prueba45@culqi.com");
		charge.put("installments", 0);
		charge.put("metadata", metadata);
		charge.put("source_id", source_id);
		return charge;
	}
	
	protected Map<String, Object> jsonUpdateCharge() throws Exception {
        Map<String, Object> cargo = new HashMap<String, Object>();
        Map<String, Object> metadata = new HashMap<String, Object>();
        metadata.put("documentNumber", "89941525");
        metadata.put("documentType", "1");
        cargo.put("metadata", metadata);
        return cargo;
    }
	
	protected Map<String, Object> jsonListCharges() throws Exception {
        Map<String, Object> charge = new HashMap<String, Object>();
        charge.put("currency_code", CurrencyCode.PEN);
        charge.put("limit",1);
        return charge;
    }

	protected Map<String, Object> jsonPlan() throws Exception {
		Map<String, Object> plan = new HashMap<String, Object>();
		Map<String, Object> metadata = new HashMap<String, Object>();
		metadata.put("order_id", "124");
		plan.put("amount", 1000);
		plan.put("currency_code", CurrencyCode.PEN);
		plan.put("interval", "dias");
		plan.put("interval_count", 30);
		plan.put("limit", 4);
		plan.put("metadata", metadata);
		plan.put("name", "plan-" + new Util().ramdonString());
		plan.put("trial_days", 15);
		return plan;
	}
	
	protected Map<String, Object> jsonUpdatePlan() throws Exception {
        Map<String, Object> plan = new HashMap<String, Object>();
        Map<String, Object> metadata = new HashMap<String, Object>();
        metadata.put("oder_id", "899");
        plan.put("metadata", metadata);
        return plan;
    }

	protected Map<String, Object> jsonCustomer() throws Exception {
		Map<String, Object> customer = new HashMap<String, Object>();
		customer.put("address", "Av Lima 123");
		customer.put("address_city", "Lima");
		customer.put("country_code", "PE");
		customer.put("email", "tst" + new Util().ramdonString() + "@culqi.com");
		customer.put("first_name", "Test");
		customer.put("last_name", "Cuqli");
		customer.put("phone_number", "99004356");
		return customer;
	}
	
	protected Map<String, Object> jsonUpdateCustomer() throws Exception {
        Map<String, Object> obj = new HashMap<String, Object>();
        Map<String, Object> metadata = new HashMap<String, Object>();
        metadata.put("dni", "89941525");
        obj.put("metadata", metadata);
        return obj;
    }
	
	protected Map<String, Object> jsonListCustomers() throws Exception {
        Map<String, Object> customer = new HashMap<String, Object>();
        customer.put("country_code", "PE");
        return customer;
    }

	protected Map<String, Object> jsonCard(String customerId, String tokenId) throws Exception {
		Map<String, Object> card = new HashMap<String, Object>();
		card.put("customer_id", customerId);
		card.put("token_id", tokenId);
		return card;
	}
	
	protected Map<String, Object> jsonUpdateCard() throws Exception {
        Map<String, Object> obj = new HashMap<String, Object>();
        Map<String, Object> metadata = new HashMap<String, Object>();
        metadata.put("dni", "89941525");
        obj.put("metadata", metadata);
        return obj;
    }
	
	protected Map<String, Object> jsonListCards() throws Exception {
        Map<String, Object> card = new HashMap<String, Object>();
        card.put("bin", "411111");
        return card;
    }

	protected Map<String, Object> jsonRefund(String chargeId) throws Exception {
		Map<String, Object> refund = new HashMap<String, Object>();
		refund.put("amount", 300);
		refund.put("charge_id", chargeId);
		refund.put("reason", Reason.solicitud_comprador);
		return refund;
	}
	
	protected Map<String, Object> jsonUpdateRefund() throws Exception {
        Map<String, Object> obj = new HashMap<String, Object>();
        Map<String, Object> metadata = new HashMap<String, Object>();
        metadata.put("dni", "89941525");
        obj.put("metadata", metadata);
        return obj;
    }
	
	protected Map<String, Object> jsonListRefunds() throws Exception {
        Map<String, Object> refund = new HashMap<String, Object>();
        refund.put("reason", Reason.solicitud_comprador);
        return refund;
    }

	 protected Map<String, Object> jsonSubscription(String cardId, String planId) throws Exception {
        Map<String, Object> subscription = new HashMap<String, Object>();
        subscription.put("card_id",cardId);
        subscription.put("plan_id",planId);
        return subscription;
    }
	 
	 protected Map<String, Object> jsonUpdateSubscription() throws Exception {
        Map<String, Object> obj = new HashMap<String, Object>();
        Map<String, Object> metadata = new HashMap<String, Object>();
        metadata.put("dni", "89941525");
        obj.put("metadata", metadata);
        return obj;
    }
	 
	 protected Map<String, Object> jsonListSubscriptions() throws Exception {
        Map<String, Object> subscription = new HashMap<String, Object>();
        subscription.put("min_amount", 500);
        subscription.put("max_amount", 900);
        return subscription;
    }
}
