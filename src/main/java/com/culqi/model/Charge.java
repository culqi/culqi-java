package com.culqi.model;

import com.culqi.apioperation.All;
import com.culqi.apioperation.Create;
import com.culqi.apioperation.Find;
import com.culqi.apioperation.Update;
import com.culqi.util.ObjectResult;

import java.util.Map;

/**
 * Created by culqi on 12/22/16.
 */

public class Charge implements All, Create, Find, Update {

    private static final String URL = "/charges/";

    public Map<String, Object> list(Map<String, Object> params) throws Exception {
        params = (params == null || params.size() == 0) ? null: params;
        return new ObjectResult().list(this.URL, params);
    }

    public Map<String, Object> list() throws Exception {
        return new ObjectResult().list(this.URL, null);
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

    public Map<String, Object> capture(String id) throws Exception {
        return new ObjectResult().capture(this.URL, id);
    }

}
