package com.jjsushi.sell.dto;


import lombok.Data;

@Data
public class CartDTO {
    /** product Id*/
    private String productId;

    /** stocks */
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
