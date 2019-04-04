package com.jjsushi.sell.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.lang.reflect.Type;

/**
 * http request
 * */
@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultVO<T> {
    /** error code*/
    private Integer code;
    /** message */
    private String msg;
    /** data */
    private T data;
}
