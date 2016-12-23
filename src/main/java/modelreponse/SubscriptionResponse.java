package modelreponse;

import lombok.Data;

/**
 * Created by culqi on 12/23/16.
 */

@Data
public class SubscriptionResponse {

    private String id;

    private long creation_date;

    private String first_name;

    private String last_name;

    private String address;

    private String address_city;

    private String country_code;

    private TokenResponse token;

    private String email;

    private String phone;

    private PlanResponse plan;

    private String current;

    private long next_billing_date;

    private String object;

}
