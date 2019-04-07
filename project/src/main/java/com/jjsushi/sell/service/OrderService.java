package com.jjsushi.sell.service;

import com.jjsushi.sell.dto.OrderDTO;
import org.hibernate.criterion.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    /** Create order*/
    OrderDTO create(OrderDTO orderDTO);

    /** check order*/
    OrderDTO findOne(String orderId);
    /** check order list*/
    Page<OrderDTO> findList(String buyerOpenid, Pageable pageable);
    /** cancel order*/
    OrderDTO cancel(OrderDTO orderDTO);
    /** finish order*/
    OrderDTO finish(OrderDTO orderDTO);

    OrderDTO paid(OrderDTO orderDTO);

    /** check all order list*/
    Page<OrderDTO> findList(Pageable pageable);

}
