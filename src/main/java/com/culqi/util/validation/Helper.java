package com.culqi.util.validation;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.time.LocalDateTime;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

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
}
