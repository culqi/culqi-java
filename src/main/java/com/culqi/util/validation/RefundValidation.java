package com.culqi.util.validation;
import java.util.List;
import java.util.Map;

public class RefundValidation {
    public static void create(Map<String, Object> data) throws Exception {
        Helper.validateStringStart((String) data.get("charge_id"), "chr");
        List<String> allowedReasonValues = List.of("duplicado", "fraudulento", "solicitud_comprador");
        Helper.validateValue((String) data.get("reason"), allowedReasonValues);
        Object amountObj = data.get("amount");
        Helper.validateAmountValue(amountObj);
    }

    public static void list(Map<String, Object> data) throws Exception {
        if (data.containsKey("reason")) {
            List<String> allowedReasonValues = List.of("duplicado", "fraudulento", "solicitud_comprador");
            Helper.validateValue((String) data.get("reason"), allowedReasonValues);
        }

        if (data.containsKey("creation_date_from") && data.containsKey("creation_date_to")) {
            Helper.validateDateFilter((String) data.get("creation_date_from"), (String) data.get("creation_date_to"));
        }
    }
}
