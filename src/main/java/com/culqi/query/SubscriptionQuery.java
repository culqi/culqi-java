package com.culqi.query;

import lombok.Data;

import java.util.Date;

/**
 * Created by culqi on 1/25/17.
 */

@Data
public class SubscriptionQuery extends FilterQuery {

    private Date date_from;

    private Date date_to;

    private long min_amount;

    private long max_amount;

    private long min_original_amount;

    private long max_original_amount;

    private int actual_cycle;

    private Date next_billing_date_from;

    private Date next_billing_date_to;

    private String user_mail;

    private String user_name;

    private String user_lastname;

    private String user_country_code;

    private String user_city;

    private String user_address;

    private String user_phone;

    private int total_cycles;

}
