package com.culqi.util.validation;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.culqi.util.CountryCodes;

public class TokenValidation {
    public static void create(Map<String, Object> body) throws Exception {
        if (!Helper.isValidCardNumber((String) body.get("card_number"))) {
            throw new CustomException("Invalid card number.");
        }

        Pattern cvvPattern = Pattern.compile("^\\d{3,4}$");
        Matcher cvvMatcher = cvvPattern.matcher((String) body.get("cvv"));
        if (!cvvMatcher.matches()) {
            throw new CustomException("Invalid CVV.");
        }

        if (!Helper.isValidEmail((String) body.get("email"))) {
            throw new CustomException("Invalid email.");
        }

        Pattern monthPattern = Pattern.compile("^(0?[1-9]|1[012])$");
        Matcher monthMatcher = monthPattern.matcher((String) body.get("expiration_month"));
        if (!monthMatcher.matches()) {
            throw new CustomException("Invalid expiration month.");
        }

        Pattern yearPattern = Pattern.compile("^\\d{4}$");
        Matcher yearMatcher = yearPattern.matcher((String) body.get("expiration_year"));
        int currentYear = LocalDate.now().getYear();
        if (!yearMatcher.matches() || Integer.parseInt((String) body.get("expiration_year")) < currentYear) {
            throw new CustomException("Invalid expiration year.");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        YearMonth expDate = YearMonth.parse((String) body.get("expiration_year") + "-" + (String) body.get("expiration_month"), formatter);
        if (expDate.isBefore(YearMonth.now())) {
            throw new CustomException("Card has expired.");
        }
    }

    public static void createTokenYapeValidation(Map<String, Object> data) throws CustomException {
        Object amountObj = data.get("amount");
        
        if (amountObj instanceof String) {
            try {
                int amount = Integer.parseInt((String) amountObj);
            } catch (NumberFormatException e) {
                throw new CustomException("Invalid 'amount'. It should be an integer or a string representing an integer.");
            }
        } else if (!(amountObj instanceof Integer)) {
            throw new CustomException("Invalid 'amount'. It should be an integer or a string representing an integer.");
        }
    }

    public static void list(Map<String, Object> data) throws Exception {
        if (data.containsKey("device_type")) {
            List<String> allowedDeviceValues = Arrays.asList("desktop", "mobile", "tablet");
            Helper.validateValue((String) data.get("device_type"), allowedDeviceValues);
        }

        if (data.containsKey("card_brand")) {
            List<String> allowedBrandValues = Arrays.asList("Visa", "Mastercard", "Amex", "Diners");
            Helper.validateValue((String) data.get("card_brand"), allowedBrandValues);
        }

        if (data.containsKey("card_type")) {
            List<String> allowedCardTypeValues = Arrays.asList("credito", "debito", "internacional");
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
