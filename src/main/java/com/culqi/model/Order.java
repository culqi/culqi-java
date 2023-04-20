package com.culqi.model;

import com.culqi.util.ObjectResult;

import java.util.Map;

/**
 * Created by culqi on 12/22/16.
 */

public class Order extends Generic {

    private static final String URL = "/orders";
    
    public Order() {
    	super(URL);
    }

    public Map<String, Object> confirm(String id) throws Exception {
        return new ObjectResult().confirm(this.URL, id);
    }

    public Map<String, Object> confirm_order_type(Map<String, Object> body) throws Exception {
        return new ObjectResult().create(body, this.URL+"confirm");
    }

}
