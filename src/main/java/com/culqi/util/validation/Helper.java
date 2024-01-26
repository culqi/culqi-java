package com.culqi.util.validation;

import java.util.regex.Pattern;

import com.google.gson.Gson;

import java.util.regex.Matcher;
import java.time.LocalDateTime;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Helper {
    public void validateAmount(Object amount) throws Exception {
        if (!(amount instanceof Integer || amount instanceof Float) ||
                ((Number) amount).intValue() != ((Number) amount).doubleValue()) {
            throw new CustomException("Invalid amount.");
        }
    }

    public static boolean isValidCardNumber(String number) {
        Pattern pattern = Pattern.compile("^\\d{13,19}$");
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }

    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile("^\\S+@\\S+\\.\\S+$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static void validateCurrencyCode(String currencyCode) throws Exception {
        if (currencyCode == null || currencyCode.isEmpty()) {
            throw new CustomException("Currency code is empty.");
        }

        List<String> allowedValues = Arrays.asList("PEN", "USD");
        if (!allowedValues.contains(currencyCode)) {
            throw new CustomException("Currency code must be either \"PEN\" or \"USD\".");
        }
    }

    public static void validateStringStart(String string, String start) throws Exception {
        if (!(string.startsWith(start + "_test_") || string.startsWith(start + "_live_"))) {
            throw new CustomException("Incorrect format. The format must start with " + start + "_test_ or " + start + "_live_");
        }
    }

    public static void validateValue(String value, List<String> allowedValues) throws Exception {
        if (!allowedValues.contains(value)) {
            System.err.println(value);
            throw new CustomException("Invalid value. It must be one of " + allowedValues);
        }
    }

    public static boolean isFutureDate(long expirationDate) {
        LocalDateTime expDate = LocalDateTime.ofInstant(Instant.ofEpochSecond(expirationDate), java.time.ZoneId.systemDefault());
        return expDate.isAfter(LocalDateTime.now());
    }

    public static void validateDateFilter(String dateFrom, String dateTo) throws CustomException {
        int parsedDateFrom;
        int parsedDateTo;
        
        try {
            parsedDateFrom = Integer.parseInt(dateFrom);
            parsedDateTo = Integer.parseInt(dateTo);
        } catch (NumberFormatException e) {
            throw new CustomException("Invalid value. Date_from and Date_to must be integers.");
        }

        if (parsedDateTo < parsedDateFrom) {
            throw new CustomException("Invalid value. Date_from must be less than Date_to.");
        }
    }
    public static void validateAmountValue(Object amountObj) throws CustomException {
        if (amountObj instanceof Integer) {
            // Amount is already an integer, no further validation needed.
        } else if (amountObj instanceof String) {
            try {
                int amount = Integer.parseInt((String) amountObj);
            } catch (NumberFormatException e) {
                throw new CustomException("Invalid 'amount'. It should be an integer or a string representing an integer.");
            }
        } else {
            throw new CustomException("Invalid 'amount'. It should be an integer or a string representing an integer.");
        }
    }

    public static boolean validValue(Object value, Boolean isInteger) {
        if (isInteger) {
            if (!(value instanceof Integer)) {
                return false;
            }
        } else {
            if (!(value instanceof String)) {
                return false;
            }
        }
        return true;
    }

    public static boolean validateRangeParameters(Object value, Integer minValue, Integer maxValue, Boolean isInteger) {
        Integer num = isInteger ? Integer.parseInt(value.toString()) : value.toString().length();
        return num < minValue || num > maxValue;
    }

    public static void validateCurrency(String currency, int amount) throws CustomException {
        validateEnumCurrency(currency);
        Integer MIN_AMOUNT_PEN = 1;
        Integer MAX_AMOUNT_PEN = 100;
        Integer MIN_AMOUNT_USD = 10;
        Integer MAX_AMOUNT_USD = 1000;

        int minAmountPublicApi = MIN_AMOUNT_PEN * 100;
        int maxAmountPublicApi = MAX_AMOUNT_PEN * 100;

        if (currency.equals("USD")) {
            minAmountPublicApi = MIN_AMOUNT_USD * 100;
            maxAmountPublicApi = MAX_AMOUNT_USD * 100;
        }

        boolean validAmount = amount >= minAmountPublicApi && amount <= maxAmountPublicApi;

        if (!validAmount) {
            if (currency.equals("USD")) {
                throw new CustomException("El campo 'amount' admite valores en el rango 100 a 150000.");
            }

            throw new CustomException("El campo 'amount' admite valores en el rango 300 a 500000.");
        }
    }

    public static void validateInitialCycles(Map<String, Object> initialCycles, String currency, Integer amount)
            throws CustomException {
        boolean hasInitialCharge = (boolean) initialCycles.get("has_initial_charge");
        int payAmount = (int) initialCycles.get("amount");
        int count = (int) initialCycles.get("count");

        if (!validValue(payAmount, true)) {
            throw new CustomException(
                    "El campo 'initial_cycles.amount' es inválido o está vacío, debe tener un valor numérico.");
        }

        if (hasInitialCharge) {
            validateCurrency(currency, amount);

            if (amount == payAmount) {
                throw new CustomException(
                        "El campo 'initial_cycles.amount' es inválido o está vacío. El valor no debe ser igual al monto del plan.");
            }

            if (count < 1 || count > 9999) {
                throw new CustomException(
                        "El campo 'initial_cycles.count' solo admite valores numéricos en el rango 1 a 9999.");
            }

            if (payAmount < 300 || payAmount > 500000) {
                throw new CustomException("El campo 'initial_cycles.amount' admite valores en el rango 300 a 500000.");
            }
        } else {
            if (count < 0 || count > 9999) {
                throw new CustomException(
                        "El campo 'initial_cycles.count' solo admite valores numéricos en el rango 0 a 9999.");
            }

            if (payAmount != 0) {
                throw new CustomException("El campo 'initial_cycles.amount' es inválido, debe ser 0.");
            }
        }
    }

    public static void validateEnumCurrency(String currency) throws CustomException {
        List<String> ENUM_CURRENCY = Arrays.asList("PEN", "USD");
        if (!ENUM_CURRENCY.contains(currency) || !validValue(currency, false)) {
            throw new CustomException(
                    "El campo 'currency' es inválido o está vacío, el código de la moneda en tres letras (Formato ISO 4217). Culqi actualmente soporta las siguientes monedas: ['PEN','USD'].");
        }
    }

    public static String validateMetadataSchema(Map<String, Object> objMetadata) throws CustomException {
        if (!(objMetadata instanceof Map) && !(objMetadata instanceof Object)) {
            throw new CustomException("Enviaste el campo metadata con un formato incorrecto.");
        }

        for (Map.Entry<String, Object> entry : objMetadata.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            int paramKey = key.length();
            int paramValue = value.toString().length();

            if (paramKey < 1 || paramKey > 30 || paramValue < 1 || paramValue > 2000) {
                throw new CustomException("El objeto 'metadata' es inválido, límite key (1 - 30), value (1 - 200)");
            }
        }

        if (objMetadata instanceof Map && objMetadata.size() > 20) {
            throw new CustomException("Enviaste más de 20 parámetros en el metadata. El límite es 20.");
        }

        Map<String, Object> transformedMetadata = new HashMap<>();
        for (Map.Entry<String, Object> entry : objMetadata.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            transformedMetadata.put(key, value instanceof String ? (String) value : String.valueOf(value));
        }

        return new Gson().toJson(transformedMetadata);
    }

    public static void validatePayloadCreatePlan(Map<String, Object> data) throws CustomException {
        // Define la lista de parámetros esperados
        List<String> expectedParameters = Arrays.asList(
                "interval_unit_time",
                "interval_count",
                "amount",
                "name",
                "description",
                "short_name",
                "currency",
                "metadata",
                "initial_cycles",
                "image");

        // Verifica si hay parámetros adicionales
        Set<String> extraParameters = new HashSet<>(data.keySet());
        extraParameters.removeAll(expectedParameters);

        if (!extraParameters.isEmpty()) {
            throw new CustomException("Parámetros adicionales no permitidos: " + String.join(", ", extraParameters));
        }
    }

    public static void validatePayloadCreateSubscription(Map<String, Object> data) throws CustomException {
        // Define la lista de parámetros esperados
        List<String> expectedParameters = Arrays.asList(
                "card_id",
                "plan_id",
                "metadata",
                "tyc");

        // Verifica si hay parámetros adicionales
        Set<String> extraParameters = new HashSet<>(data.keySet());
        extraParameters.removeAll(expectedParameters);

        if (!extraParameters.isEmpty()) {
            throw new CustomException("Parámetros adicionales no permitidos: " + String.join(", ", extraParameters));
        }
    }

    public static void validatePayloadUpdatePlan(Map<String, Object> data) throws CustomException {
        // Define la lista de parámetros esperados
        List<String> expectedParameters = Arrays.asList(
                "name", "description", "short_name", "status", "image", "metadata");

        // Verifica si hay parámetros adicionales
        Set<String> extraParameters = new HashSet<>(data.keySet());
        extraParameters.removeAll(expectedParameters);

        if (!extraParameters.isEmpty()) {
            throw new CustomException("Parámetros adicionales no permitidos: " + String.join(", ", extraParameters));
        }
    }

    public static void validatePayloadUpdateSubscription(Map<String, Object> data) throws CustomException {
        // Define la lista de parámetros esperados
        List<String> expectedParameters = Arrays.asList(
                "card_id", "metadata");

        // Verifica si hay parámetros adicionales
        Set<String> extraParameters = new HashSet<>(data.keySet());
        extraParameters.removeAll(expectedParameters);

        if (!extraParameters.isEmpty()) {
            throw new CustomException("Parámetros adicionales no permitidos: " + String.join(", ", extraParameters));
        }
    }

    public static void validatePayloadFilterPlan(Map<String, Object> data) throws CustomException {
        // Define la lista de parámetros esperados
        List<String> expectedParameters = Arrays.asList(
                "status",
                "min_amount",
                "max_amount",
                "creation_date_to",
                "creation_date_from",
                "limit",
                "after",
                "before");

        // Verifica si hay parámetros adicionales
        Set<String> extraParameters = new HashSet<>(data.keySet());
        extraParameters.removeAll(expectedParameters);

        if (!extraParameters.isEmpty()) {
            throw new CustomException("Parámetros adicionales no permitidos: " + String.join(", ", extraParameters));
        }
    }

    public static void validatePayloadFilterSubscription(Map<String, Object> data) throws CustomException {
        // Define la lista de parámetros esperados
        List<String> expectedParameters = Arrays.asList(
                "status",
                "plan_id",
                "creation_date_to",
                "creation_date_from",
                "limit",
                "after",
                "before");

        // Verifica si hay parámetros adicionales
        Set<String> extraParameters = new HashSet<>(data.keySet());
        extraParameters.removeAll(expectedParameters);

        if (!extraParameters.isEmpty()) {
            throw new CustomException("Parámetros adicionales no permitidos: " + String.join(", ", extraParameters));
        }
    }

    public static String getStringParameter(Map<String, Object> data, String key) {
        return data.containsKey(key) ? String.valueOf(data.get(key)) : null;
    }

    public static Integer getIntegerParameter(Map<String, Object> data, String key) {
        return !validValue(data, true) && data.containsKey(key) ? (Integer) data.get(key) : null;
    }
}
