package com.culqi.model;

import com.culqi.apioperation.All;
import com.culqi.apioperation.Find;
import com.culqi.util.ObjectResult;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by culqi on 1/25/17.
 */
public class Transfer implements All, Find {

    private static final String URL = "/transfers/";

    public Map<String, Object> list(Map<String, Object> params) throws Exception {
        if (params == null) {
            params = new HashMap<String, Object>();
            params.put("limit", 50);
        }
        if(params != null && params.size() == 0) {
            params.put("limit", 50);
        }
        return new ObjectResult().list(this.URL, params);
    }

    public Map<String, Object> list() throws Exception {
        Map<String, Object> defaultParam = new HashMap<String, Object>();
        defaultParam.put("limit", 50);
        return new ObjectResult().list(this.URL, defaultParam);
    }

    public Map<String, Object> get(String id) throws Exception {
        return new ObjectResult().get_or_delete(this.URL, id, false);
    }

}
