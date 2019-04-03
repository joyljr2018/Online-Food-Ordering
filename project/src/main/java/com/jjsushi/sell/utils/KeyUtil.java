package com.jjsushi.sell.utils;


/***
 * key generator
 * format: time  + random number
 * @return
 */

import java.util.Random;
public class KeyUtil {

    public static synchronized String genUniqueKey(){
        Random rand = new Random();
        Integer number = rand.nextInt(900000)+100000;
        return System.currentTimeMillis()+String.valueOf(number);
    }
}
