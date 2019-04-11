package com.jjsushi.sell.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jjsushi.sell.enums.ProductStatusEnum;
import com.jjsushi.sell.utils.EnumUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
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

    private Date createTime;
    private Date updateTime;

    public ProductStatusEnum getProductStatusEnum(){
        return EnumUtil.getByCode(productStatus,ProductStatusEnum.class);
    }

    public ProductInfo() {
    }
}
