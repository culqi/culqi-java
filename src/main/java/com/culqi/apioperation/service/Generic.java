package com.culqi.apioperation.service;

import java.io.IOException;
import java.util.Map;

import com.culqi.apioperation.All;
import com.culqi.apioperation.Create;
import com.culqi.apioperation.Find;
import com.culqi.apioperation.ObjectResult;
import com.culqi.model.ResponseCulqi;
import com.culqi.util.validation.*;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Generic implements All, Create, Find {

    private String URL = "";
    
    public Generic(String url) {
    	this.URL = url;
    }

    ObjectMapper mapper = new ObjectMapper();

    public ResponseCulqi list(Map<String, Object> params) throws Exception {
        params = (params == null || params.size() == 0) ? null : params;
        Map<String, String> validationResponse = verifyClassValidationList(params, this.URL);
        if (validationResponse != null) {
            ResponseCulqi response = new ResponseCulqi();
            response.setStatusCode(400);
            response.setBody(mapper.writeValueAsString(validationResponse));
            System.out.println("Error : " + response);
            return response;
        }

        String url = this.URL;
        if (!url.contains("plans") || !url.contains("subscriptions")) {
            // Realiza el replace solo si no contiene las subcadenas
            url.replace("/", "");
        }

        return new ObjectResult().list(url, params);
    }

    public ResponseCulqi list() throws Exception {
        return new ObjectResult().list(this.URL.replace("/",""), null);
    }

    public ResponseCulqi create(Map<String, Object> body) throws Exception {
        Map<String, String> validationResponse = verifyClassValidationCreate(body, this.URL);
        if (validationResponse != null) {
            ResponseCulqi response = new ResponseCulqi();
            response.setStatusCode(400);
            response.setBody(mapper.writeValueAsString(validationResponse));
            System.out.println("Error : " + response);
            return response;
        }
        return new ObjectResult().create(body, this.URL);
    }

    public ResponseCulqi create(Map<String, Object> body, String rsaPublicKey, String rsaId) throws Exception {
        return new ObjectResult().create(body, this.URL, rsaPublicKey, rsaId);
    }

    public ResponseCulqi get(String id) throws Exception {
        Map<String, String> validationResponse = verifyClassValidationUpdate(id, this.URL);
        if (validationResponse != null) {
            ResponseCulqi response = new ResponseCulqi();
            response.setStatusCode(400);
            response.setBody(mapper.writeValueAsString(validationResponse));
            System.out.println("Error : " + response);
            return response;
        }
        return new ObjectResult().get_or_delete(this.URL, id, false);
    }
    
    public ResponseCulqi update(Map<String, Object> body, String id) throws Exception {
        Map<String, String> validationResponse = validatePayloadUpdate(id, this.URL, body);
        if (validationResponse != null) {
            ResponseCulqi response = new ResponseCulqi();
            response.setStatusCode(400);
            response.setBody(mapper.writeValueAsString(validationResponse));
            System.out.println("Error : " + response);
            return response;
        }
        return new ObjectResult().update(body, this.URL, id);
    }

    public ResponseCulqi update(Map<String, Object> body, String id, String rsaPublicKey, String rsaId)
            throws Exception {
        Map<String, String> validationResponse = validatePayloadUpdate(id, this.URL, body);
        if (validationResponse != null) {
            ResponseCulqi response = new ResponseCulqi();
            response.setStatusCode(400);
            response.setBody(mapper.writeValueAsString(validationResponse));
            System.out.println("Error : " + response);
            return response;
        }
        return new ObjectResult().update(body, this.URL, id, rsaPublicKey, rsaId);
    }
    
    public ResponseCulqi delete(String id) throws Exception {
        Map<String, String> validationResponse = verifyClassValidationUpdate(id, this.URL);
         if (validationResponse != null) {
            ResponseCulqi response = new ResponseCulqi();
            response.setStatusCode(400);
            response.setBody(mapper.writeValueAsString(validationResponse));
            System.out.println("Error : " + response);
            return response;
        }
        return new ObjectResult().get_or_delete(this.URL, id, true);
    }

    private static Map<String, String> verifyClassValidationCreate(Map<String, Object> body, String url) throws Exception {
        try {
            if (url.contains("tokens")) {
                TokenValidation.create(body);
            }
            if (url.contains("charges")) {
                ChargeValidation.create(body);
            }
            if (url.contains("refunds")) {
                RefundValidation.create(body);
            }
            if (url.contains("plans")) {
                PlanValidation.create(body);
            }
            if (url.contains("customers")) {
                CustomerValidation.create(body);
            }
            if (url.contains("cards")) {
                CardValidation.create(body);
            }
            if (url.contains("subscriptions")) {
                SubscriptionValidation.create(body);
            }
            if (url.contains("orders")) {
                OrderValidation.create(body);
            }
        } catch (CustomException e) {
            return e.getErrorData();
        }
        return null;
    }

    private static Map<String, String> validatePayloadUpdate(String id, String url, Map<String, Object> body)
            throws Exception {
        try {
            if (url.contains("plans")) {
                Helper.validateStringStart(id, "pln");
                PlanValidation.update(body);
            }
            if (url.contains("subscriptions")) {
                Helper.validateStringStart(id, "sxn");
                System.out.println("validacion id paso");
                SubscriptionValidation.update(body);
            }
        } catch (CustomException e) {
            System.out.println(e.getErrorData());
            return e.getErrorData();
        }
        return null;
    }

    private static Map<String, String> verifyClassValidationUpdate(String id, String url) throws Exception {
        try {
            if (url.contains("tokens")) {
                Helper.validateStringStart(id, "tkn");
            }
            if (url.contains("charges")) {
                Helper.validateStringStart(id, "chr");
            }
            if (url.contains("refunds")) {
                Helper.validateStringStart(id, "ref");
            }
            if (url.contains("plans")) {
                Helper.validateStringStart(id, "pln");
            }
            if (url.contains("customers")) {
                Helper.validateStringStart(id, "cus");
            }
            if (url.contains("cards")) {
                Helper.validateStringStart(id, "crd");
            }
            if (url.contains("subscriptions")) {
                Helper.validateStringStart(id, "sxn");
            }
            if (url.contains("orders")) {
                Helper.validateStringStart(id, "ord");
            }
        } catch (CustomException e) {
            return e.getErrorData();
        }
        return null;
    }
    
    private static Map<String, String> verifyClassValidationList(Map<String, Object> params, String url) throws Exception {
        try {
            if (url.contains("tokens")) {
                TokenValidation.list(params);
            }
            if (url.contains("charges")) {
                ChargeValidation.list(params);
            }
            if (url.contains("refunds")) {
                RefundValidation.list(params);
            }
            if (url.contains("plans")) {
                PlanValidation.list(params);
            }
            if (url.contains("customers")) {
                CustomerValidation.list(params);
            }
            if (url.contains("cards")) {
                CardValidation.list(params);
            }
            if (url.contains("subscriptions")) {
                SubscriptionValidation.list(params);
            }
            if (url.contains("orders")) {
                OrderValidation.list(params);
            }
        } catch (CustomException e) {
            return e.getErrorData();
        }
        return null; // No validation errors
    }

}
