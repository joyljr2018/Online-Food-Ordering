package com.jjsushi.sell.vo;

import lombok.Data;

import java.lang.reflect.Type;

/**
 * http request
 * */
@Data
public class ResultVO<T> {
    /** error code*/
    private Integer code;
    /** message */
    private String msg;
    /** data */
    private T data;
}
