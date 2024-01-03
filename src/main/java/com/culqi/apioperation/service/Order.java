package com.culqi.apioperation.service;

import java.util.Map;

import com.culqi.apioperation.ObjectResult;
import com.culqi.model.ResponseCulqi;
import com.culqi.util.validation.CustomException;
import com.culqi.util.validation.Helper;
import com.culqi.util.validation.TokenValidation;

/**
 * Created by culqi on 12/22/16.
 */

public class Order extends Generic {

    private static final String URL = "/orders/";
    
    public Order() {
    	super(URL);
    }

    public ResponseCulqi confirm(String id) throws Exception {
        Map<String, String> validationResponse = verifyClassValidationConfirm(id);
        if (validationResponse != null) {
            ResponseCulqi response = new ResponseCulqi();
            response.setStatusCode(400);
            response.setBody(mapper.writeValueAsString(validationResponse));
            return response;
        }
        return new ObjectResult().confirm(this.URL, id);
    }

    public ResponseCulqi confirm_order_type(Map<String, Object> body) throws Exception {
        String id = (String)body.get("order_id");
        Map<String, String> validationResponse = verifyClassValidationConfirm(id);
        if (validationResponse != null) {
            ResponseCulqi response = new ResponseCulqi();
            response.setStatusCode(400);
            response.setBody(mapper.writeValueAsString(validationResponse));
            return response;
        }
        return new ObjectResult().create(body, this.URL+"confirm");
    }
    
    private static Map<String, String> verifyClassValidationConfirm(String id) throws Exception {
        try {
            Helper.validateStringStart(id, "ord");
        } catch (CustomException e) {
            return e.getErrorData();
        }
        return null;
    }

}
