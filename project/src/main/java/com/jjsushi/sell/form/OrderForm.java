package com.jjsushi.sell.form;

/**
 *
 * 2019/4/3
 * */
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class OrderForm {
    @NotEmpty(message= "Name required")
    private String name;

    @NotEmpty(message = "Phone number required")
    private String phone;
    @NotEmpty(message ="Address required")
    private String address;

    @NotEmpty(message ="openid required")
    private String openid;
    @NotEmpty(message ="Cart can not be empty")
    private String items;
}
