package com.culqi.model;

import com.culqi.query.BalanceQuery;
import com.culqi.util.ObjectResult;

import java.util.List;
import java.util.Map;

/**
 * Created by culqi on 1/25/17.
 */
public class Balance {

    private static final String URL = "/balances/";

    // for review...
    // pagination to implement
    public List<Map<String, Object>> list(Security security, BalanceQuery params) throws Exception {
        return new ObjectResult().list(security,URL, params);
    }

    public Map<String, Object> get(Security security, String id) throws Exception {
        return new ObjectResult().get_or_delete(security,URL, id, false);
    }

}
