package com.culqi.model;

import com.culqi.util.ObjectResult;
import lombok.Data;

import java.util.Map;

/**
 * Created by culqi on 12/23/16.
 */

@Data
public class Subscription {

    private static final String URL = "/subscriptions/";

    private String address;

    private String address_city;

    private String country_code;

    private String email;

    private String last_name;

    private String first_name;

    private int phone_number;

    private String plan_alias;

    private String token_id;

    public Map<String, Object> create(Security security) throws Exception {
        return new ObjectResult().run(security,this, URL);
    }

}
