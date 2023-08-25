package com.culqi.apioperation.service;

import java.util.Map;

import com.culqi.apioperation.ObjectResult;
import com.culqi.model.ResponseCulqi;

/**
 * Created by culqi on 12/21/16.
 */

public class Token extends Generic {

    private static final String URL = "/tokens/";
    private static final String URL_YAPE = "/tokens/yape";
    
    public Token() {
    	super(URL);
    }

    public ResponseCulqi createYape(Map<String, Object> body) throws Exception {
        return new ObjectResult().create(body, this.URL_YAPE);
    }

}
