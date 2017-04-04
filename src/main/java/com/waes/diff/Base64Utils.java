package com.waes.diff;

import java.util.Base64;

/**
 * util class to Base64 encode and decode string
 *
 * @author Nishanthan Krishnakumar
 * @version 1.0
 */
public class Base64Utils {
    public static String getDecodedString(String encodedString){
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        return   new String(decodedBytes);
    }

    public static String getEncodedString(String decodedString){
        return Base64.getEncoder().encodeToString(decodedString.getBytes());
    }
}
