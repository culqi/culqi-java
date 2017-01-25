package com.culqi.query;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

/**
 * Created by culqi on 1/25/17.
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlanQuery extends FilterQuery {

    private String alias;

    private String description;

    private long min_amount;

    private long max_amount;

    private long min_original_amount;

    private long max_original_amount;

    private long min_period_size;

    private long max_period_size;

    private long min_trial_period;

    private long max_trial_period;

    private int min_cycles;

    private int max_cycles;

    private Date date_from;

    private Date date_to;

}
