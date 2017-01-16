package com.culqi.model;

import com.culqi.util.ObjectResult;
import lombok.Data;

import java.util.Map;

/**
 * Created by culqi on 12/23/16.
 */

@Data
public class Refund {

    private static final String URL = "/refunds/";

    private int amount;

    private String charge_id;

    private String reason;

    public Map<String, Object> create(Security security) throws Exception {
        return new ObjectResult().run(security,this, URL);
    }

}
