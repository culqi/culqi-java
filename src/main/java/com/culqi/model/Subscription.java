package com.culqi.model;

import com.culqi.query.SubscriptionQuery;
import com.culqi.util.ObjectResult;
import lombok.Data;

import java.util.List;
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

    // pagination to implement
    public List<Map<String, Object>> list(Security security, SubscriptionQuery params) throws Exception {
        return new ObjectResult().list(security,URL, params);
    }

    public Map<String, Object> create(Security security) throws Exception {
        return new ObjectResult().create(security,this, URL);
    }

    public Map<String, Object> get(Security security, String id) throws Exception {
        return new ObjectResult().get_or_delete(security,URL, id, false);
    }

    // to implement (does not work)
    public Map<String, Object> delete(Security security, String id) throws Exception {
        System.out.println(id);
        return new ObjectResult().get_or_delete(security,URL, id, true);
    }

}
