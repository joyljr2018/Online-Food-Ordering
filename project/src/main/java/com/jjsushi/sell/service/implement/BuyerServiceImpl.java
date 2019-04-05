package com.jjsushi.sell.service.implement;

import com.jjsushi.sell.dto.OrderDTO;
import com.jjsushi.sell.enums.ResultEnum;
import com.jjsushi.sell.exception.SellException;
import com.jjsushi.sell.service.BuyerService;
import com.jjsushi.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {
    @Autowired
    private OrderService orderService;
    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        return checkOrderOwner(openid,orderId);

    }

    @Override
    public OrderDTO canelOrder(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openid,orderId);
        if(orderDTO==null){
            log.error("[Cancel Order] order doesn't exist, orderId={}",orderId);
            throw new SellException(ResultEnum.ORDER_DOES_NOT_EXIST);
        }
        return orderService.cancel(orderDTO);
    }

    private OrderDTO checkOrderOwner(String openid, String orderId){
        OrderDTO orderDTO = orderService.findOne(orderId);
        if(orderDTO==null) {
            return null;
        }
        if(!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)){
            log.error("[check order] order's openid are differnt, openid={}, orderDTO={}",openid,orderDTO);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
