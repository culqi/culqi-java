package com.culqi.util.validation;
import java.util.List;
import java.util.Map;

import com.culqi.util.CountryCodes;

public class CustomerValidation {
    public static void create(Map<String, Object> data) throws Exception {
        String first_name = (String) data.get("first_name");
        String last_name = (String) data.get("last_name");
        String address = (String) data.get("address");
        String address_city = (String) data.get("address_city");
        if (first_name == null || first_name.isEmpty()) {
            throw new CustomException("first name is empty.");
        }

        if (last_name == null || last_name.isEmpty()) {
            throw new CustomException("last name is empty.");
        }

        if (address == null || address.isEmpty()) {
            throw new CustomException("address is empty.");
        }

        if (address_city == null || address_city.isEmpty()) {
            throw new CustomException("address_city is empty.");
        }

        if (!(data.get("phone_number") instanceof String)) {
            throw new CustomException("Invalid 'phone_number'. It should be a string.");
        }

        List<String> countryCodes = CountryCodes.getCountryCodes();
        Helper.validateValue((String) data.get("country_code"), countryCodes);

        String email = (String) data.get("email");
        if (!Helper.isValidEmail(email)) {
            throw new CustomException("Invalid email.");
        }
    }

    public static void list(Map<String, Object> data) throws Exception {
        if (data.containsKey("email")) {
            String email = (String) data.get("email");
            if (!Helper.isValidEmail(email)) {
                throw new CustomException("Invalid email.");
            }
        }
        if (data.containsKey("country_code")) {
            List<String> countryCodes = CountryCodes.getCountryCodes();
            Helper.validateValue((String) data.get("country_code"), countryCodes);
        }
    }
}
