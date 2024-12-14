package com.culqi.apioperation.service;

import java.util.Map;

import com.culqi.apioperation.ObjectResult;
import com.culqi.model.ResponseCulqi;
import com.culqi.util.validation.*;

/**
 * Created by culqi on 12/21/16.
 */

public class Token extends Generic {

    private static final String URL = "/tokens/";
    private static final String URL_YAPE = "/tokens/yape";
    
    public Token() {
    	super(URL);
    }

    public ResponseCulqi createYape(Map<String, Object> body) throws Exception {
        Map<String, String> validationResponse = verifyClassValidationYape(body);
        if (validationResponse != null) {
            ResponseCulqi response = new ResponseCulqi();
            response.setStatusCode(400);
            response.setBody(mapper.writeValueAsString(validationResponse));
            return response;
        }
        return new ObjectResult().create(body, this.URL_YAPE);
    }

    private static Map<String, String> verifyClassValidationYape(Map<String, Object> body) throws Exception {
        try {
            TokenValidation.createTokenYapeValidation(body);
        } catch (CustomException e) {
            return e.getErrorData();
        }
        return null;
    }

}
