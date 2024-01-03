package com.culqi.util.validation;
import java.util.List;
import java.util.Map;

import com.culqi.util.CountryCodes;

public class OrderValidation {
    public static void create(Map<String, Object> data) throws Exception {
        Map<String, Object> clientDetails = (Map<String, Object>) data.get("client_details");
        String first_name = (String) clientDetails.get("first_name");
        String last_name = (String) clientDetails.get("last_name");
        String phone_number = (String) clientDetails.get("phone_number");
        String email = (String) clientDetails.get("email");
        if (first_name == null || first_name.isEmpty()) {
            throw new Exception("first name is empty.");
        }

        if (last_name == null || last_name.isEmpty()) {
            throw new Exception("last name is empty.");
        }

        if (phone_number == null || phone_number.isEmpty()) {
            throw new Exception("address is empty.");
        }

        if (!Helper.isValidEmail(email)) {
            throw new CustomException("Invalid email.");
        }

        Helper.validateCurrencyCode((String) data.get("currency_code"));

        Object amountObj = data.get("amount");
        Helper.validateAmountValue(amountObj);

        if (!Helper.isFutureDate((long) data.get("expirationDate"))) {
            throw new Exception("expiration_date must be a future date.");
        }
    }

    public static void list(Map<String, Object> data) throws Exception {
        if (data.containsKey("amount")) {
            Object amountObj = data.get("amount");
            Helper.validateAmountValue(amountObj);
        }
        if (data.containsKey("min_amount")) {
            Object amountObj = data.get("min_amount");
            Helper.validateAmountValue(amountObj);
        }
        if (data.containsKey("max_amount")) {
            Object amountObj = data.get("max_amount");
            Helper.validateAmountValue(amountObj);
        }
        if (data.containsKey("creation_date_from") && data.containsKey("creation_date_to")) {
            Helper.validateDateFilter((String) data.get("creation_date_from"), (String) data.get("creation_date_to"));
        }
    }
}
