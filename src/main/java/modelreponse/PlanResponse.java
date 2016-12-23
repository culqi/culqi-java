package modelreponse;

import lombok.Data;

/**
 * Created by culqi on 12/23/16.
 */

@Data
public class PlanResponse {

    private String id;

    private long creation_date;

    private String alias;

    private String name;

    private int amount;

    private String currency_code;

    private String interval;

    private int interval_count;

    private int limit;

    private int trial_days;

    private String object;

}
