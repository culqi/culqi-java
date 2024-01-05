package com.culqi.util.validation;

import java.util.HashMap;
import java.util.Map;

public class CustomException extends Exception {
    private Map<String, String> errorData;

    public CustomException(String merchantMessage) {
        super(merchantMessage);
        errorData = new HashMap<>();
        errorData.put("object", "error");
        errorData.put("type", "param_error");
        errorData.put("merchant_message", merchantMessage);
        errorData.put("user_message", merchantMessage);
    }

    public Map<String, String> getErrorData() {
        return errorData;
    }
}

