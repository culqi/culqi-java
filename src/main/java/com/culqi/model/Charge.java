package com.culqi.model;

import com.culqi.query.ChargeQuery;
import com.culqi.util.ObjectResult;
import lombok.Data;

import java.util.List;
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

    // pagination to implement
    public List<Map<String, Object>> list(Security security, ChargeQuery params) throws Exception {
        return new ObjectResult().list(security,URL, params);
    }

    public Map<String, Object> create(Security security) throws Exception {
        return new ObjectResult().create(security,this, URL);
    }

    public Map<String, Object> get(Security security, String id) throws Exception {
        return new ObjectResult().get_or_delete(security,URL, id, false);
    }

}
