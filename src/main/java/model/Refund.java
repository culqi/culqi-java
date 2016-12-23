package model;

import lombok.Data;

/**
 * Created by culqi on 12/23/16.
 */

@Data
public class Refund {

    private int amount;

    private String charge_id;

    private String reason;

}
