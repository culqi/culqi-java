package com.culqi.model;

import com.culqi.util.ObjectResult;

import java.util.Map;

/**
 * Created by culqi on 1/25/17.
 */
public class Iin {

    private static final String URL = "/iins/";

    public Map<String, Object> get(Security security, String id) throws Exception {
        return new ObjectResult().get_or_delete(security,URL, id, false);
    }

}
