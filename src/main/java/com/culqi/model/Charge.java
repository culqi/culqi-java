package com.culqi.model;

import com.culqi.util.ObjectResult;

import java.util.Map;

/**
 * Created by culqi on 12/22/16.
 */

public class Charge extends Generic {

    private static final String URL = "/charges/";
    
    public Charge() {
    	super(URL);
    }

    public Map<String, Object> capture(String id) throws Exception {
        return new ObjectResult().capture(this.URL, id);
    }

}
