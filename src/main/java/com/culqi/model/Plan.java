package com.culqi.model;

import lombok.Data;

/**
 * Created by culqi on 12/22/16.
 */

@Data
public class Plan {

    private String alias;

    private int amount;

    private String currency_code;

    private String interval;

    private int interval_count;

    private int limit;

    private String name;

    private int trial_days;

}
