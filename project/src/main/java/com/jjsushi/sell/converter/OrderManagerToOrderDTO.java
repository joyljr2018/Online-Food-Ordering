package com.jjsushi.sell.converter;

import com.jjsushi.sell.dao.OrderManager;
import com.jjsushi.sell.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class OrderManagerToOrderDTO {

    public static OrderDTO convert(OrderManager orderManager){

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderManager,orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderManager> orderManagerList){
        return orderManagerList.stream().map(e->convert(e)).collect(Collectors.toList());

    }
}
