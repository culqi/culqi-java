package com.culqi.apioperation.service;

import com.culqi.apioperation.ObjectResult;
import com.culqi.model.ResponseCulqi;

/**
 * Created by culqi on 12/22/16.
 */

public class Charge extends Generic {

    private static final String URL = "/charges/";
    
    public Charge() {
    	super(URL);
    }

    public ResponseCulqi capture(String id) throws Exception {
        return new ObjectResult().capture(this.URL, id);
    }

}
