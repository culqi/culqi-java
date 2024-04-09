package com.culqi.util.validation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class PlanValidation {

    static String REGEX_IMAGE = "^(http://www\\.|https://www\\.|http://|https://)?[a-z0-9]+([-.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(/.*)?$";

    public static void create(Map<String, Object> data) throws Exception {
        // Validate payload
        List<String> requiredFields = Arrays.asList(
                "interval_unit_time",
                "interval_count",
                "amount",
                "name",
                "description",
                "short_name",
                "currency",
                "initial_cycles");

        Helper.additionalValidation(data, requiredFields, null);
        Helper.validatePayloadCreatePlan(data);

        // Validate parameter: interval_unit_time
        List<Integer> allowedValues = Arrays.asList(1, 2, 3, 4, 5, 6);
        if (!Helper.validValue(data.get("interval_unit_time"), true) ||
                !allowedValues.contains(data.get("interval_unit_time"))) {
            throw new CustomException(
                    "El campo 'interval_unit_time' tiene un valor inválido o está vacío. Estos son los únicos valores permitidos: [ 1, 2, 3, 4, 5, 6].");
        }

        if (Helper.validateRangeParameters(data.get("interval_count"), 0, 9999, true) ||
                !Helper.validValue(data.get("interval_unit_time"), true)) {
            throw new CustomException(
                    "El campo 'interval_count' solo admite valores numéricos en el rango 0 a 9999.");
        }

        if (!Helper.validValue(data.get("amount"), true)) {
            throw new CustomException(
                    "El campo 'amount' es inválido o está vacío, debe tener un valor numérico entero.");
        }

        Helper.validateCurrency(data.get("currency").toString(), Integer.parseInt(data.get("amount").toString()));

        if (!Helper.validValue(data.get("name"), false) ||
                Helper.validateRangeParameters(data.get("name"), 5, 50, false)) {
            throw new CustomException(
                    "El campo 'name' es inválido o está vacío. El valor debe tener un rango de 5 a 50 caracteres.");
        }

        if (!Helper.validValue(data.get("description"), false) ||
                Helper.validateRangeParameters(data.get("description"), 5, 250, false)) {
            throw new CustomException(
                    "El campo 'description' es inválido o está vacío. El valor debe tener un rango de 5 a 250 caracteres.");
        }

        if (!Helper.validValue(data.get("short_name"), false) ||
                Helper.validateRangeParameters(data.get("short_name"), 5, 50, false)) {
            throw new CustomException(
                    "El campo 'short_name' es inválido o está vacío. El valor debe tener un rango de 5 a 50 caracteres.");
        }

        if (data.get("initial_cycles") == null) {
            throw new CustomException(
                    "El campo 'initial_cycles' es requerido.");
        }

        Helper.validateEnumCurrency(data.get("currency").toString());

        Map<String, Object> initialCycles = (Map<String, Object>) data.get("initial_cycles");
        if (!Helper.validValue(initialCycles.get("count"), true)) {
            throw new CustomException(
                    "El campo 'initial_cycles.count' es inválido o está vacío, debe tener un valor numérico.");
        }

        if (!Helper.validValue(initialCycles.get("amount"), true)) {
            throw new CustomException(
                    "El campo 'initial_cycles.amount' es inválido o está vacío, debe tener un valor numérico entero.");
        }

        if (!Helper.validValue(initialCycles.get("interval_unit_time"), true) ||
                !allowedValues.contains(initialCycles.get("interval_unit_time"))) {
            throw new CustomException(
                    "El campo 'initial_cycles.interval_unit_time' tiene un valor inválido o está vacío. Estos son los únicos valores permitidos: [ 1, 2, 3, 4, 5, 6].");
        }

        if (!(initialCycles.get("has_initial_charge") instanceof Boolean)) {
            throw new CustomException(
                    "El campo 'initial_cycles.has_initial_charge' es inválido o está vacío. El valor debe ser un booleano (true o false).");
        }

        Helper.validateInitialCycles(initialCycles, data.get("currency").toString(),
                Integer.parseInt(data.get("amount").toString()));

        if (data.containsKey("metadata")) {
            Helper.validateMetadataSchema((Map<String, Object>) data.get("metadata"));
        }

        if (data.containsKey("image")) {
            if (Helper.validateRangeParameters(data.get("image"), 5, 250, false) ||
                    !Pattern.matches(REGEX_IMAGE, data.get("image").toString())) {
                throw new CustomException(
                        "El campo 'image' es inválido o está vacío. El valor debe ser una cadena y debe ser una URL válida de 5 a 250 caracteres.");
            }
        }
    }

    public static void list(Map<String, Object> data) throws Exception {

        Helper.validatePayloadFilterPlan(data);

        List<Integer> PLAN_STATUS = Arrays.asList(1, 2);
        if (data.containsKey("status")) {
            if (!PLAN_STATUS.contains(data.get("status"))) {
                throw new CustomException(
                        "El filtro 'status' tiene un valor inválido o está vacío. Estos son los únicos valores permitidos: 1, 2.");
            }
        }

        if (data.containsKey("min_amount")) {
            if (!Helper.validValue(data.get("min_amount"), true) ||
                    Helper.validateRangeParameters(data.get("min_amount"), 300, 500000, true)) {
                throw new CustomException(
                        "El filtro 'min_amount' admite valores en el rango 300 a 500000.");
            }
        }

        if (data.containsKey("max_amount")) {
            if (!Helper.validValue(data.get("max_amount"), true) ||
                    Helper.validateRangeParameters(data.get("max_amount"), 300, 500000, true)) {
                throw new CustomException(
                        "El filtro 'max_amount' admite valores en el rango 300 a 500000.");
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
        Helper.validatePayloadUpdatePlan(data);

        if (data.containsKey("name")) {
            if (!Helper.validValue(data.get("name"), false) ||
                    Helper.validateRangeParameters(data.get("name"), 5, 50, false)) {
                throw new CustomException(
                        "El campo 'name' es inválido o está vacío. El valor debe tener un rango de 5 a 50 caracteres.");
            }
        }

        if (data.containsKey("description")) {
            if (!Helper.validValue(data.get("description"), false) ||
                    Helper.validateRangeParameters(data.get("description"), 5, 250, false)) {
                throw new CustomException(
                        "El campo 'description' es inválido o está vacío. El valor debe tener un rango de 5 a 250 caracteres.");
            }
        }

        if (data.containsKey("shortName")) {
            if (!Helper.validValue(data.get("short_name"), false) ||
                    Helper.validateRangeParameters(data.get("short_name"), 5, 250, false)) {
                throw new CustomException(
                        "El campo 'short_name' es inválido o está vacío. El valor debe tener un rango de 5 a 50 caracteres.");
            }
        }

        List<Integer> PLAN_STATUS = Arrays.asList(1, 2);
        if (data.containsKey("status")) {
            if (!PLAN_STATUS.contains(data.get("status"))) {
                throw new CustomException(
                        "El filtro 'status' tiene un valor inválido o está vacío. Estos son los únicos valores permitidos: 1, 2.");
            }
        }

        if (data.containsKey("image")) {
            if (Helper.validateRangeParameters(data.get("image"), 5, 250, false) ||
                    !Pattern.matches(REGEX_IMAGE, data.get("image").toString())) {
                throw new CustomException(
                        "El campo 'image' es inválido o está vacío. El valor debe ser una cadena y debe ser una URL válida.");
            }
        }

        if (data.containsKey("metadata")) {
            Helper.validateMetadataSchema((Map<String, Object>) data.get("metadata"));
        }
    }

}
