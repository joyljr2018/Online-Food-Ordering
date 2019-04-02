package com.jjsushi.sell.dao;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
public class ProductInfo {

    @Id
    private String productId;
    private String productName;
    private BigDecimal productPrice;
    private Integer productStock;
    private String productDescription;
    // link of the icon
    private String productIcon;
    // 0 for active 1 for inactive
    private Integer productStatus;
    private Integer categoryType;
    public ProductInfo() {
    }
}
