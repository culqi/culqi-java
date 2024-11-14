package com.culqi.util;
import java.security.SecureRandom;
import java.util.UUID;

/**
 * Created by culqi on 12/21/16.
 */
public class Util {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom random = new SecureRandom();

    public Util(){}

    public int ramdomNumber() {
        String uuidNumber = String.valueOf(UUID.randomUUID().getMostSignificantBits()%2000);
        return Integer.parseInt(uuidNumber.replace("-",""));
    }

    public String ramdonString() {
        return UUID.randomUUID().toString();
    }

    public String ramdonStringWithLengthMax(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }    
}
