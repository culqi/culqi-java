package com.culqi.modelreponse;

import lombok.Data;
import com.culqi.model.CardHolder;

/**
 * Created by culqi on 12/21/16.
 */

@Data
public class TokenResponse {

    private String id;

    private String value;

    private String card_number;

    private CardHolder cardholder;

    private String brand_name;

    private String bank_name;

    private String bank_country;

    private String object;

}
