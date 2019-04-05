package com.jjsushi.sell.service;

import com.jjsushi.sell.dto.OrderDTO;

public interface BuyerService {

    // check specific order
    OrderDTO findOrderOne(String openid, String orderId);
    // cancel order
    OrderDTO canelOrder(String openid, String orderId);
}
