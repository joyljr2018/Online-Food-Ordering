package com.jjsushi.sell.controller;


import com.jjsushi.sell.dto.OrderDTO;
import com.jjsushi.sell.enums.ResultEnum;
import com.jjsushi.sell.exception.SellException;
import com.jjsushi.sell.service.OrderService;
import com.lly835.bestpay.rest.type.Get;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.NoSuchElementException;

@Slf4j
@Controller
@RequestMapping("/seller/order")
public class SellerOrderController {

    @Autowired
    OrderService orderService;

    /**
     * order lis
     * @Param opage
     * @Param size
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value="page",defaultValue = "1") Integer page, @RequestParam(value="size",defaultValue = "10") Integer size, Map<String,Object> map){
        Page<OrderDTO> orderDTOPage = orderService.findList(PageRequest.of(page-1,size));
        map.put("orderDTOPage",orderDTOPage);
        map.put("currentPage",page);
        map.put("size",size);
        return new ModelAndView("order/list",map);

    }
    /**
     * Cancel order
     * @Param orderId
     * @Param map
     * @return
     */

    @GetMapping("cancel")
    public ModelAndView cancel(@RequestParam("orderId") String orderId,Map<String,Object> map){
        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.cancel(orderDTO);
        }
        catch(SellException | NoSuchElementException e){

                log.error("[Cancel order] order unfound");
                map.put("msg", ResultEnum.ORDER_DOES_NOT_EXIST.getMessage());
                map.put("url","/sell/seller/order/list");
                return new ModelAndView("common/error",map);

        }
        map.put("msg", ResultEnum.ORDER_CANCEL_SUCCESS.getMessage());
        map.put("url","/sell/seller/order/list");

        return new ModelAndView("common/success",map);
    }
    /**
     * Order detail
     * @Param orderId
     * @Param map
     * @return
     */

    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId") String orderId, Map<String,Object> map){
        OrderDTO orderDTO = new OrderDTO();
        try{
            orderDTO = orderService.findOne(orderId);
        }catch(SellException e){
            log.error("[Seller checking order detail] error,",e);
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }

        map.put("orderDTO",orderDTO);

        return new ModelAndView("order/detail",map);
    }

}
