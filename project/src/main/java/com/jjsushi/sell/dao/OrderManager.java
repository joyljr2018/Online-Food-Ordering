package com.jjsushi.sell.dao;

import com.jjsushi.sell.enums.OrderStatusEnum;
import com.jjsushi.sell.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

@Entity
@Data
@DynamicUpdate
public class OrderManager {
    @Id
    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    //buyer's wechat id
    private String buyerOpenid;

    private BigDecimal orderAmount;

    // order status new order, default 0
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    // payment status , unpaid , default 0
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    private Date createTime;

    private Date updateTime;

}
