package com.culqi.model;

import com.culqi.util.ObjectResult;
import lombok.Data;

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

    public Map<String, Object> create(Security security) throws Exception {
        return new ObjectResult().run(security,this, URL);
    }

}
