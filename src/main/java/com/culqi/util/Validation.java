package com.culqi.util;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import com.culqi.util.CountryCodes;

public class Validation {
    // Create token validation method
    public void createTokenValidation(Data data) throws Exception {
        // Validate card number
        if (!isValidCardNumber(data.cardNumber)) {
            throw new Exception("Invalid card number.");
        }

        // Validate CVV
        Pattern cvvPattern = Pattern.compile("^\\d{3,4}$");
        Matcher cvvMatcher = cvvPattern.matcher(data.cvv);
        if (!cvvMatcher.matches()) {
            throw new Exception("Invalid CVV.");
        }

        // Validate email
        if (!isValidEmail(data.email)) {
            throw new Exception("Invalid email.");
        }

        // Validate expiration month
        Pattern monthPattern = Pattern.compile("^(0?[1-9]|1[012])$");
        Matcher monthMatcher = monthPattern.matcher(data.expirationMonth);
        if (!monthMatcher.matches()) {
            throw new Exception("Invalid expiration month.");
        }

        // Validate expiration year
        Pattern yearPattern = Pattern.compile("^\\d{4}$");
        Matcher yearMatcher = yearPattern.matcher(data.expirationYear);
        int currentYear = LocalDate.now().getYear();
        if (!yearMatcher.matches() || Integer.parseInt(data.expirationYear) < currentYear) {
            throw new Exception("Invalid expiration year.");
        }

        // Check if the card is expired
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        YearMonth expDate = YearMonth.parse(data.expirationYear + "-" + data.expirationMonth, formatter);
        if (expDate.isBefore(YearMonth.now())) {
            throw new Exception("Card has expired.");
        }
    }

    public void chargeValidation(Data data) throws Exception {
        // Validate email
        if (!isValidEmail(data.email)) {
            throw new Exception("Invalid email.");
        }

        // Validate amount
        validateAmount(data.amount);

        // Validate currency code and source id
        validateCurrencyCode(data.currencyCode);
        validateStringStart(data.sourceId, "tkn");
    }

    public void refundValidation(Data data) throws Exception {
        // Validate charge format
        validateStringStart(data.chargeId, "chr");

        // Validate reason
        List<String> allowedValues = Arrays.asList("duplicado", "fraudulento", "solicitud_comprador");
        validateValue(data.reason, allowedValues);

        // Validate amount
        validateAmount(data.amount);
    }

    public void planValidation(Data data) throws Exception {
        // Validate amount
        validateAmount(data.amount);

        // Validate interval
        List<String> allowedValues = Arrays.asList("dias", "semanas", "meses", "años");
        validateValue(data.interval, allowedValues);

        // Validate currency
        validateCurrencyCode(data.currencyCode);
    }

    public void customerValidation(Data data) throws Exception {
        // Validate address, firstname, and lastname
        if (data.first_name == null || data.first_name.isEmpty()) {
            throw new Exception("first name is empty.");
        }

        if (data.last_name == null || data.last_name.isEmpty()) {
            throw new Exception("last name is empty.");
        }

        if (data.address == null || data.address.isEmpty()) {
            throw new Exception("address is empty.");
        }

        if (data.address_city == null || data.address_city.isEmpty()) {
            throw new Exception("address_city is empty.");
        }

        // Validate country code
        validateValue(data.country_code, CountryCodes.getCountryCodes());

        // Validate email
        if (!isValidEmail(data.email)) {
            throw new Exception("Invalid email.");
        }
    }

    public void cardValidation(Data data) throws Exception {
        // Validate customer and token format
        validateStringStart(data.customer_id, "cus");
        validateStringStart(data.token_id, "tkn");
    }

    public void subscriptionValidation(Data data) throws Exception {
        // Validate card and plan format
        validateStringStart(data.card_id, "crd");
        validateStringStart(data.plan_id, "pln");
    }
    
    public void orderValidation(Data data) throws Exception {
        // Validate amount
        if (!(data.amount instanceof Integer || data.amount instanceof Float) ||
                ((Number) data.amount).intValue() != ((Number) data.amount).doubleValue()) {
            throw new Exception("Invalid amount.");
        }

        // Validate currency
        validateCurrencyCode(data.currencyCode);

        // Validate firstname, lastname, and phone
        ClientDetails clientDetails = data.clientDetails;
        if (clientDetails == null || clientDetails.firstName == null || clientDetails.firstName.isEmpty()) {
            throw new Exception("first name is empty.");
        }

        if (clientDetails.lastName == null || clientDetails.lastName.isEmpty()) {
            throw new Exception("last name is empty.");
        }

        if (clientDetails.phoneNumber == null || clientDetails.phoneNumber.isEmpty()) {
            throw new Exception("phone_number is empty.");
        }

        // Validate email
        if (!isValidEmail(clientDetails.email)) {
            throw new Exception("Invalid email.");
        }

        // Validate expiration date
        if (!isFutureDate(data.expirationDate)) {
            throw new Exception("expiration_date must be a future date.");
        }
    }

    public void confirmOrderTypeValidation(Data data) throws Exception {
        validateStringStart(data.orderId, "ord");
    }

    private void validateAmount(Object amount) throws Exception {
        if (!(amount instanceof Integer || amount instanceof Float) ||
                ((Number) amount).intValue() != ((Number) amount).doubleValue()) {
            throw new Exception("Invalid amount.");
        }
    }

    // Method to validate card number
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
            throw new Exception("Currency code is empty.");
        }

        List<String> allowedValues = Arrays.asList("PEN", "USD");
        if (!allowedValues.contains(currencyCode)) {
            throw new Exception("Currency code must be either \"PEN\" or \"USD\".");
        }
    }

    public static void validateStringStart(String string, String start) throws Exception {
        if (!(string.startsWith(start + "_test_") || string.startsWith(start + "_live_"))) {
            throw new Exception("Incorrect format. The format must start with " + start + "_test_ or " + start + "_live_");
        }
    }

    public static void validateValue(String value, List<String> allowedValues) throws Exception {
        if (!allowedValues.contains(value)) {
            throw new Exception("Invalid value. It must be one of " + allowedValues);
        }
    }

    public static boolean isFutureDate(long expirationDate) {
        LocalDateTime expDate = LocalDateTime.ofInstant(Instant.ofEpochSecond(expirationDate), java.time.ZoneId.systemDefault());
        return expDate.isAfter(LocalDateTime.now());
    }

    // Data class with public fields
    public static class Data {
        public String first_name;
        public String last_name;
        public String address;
        public String address_city;
        public String country_code;
        public String cardNumber;
        public String cvv;
        public String email;
        public String expirationMonth;
        public String expirationYear;
        public Object amount;
        public String currencyCode;
        public String sourceId;
        public String chargeId;
        public String reason;
        public String interval;
        public String customer_id;
        public String token_id;
        public String card_id;
        public String plan_id;
        public ClientDetails clientDetails;
        public long expirationDate;
        public String orderId;
    }

    public static class ClientDetails {
        public String firstName;
        public String lastName;
        public String phoneNumber;
        public String email;
    }
}
