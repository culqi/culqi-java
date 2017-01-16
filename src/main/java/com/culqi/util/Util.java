package com.culqi.util;

import java.util.UUID;

/**
 * Created by culqi on 12/21/16.
 */
public class Util {

    public Util(){}

    public int ramdomNumber() {
        String uuidNumber = String.valueOf(UUID.randomUUID().getMostSignificantBits()%2000);
        return Integer.parseInt(uuidNumber.replace("-",""));
    }

    public String ramdonString() {
        return UUID.randomUUID().toString();
    }

}
