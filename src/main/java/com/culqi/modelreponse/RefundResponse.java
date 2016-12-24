package com.culqi.modelreponse;

import lombok.Data;

/**
 * Created by culqi on 12/23/16.
 */

@Data
public class RefundResponse {

    private String id;

    private String charge_id;

    private int amount;

    private String reason;

    private long date;

    private String object;

}
