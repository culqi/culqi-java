package com.culqi.model;

import com.culqi.apioperation.All;
import com.culqi.apioperation.Create;
import com.culqi.apioperation.Find;
import com.culqi.apioperation.Update;
import com.culqi.util.ObjectResult;
import java.util.List;
import java.util.Map;

/**
 * Created by culqi on 12/22/16.
 */

public class Plan implements All, Create, Find, Update {

    private static final String URL = "/plans/";

    public List<Map<String, Object>> list(Map<String, Object> params) throws Exception {
        return new ObjectResult().list(this.URL, params);
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

}
