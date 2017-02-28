package com.culqi.model;

import com.culqi.apioperation.*;
import com.culqi.util.ObjectResult;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by culqi on 12/22/16.
 */

public class Plan implements All, Create, Find, Update, Delete {

    private static final String URL = "/plans/";

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

    public Map<String, Object> create(Map<String, Object> body) throws Exception {
        return new ObjectResult().create(body, this.URL);
    }

    public Map<String, Object> get(String id) throws Exception {
        return new ObjectResult().get_or_delete(this.URL, id, false);
    }

    public Map<String, Object> update(Map<String, Object> body, String id) throws Exception {
        return new ObjectResult().update(body, this.URL, id);
    }


    public Map<String, Object> delete(String id) throws Exception {
        return new ObjectResult().get_or_delete(this.URL, id, true);
    }
}
