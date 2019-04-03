package com.jjsushi.sell.dto;

import com.jjsushi.sell.dao.OrderDetail;
import com.jjsushi.sell.enums.OrderStatusEnum;
import com.jjsushi.sell.enums.PayStatusEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Data
public class OrderDTO {
    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    //buyer's wechat id
    private String buyerOpenid;

    private BigDecimal orderAmount;

    // order status new order, default 0
    private Integer orderStatus;

    // payment status , unpaid , default 0
    private Integer payStatus;

    private Date createTime;

    private Date updateTime;

    List<OrderDetail> orderDetailList;
}
