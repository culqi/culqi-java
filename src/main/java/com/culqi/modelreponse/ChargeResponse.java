package com.culqi.modelreponse;

import lombok.Data;
import com.culqi.model.Client;

/**
 * Created by culqi on 12/22/16.
 */

@Data
public class ChargeResponse {

    private String id;

    private String order_id;

    private int amount;

    private int actual_amount;

    private int net_amount;

    private String product_description;

    private long date;

    private int installments;

    private String installments_amount;

    private String fraud_score;

    private String reference_code;

    private String response_code;

    private String merchant_message;

    private String user_message;

    private String device_ip;

    private String device_country;

    private String country_ip;

    private String product;

    private String state;

    private Client client;

    private TokenResponse token;

    private int[] operations;

    private String object;

}
