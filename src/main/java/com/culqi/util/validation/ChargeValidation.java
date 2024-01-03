package com.culqi.util.validation;
import java.util.List;
import java.util.Map;

import com.culqi.util.CountryCodes;

public class ChargeValidation {
    public static void create(Map<String, Object> data) throws Exception {
        String email = (String) data.get("email");
        if (!Helper.isValidEmail(email)) {
            throw new CustomException("Invalid email.");
        }
        Object amountObj = data.get("amount");
        Helper.validateAmountValue(amountObj);

       Helper.validateCurrencyCode((String) data.get("currency_code"));

       String sourceId = (String) data.get("source_id");

       if (sourceId.startsWith("tkn")) {
           Helper.validateStringStart(sourceId, "tkn");
       } else if (sourceId.startsWith("ype")) {
           Helper.validateStringStart(sourceId, "ype");
       } else if (sourceId.startsWith("crd")) {
           Helper.validateStringStart(sourceId, "crd");
       } else {
           throw new CustomException("Incorrect format. The format must start with tkn, ype, or crd.");
       }
    }

    public static void list(Map<String, Object> data) throws Exception {
        if (data.containsKey("currency_code")) {
            Helper.validateCurrencyCode((String) data.get("currency_code"));
        }
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
        if (data.containsKey("installments")) {
            Object amountObj = data.get("installments");
            Helper.validateAmountValue(amountObj);
        }
        if (data.containsKey("min_installments")) {
            Object amountObj = data.get("min_installments");
            Helper.validateAmountValue(amountObj);
        }
        if (data.containsKey("max_installments")) {
            Object amountObj = data.get("max_installments");
            Helper.validateAmountValue(amountObj);
        }
        if (data.containsKey("email")) {
            String email = (String) data.get("email");
            if (!Helper.isValidEmail(email)) {
                throw new CustomException("Invalid email.");
            }
        }
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
