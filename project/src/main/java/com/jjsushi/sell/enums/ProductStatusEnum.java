package com.jjsushi.sell.enums;

import lombok.Getter;

@Getter
public enum ProductStatusEnum implements CodeEnum {
    UP(0,"In stock"),
    DOWND(1,"Out of stock")
    ;
    private Integer code;
    private String message;

    ProductStatusEnum(Integer code, String message){
        this.code=code;
        this.message=message;

    }

}
