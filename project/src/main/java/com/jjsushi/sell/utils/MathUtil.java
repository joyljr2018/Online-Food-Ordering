package com.jjsushi.sell.utils;

public class MathUtil {


    private static final Double MONEY_RANGE = 0.01;

    /**
     * compare if two numbers are equal
     * @param d1
     * @param d2
     * @return
     */
    public static Boolean equals(Double d1, Double d2) {
        Double result = Math.abs(d1 - d2);
        if (result < MONEY_RANGE) {
            return true;
        }else {
            return false;
        }
    }
}
