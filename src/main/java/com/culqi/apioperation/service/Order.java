package com.culqi.apioperation.service;

import java.util.Map;

import com.culqi.apioperation.ObjectResult;
import com.culqi.model.ResponseCulqi;

/**
 * Created by culqi on 12/22/16.
 */

public class Order extends Generic {

    private static final String URL = "/orders/";
    
    public Order() {
    	super(URL);
    }

    public ResponseCulqi confirm(String id) throws Exception {
        return new ObjectResult().confirm(this.URL, id);
    }

    public ResponseCulqi confirm_order_type(Map<String, Object> body) throws Exception {
        return new ObjectResult().create(body, this.URL+"confirm");
    }

}
