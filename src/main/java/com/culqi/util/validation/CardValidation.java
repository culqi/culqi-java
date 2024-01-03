package com.culqi.util.validation;
import java.util.List;
import java.util.Map;

import com.culqi.util.CountryCodes;

public class CardValidation {
    public static void create(Map<String, Object> data) throws Exception {
        Helper.validateStringStart((String) data.get("customer_id"), "cus");
        Helper.validateStringStart((String) data.get("token_id"), "tkn");
    }

    public static void list(Map<String, Object> data) throws Exception {
        if (data.containsKey("card_brand")) {
            List<String> allowedBrandValues = List.of("Visa", "Mastercard", "Amex", "Diners");
            Helper.validateValue((String) data.get("card_brand"), allowedBrandValues);
        }

        if (data.containsKey("card_type")) {
            List<String> allowedCardTypeValues = List.of("credito", "debito", "internacional");
            Helper.validateValue((String) data.get("card_type"), allowedCardTypeValues);
        }

        if (data.containsKey("country_code")) {
            List<String> countryCodes = CountryCodes.getCountryCodes();
            Helper.validateValue((String) data.get("country_code"), countryCodes);
        }

        if (data.containsKey("creation_date_from") && data.containsKey("creation_date_to")) {
            Helper.validateDateFilter((String) data.get("creation_date_from"), (String) data.get("creation_date_to"));
        }
    }
}
