package com.jjsushi.sell.service.implement;

import com.jjsushi.sell.dto.OrderDTO;
import com.jjsushi.sell.service.OrderService;
import com.jjsushi.sell.service.PayService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class PayServiceImplTest {
    @Autowired
    private PayService payService;

    @Autowired
    private OrderService orderService;
    @Test
    public void create() throws  Exception {
        OrderDTO orderDTO = orderService.findOne("");
        payService.create(orderDTO);
    }

    @Test
    public void refund() {
        OrderDTO orderDTO = orderService.findOne("");
        payService.refund(orderDTO);
    }
}