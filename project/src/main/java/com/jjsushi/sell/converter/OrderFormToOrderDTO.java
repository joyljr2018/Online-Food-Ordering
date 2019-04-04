package com.jjsushi.sell.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jjsushi.sell.dao.OrderDetail;
import com.jjsushi.sell.dto.OrderDTO;
import com.jjsushi.sell.enums.ResultEnum;
import com.jjsushi.sell.exception.SellException;
import com.jjsushi.sell.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
@Slf4j
public class OrderFormToOrderDTO {
    public static OrderDTO convert(OrderForm orderForm){
        Gson gson = new Gson();
        OrderDTO orderDTO =new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        List<OrderDetail> orderDetailList  = new ArrayList<>();
        try{
            orderDetailList=gson.fromJson(orderForm.getItems(),new TypeToken<List<OrderDetail>>(){}.getType());
        }catch(Exception e){
            log.error("[Convert target] error, string ={}",orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
