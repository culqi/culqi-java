package com.culqi.model;

import lombok.Data;

/**
 * Created by culqi on 12/21/16.
 */

@Data
public class Token {

    private String card_number;

    private String currency_code;

    private String cvv;

    private int expiration_month;

    private int expiration_year;

    private String fingerprint;

    private String last_name;

    private String email;

    private String first_name;

}
