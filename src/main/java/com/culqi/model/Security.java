package com.culqi.model;

import lombok.Data;

/**
 * Created by culqi on 12/21/16.
 */

@Data
public class Security {

    private String COD_ECOMMERCE;
    private String API_KEY;

    public Security(String COD_ECOMMERCE){
        this.setCOD_ECOMMERCE(COD_ECOMMERCE);
    }

    public Security(String COD_ECOMMERCE, String API_KEY){
        this.setCOD_ECOMMERCE(COD_ECOMMERCE);
        this.setAPI_KEY(API_KEY);
    }

}
