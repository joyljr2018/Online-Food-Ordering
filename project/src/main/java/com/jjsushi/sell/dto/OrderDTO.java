package com.jjsushi.sell.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jjsushi.sell.dao.OrderDetail;
import com.jjsushi.sell.enums.OrderStatusEnum;
import com.jjsushi.sell.enums.PayStatusEnum;
import com.jjsushi.sell.utils.DateToLongSerializer;
import lombok.Data;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
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

    @JsonSerialize(using = DateToLongSerializer.class)
    private Date createTime;
    @JsonSerialize(using = DateToLongSerializer.class)
    private Date updateTime;

    List<OrderDetail> orderDetailList;
}
