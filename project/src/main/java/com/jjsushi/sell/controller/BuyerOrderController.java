package com.jjsushi.sell.controller;

import com.jjsushi.sell.converter.OrderFormToOrderDTO;
import com.jjsushi.sell.dto.OrderDTO;
import com.jjsushi.sell.enums.ResultEnum;
import com.jjsushi.sell.exception.SellException;
import com.jjsushi.sell.form.OrderForm;
import com.jjsushi.sell.service.BuyerService;
import com.jjsushi.sell.service.OrderService;
import com.jjsushi.sell.utils.ResultVOUtil;
import com.jjsushi.sell.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private BuyerService buyerService;
    @Autowired
    private OrderService orderService;
    // Create Order
    @PostMapping("/create")
    public ResultVO<Map<String,String>> create(@Valid OrderForm orderForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("[Create Order] incorrect parameters, orderForm={}",orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO= OrderFormToOrderDTO.convert(orderForm);
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("[create order] Cart can't be empty orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.CART_EMPTY);
        }

        OrderDTO result = orderService.create(orderDTO);
        Map<String,String> map = new HashMap<>();
        map.put("orderId",result.getOrderId());
        return ResultVOUtil.success(map);
    }
    // Order List
    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam("openId") String openId,
                                          @RequestParam(value="page",defaultValue = "0") Integer page,
                                          @RequestParam(value="size",defaultValue = "10") Integer size){

        if(StringUtils.isEmpty(openId)){
            log.error("[order list] openid is empty openid:{}",openId);
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        Page<OrderDTO> orderDTOPage=orderService.findList(openId,PageRequest.of(page,size));

        return ResultVOUtil.success(orderDTOPage.getContent());
    }

    // Order Detail
    @GetMapping("/detail")
    public ResultVO<OrderDTO> detail(@RequestParam("openid") String openid, @RequestParam("orderId") String orderId){

        OrderDTO orderDTO= buyerService.findOrderOne(openid, orderId);
        return ResultVOUtil.success(orderDTO);
    }


    // Cancel Order
    @PostMapping("/cancel")
    public ResultVO cancel(@RequestParam("openid") String openid, @RequestParam("orderId") String orderId){

        OrderDTO orderDTO=buyerService.canelOrder(openid,orderId);
        orderService.cancel(orderDTO);
        return ResultVOUtil.success();

    }
}
