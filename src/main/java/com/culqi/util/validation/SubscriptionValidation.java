package com.culqi.util.validation;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SubscriptionValidation {
    public static void create(Map<String, Object> data) throws Exception {

        List<String> requiredFields = Arrays.asList(
            "card_id",
            "plan_id",
            "tyc"
        );

        Helper.additionalValidation(data, requiredFields, null);
        Helper.validatePayloadCreateSubscription(data);

        Integer GENERATED_ID = 25;
        // Validate card_id format
        if (!Helper.validValue(data.get("card_id"), false) ||
                data.get("card_id").toString().length() != GENERATED_ID) {
            throw new CustomException(
                    "El campo 'card_id' es inválido. La longitud debe ser de 25 caracteres.");
        }
        Helper.validateStringStart((String) data.get("card_id"), "crd");

        // Validate plan_id format
        if (!Helper.validValue(data.get("plan_id"), false) ||
                data.get("plan_id").toString().length() != GENERATED_ID) {
            throw new CustomException(
                    "El campo 'plan_id' es inválido. La longitud debe ser de 25 caracteres.");
        }
        Helper.validateStringStart((String) data.get("plan_id"), "pln");


        // Validate parameter: metadata
        if (data.containsKey("metadata")) {
            Helper.validateMetadataSchema((Map<String, Object>) data.get("metadata"));
        }

        // Validate parameter: tyc
        if (!data.containsKey("tyc") || !(Boolean.FALSE.equals(data.get("tyc"))
                        || Boolean.TRUE.equals(data.get("tyc")))) {
            throw new CustomException(
                    "El campo 'tyc' es inválido o está vacío. El valor debe ser un booleano.");
        }

    }

    public static void list(Map<String, Object> data) throws Exception {

        Helper.validatePayloadFilterSubscription(data);
        List<Integer> SUBSCRIPTION_STATUS = Arrays.asList(1, 2, 3, 4, 5, 6, 8);
        // Validar parámetro: status
        if (data.containsKey("status")) {
            if (!SUBSCRIPTION_STATUS.contains(data.get("status"))) {
                throw new CustomException(
                        "El filtro 'status' tiene un valor inválido o está vacío. Estos son los únicos valores permitidos: [1, 2, 3, 4, 5, 6, 8].");
            }
        }

        if (data.containsKey("limit")) {
            if (!Helper.validValue(data.get("limit"), true) ||
                    Helper.validateRangeParameters(data.get("limit"), 1, 100, true)) {
                throw new CustomException(
                        "El filtro 'limit' admite valores en el rango 1 a 100.");
            }
        }

        Integer GENERATED_ID = 25;
        if (data.containsKey("plan_id")) {
            if (!Helper.validValue(data.get("plan_id"), false) ||
                    data.get("plan_id").toString().length() != GENERATED_ID) {
                throw new CustomException(
                        "El campo 'plan_id' es inválido. La longitud debe ser de 25 caracteres.");
            }
            Helper.validateStringStart((String) data.get("plan_id"), "pln");
        }
        if (data.containsKey("before")) {
            if (!Helper.validValue(data.get("before"), false) ||
                    data.get("before").toString().length() != GENERATED_ID) {
                throw new CustomException(
                        "El campo 'before' es inválido. La longitud debe ser de 25 caracteres.");
            }
        }

        if (data.containsKey("after")) {
            if (!Helper.validValue(data.get("after"), false) ||
                    data.get("after").toString().length() != GENERATED_ID) {
                throw new CustomException(
                        "El campo 'after' es inválido. La longitud debe ser de 25 caracteres.");
            }
        }

        if (data.containsKey("creation_date_from")) {
            Object creationDateFrom = data.get("creation_date_from");
        
            if (String.valueOf(creationDateFrom).length() != 10 && String.valueOf(creationDateFrom).length() != 13) {
                throw new CustomException(
                    "El campo 'creation_date_from' debe tener una longitud diferente de 10 o 13 caracteres.");
            }
        }

        if (data.containsKey("creation_date_to")) {
            Object creationDateTo = data.get("creation_date_to");
        
            if (String.valueOf(creationDateTo).length() != 10 && String.valueOf(creationDateTo).length() != 13) {
                throw new CustomException(
                    "El campo 'creation_date_to' debe tener una longitud diferente de 10 o 13 caracteres.");
            }
        }

    }

    public static void update(Map<String, Object> data) throws Exception {
        List<String> requiredFields = Arrays.asList(
            "card_id"
        );

        Helper.additionalValidation(data, requiredFields, null);
        Helper.validatePayloadUpdateSubscription(data);
        Integer GENERATED_ID = 25;
        // Validate card_id format
        if (!Helper.validValue(data.get("card_id"), false) ||
                data.get("card_id").toString().length() != GENERATED_ID || !data.containsKey("card_id")) {
            throw new CustomException(
                    "El campo 'card_id' es inválido. La longitud debe ser de 25 caracteres.");
        }

        Helper.validateStringStart(data.get("card_id").toString(), "crd");
        // Validate parameter: metadata
        if (data.containsKey("metadata")) {
            Helper.validateMetadataSchema((Map<String, Object>) data.get("metadata"));
        }
    }
}
