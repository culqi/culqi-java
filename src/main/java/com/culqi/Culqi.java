package com.culqi;

import com.culqi.model.Security;


/**
 * Created by culqi on 12/23/16.
 */
public class Culqi {

    Security security = null;

    public Culqi(){
    }

    public Security init(String COD_ECOMMERCE){
        return new Security(COD_ECOMMERCE);
    }

    public Security init(String COD_ECOMMERCE, String API_KEY){
        return new Security(COD_ECOMMERCE, API_KEY);
    }

}
