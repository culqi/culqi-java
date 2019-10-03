package com.culqi.model;

import com.culqi.apioperation.*;
import com.culqi.util.ObjectResult;

import java.util.Map;

/**
 * Created by culqi on 12/02/17.
 */
public class Customer implements All, Create, Find, Update, Delete {

    private static final String URL = "/customers/";

    public Map<String, Object> list(Map<String, Object> params) throws Exception {
        params = (params == null || params.size() == 0) ? null: params;
        return new ObjectResult().list(this.URL.replace("/",""), params);
    }

    public Map<String, Object> list() throws Exception {
        return new ObjectResult().list(this.URL.replace("/",""), null);
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
