package com.culqi.util.validation;
import java.util.Map;

public class SubscriptionValidation {
    public static void create(Map<String, Object> data) throws Exception {
        Helper.validateStringStart((String) data.get("card_id"), "crd");
        Helper.validateStringStart((String) data.get("plan_id"), "pln");
    }

    public static void list(Map<String, Object> data) throws Exception {
        if (data.containsKey("plan_id")) {
            Helper.validateStringStart((String) data.get("plan_id"), "pln");
        }

        if (data.containsKey("creation_date_from") && data.containsKey("creation_date_to")) {
            Helper.validateDateFilter((String) data.get("creation_date_from"), (String) data.get("creation_date_to"));
        }
    }
}
