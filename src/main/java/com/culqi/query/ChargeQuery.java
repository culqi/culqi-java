package com.culqi.query;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

/**
 * Created by culqi on 1/24/17.
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChargeQuery extends FilterQuery {

    private long min_amount;

    private long max_amount;

    private Date date_from;

    private Date date_to;

    private String client_mail;

    private String client_name;

    private String card_number;

    private String currency_code;

    private String brand;

    private String state;

}
