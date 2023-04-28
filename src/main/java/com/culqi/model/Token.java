package com.culqi.model;

import com.culqi.util.ObjectResult;

import java.util.Map;

/**
 * Created by culqi on 12/21/16.
 */

public class Token extends Generic {

    private static final String URL = "/tokens/";
    private static final String URL_YAPE = "/tokens/yape";
    
    public Token() {
    	super(URL);
    }

    public Map<String, Object> createYape(Map<String, Object> body) throws Exception {
        return new ObjectResult().create(body, this.URL_YAPE);
    }

}
