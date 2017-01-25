package com.culqi.model;

import com.culqi.query.PlanQuery;
import com.culqi.util.ObjectResult;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Created by culqi on 12/22/16.
 */

@Data
public class Plan {

    private static final String URL = "/plans/";

    private String alias;

    private int amount;

    private String currency_code;

    private String interval;

    private int interval_count;

    private int limit;

    private String name;

    private int trial_days;

    // pagination to implement
    public List<Map<String, Object>> list(Security security, PlanQuery params) throws Exception {
        return new ObjectResult().list(security,URL, params);
    }

    public Map<String, Object> create(Security security) throws Exception {
        return new ObjectResult().create(security,this, URL);
    }

    public Map<String, Object> get(Security security, String id) throws Exception {
        return new ObjectResult().get_or_delete(security,URL, id, false);
    }

}
