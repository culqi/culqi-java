package com.culqi.model;

import com.culqi.query.RefundQuery;
import com.culqi.util.ObjectResult;
import lombok.Data;

import java.util.List;
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

    // pagination to implement
    public List<Map<String, Object>> list(Security security, RefundQuery params) throws Exception {
        return new ObjectResult().list(security,URL, params);
    }

    public Map<String, Object> create(Security security) throws Exception {
        return new ObjectResult().create(security,this, URL);
    }

    public Map<String, Object> get(Security security, String id) throws Exception {
        return new ObjectResult().get_or_delete(security,URL, id, false);
    }

}
