package model;

import lombok.Data;

/**
 * Created by culqi on 12/23/16.
 */

@Data
public class Subscription {

    private String address;

    private String address_city;

    private String country_code;

    private String email;

    private String last_name;

    private String first_name;

    private int phone_number;

    private String plan_alias;

    private String token_id;

}
