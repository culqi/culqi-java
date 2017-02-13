package com.culqi.model;

import com.culqi.apioperation.*;
import com.culqi.util.ObjectResult;

import java.util.List;
import java.util.Map;

/**
 * Created by culqi on 12/02/17.
 */
public class Card implements All, Create, Delete, Find, Update {

    private static final String URL = "/cards/";

    public List<Map<String, Object>> list(Map<String, Object> params) throws Exception {
        return new ObjectResult().list(this.URL, params);
    }

    public Map<String, Object> create(Map<String, Object> body) throws Exception {
        return new ObjectResult().create(body, this.URL);
    }

    public Map<String, Object> get(String id) throws Exception {
        return new ObjectResult().get_or_delete(this.URL, id, false);
    }

    public Map<String, Object> delete(String id) throws Exception {
        return new ObjectResult().get_or_delete(this.URL, id, true);
    }

    public Map<String, Object> update(Map<String, Object> body, String id) throws Exception {
        return new ObjectResult().update(body, this.URL, id);
    }

}
