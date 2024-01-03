package com.culqi.util.validation;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PlanValidation {
    public static void create(Map<String, Object> data) throws Exception {
        List<String> allowedValues = Arrays.asList("dias", "semanas", "meses", "años");
        Helper.validateValue((String) data.get("reason"), allowedValues);
        Object amountObj = data.get("amount");
        Helper.validateAmountValue(amountObj);
        Helper.validateCurrencyCode((String) data.get("currency_code"));
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
