package com.culqi.model;

import com.culqi.util.ObjectResult;
import lombok.Data;

import java.util.Map;

/**
 * Created by culqi on 12/22/16.
 */

@Data
public class Charge {

    private static final String URL = "/charges/";

    private String address;

    private String address_city;

    private int amount;

    private String country_code;

    private String currency_code;

    private String email;

    private String first_name;

    private int installments;

    private String last_name;

    private String metadata;

    private int phone_number;

    private String product_description;

    private String token_id;

    public Map<String, Object> create(Security security) throws Exception {
        return new ObjectResult().run(security,this, URL);
    }

}
