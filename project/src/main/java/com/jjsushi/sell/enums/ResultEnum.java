package com.jjsushi.sell.enums;

import lombok.Data;
import lombok.Getter;

@Getter
public enum ResultEnum {

    PRODUCT_NOT_EXIST(10,"Product doesn't exist"),
    PRODUCT_OUT_OF_STOCK(11,"Product is out of stock"),
    ;
    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


}
