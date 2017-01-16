package com.culqi.model;

import com.culqi.util.ObjectResult;
import lombok.Data;

import java.util.Map;

/**
 * Created by culqi on 12/21/16.
 */

@Data
public class Token {

    private static final String URL = "/tokens/";

    private String card_number;

    private String currency_code;

    private String cvv;

    private int expiration_month;

    private int expiration_year;

    private String fingerprint;

    private String last_name;

    private String email;

    private String first_name;

    public Map<String, Object> create(Security security) throws Exception {
        return new ObjectResult().run(security,this, URL);
    }

}
