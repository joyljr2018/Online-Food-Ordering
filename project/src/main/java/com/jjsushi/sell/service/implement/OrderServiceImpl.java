package com.jjsushi.sell.service.implement;

import com.jjsushi.sell.controller.WebSocket;
import com.jjsushi.sell.converter.OrderManagerToOrderDTO;
import com.jjsushi.sell.dao.OrderDetail;
import com.jjsushi.sell.dao.OrderManager;
import com.jjsushi.sell.dao.ProductInfo;
import com.jjsushi.sell.dto.CartDTO;
import com.jjsushi.sell.dto.OrderDTO;
import com.jjsushi.sell.enums.OrderStatusEnum;
import com.jjsushi.sell.enums.PayStatusEnum;
import com.jjsushi.sell.enums.ResultEnum;
import com.jjsushi.sell.exception.SellException;
import com.jjsushi.sell.repository.OrderDetailRepository;
import com.jjsushi.sell.repository.OrderManagerRepository;
import com.jjsushi.sell.service.OrderService;
import com.jjsushi.sell.service.ProductInFoService;
import com.jjsushi.sell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private WebSocket webSocket;
    @Autowired
    private ProductInFoService productInFoService;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderManagerRepository orderManagerRepository;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.genUniqueKey();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ONE.ZERO);
        List<CartDTO> cartDTOList = new ArrayList<>();
        // 1. first check stock and price
        for(OrderDetail orderDetail: orderDTO.getOrderDetailList()){
            ProductInfo productInfo = productInFoService.findOne(orderDetail.getProductId());
            if(productInfo==null){
                throw new SecurityException(ResultEnum.PRODUCT_NOT_EXIST.getMessage());
            }
            // 2. calculate the total amount needed to pay
            orderAmount=productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);
            // copy the data of productInfo to orderDetail
            BeanUtils.copyProperties(productInfo,orderDetail);
            // save order detail to database

            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);

            orderDetailRepository.save(orderDetail);
            CartDTO cartDTO = new CartDTO(orderDetail.getProductId(),orderDetail.getProductQuantity());
            cartDTOList.add(cartDTO);

        }


        // 3. send to database (orderManage and orderDetail)

        OrderManager orderManager =new OrderManager();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO,orderManager);
        orderManager.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderManager.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderManager.setOrderAmount(orderAmount);
        Timestamp ts=new Timestamp(System.currentTimeMillis());
        Date date=new Date(ts.getTime());
        orderManager.setCreateTime(date);
        orderManager.setUpdateTime(date);
        orderManagerRepository.save(orderManager);

//        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e ->
//                new CartDTO(e.getProductId(), e.getProductQuantity())
//        ).collect(Collectors.toList());
        // 4. update stock
        productInFoService.decreaseStock(cartDTOList);

        // send websocket message
        webSocket.sendMessage("New Order!:"+orderId);
        webSocket.onMessage("new order:"+orderId);
        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        OrderManager orderManager = orderManagerRepository.findById(orderId).get();
        if(orderManager==null){
            throw new SellException(ResultEnum.ORDER_DOES_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if(CollectionUtils.isEmpty(orderDetailList)){
            throw new SellException(ResultEnum.ORDERDETAIL_DOENST_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderManager,orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderManager> orderManagerPage = orderManagerRepository.findByBuyerOpenid(buyerOpenid,pageable);
        List<OrderDTO> orderDTOList = OrderManagerToOrderDTO.convert(orderManagerPage.getContent());

        return  new PageImpl<OrderDTO>(orderDTOList,pageable,orderManagerPage.getTotalElements());


    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        OrderManager orderManager = new OrderManager();

        // check order status
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("[Cancel order],orderId ={}, orderStatus={}", orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        // edit order sattus
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO,orderManager);
        OrderManager updatedResult = orderManagerRepository.save(orderManager);
        if(updatedResult==null){
            log.error("[Cancel order] failed, orderManager={}",orderManager);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        // update stock
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("[Cancel order] No order details found, orderDTO={}",orderDTO);
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e->new CartDTO(e.getProductId(),e.getProductQuantity())).collect(Collectors.toList());
        productInFoService.increaseStock(cartDTOList);
        // if order is paid, refund needed
        if(orderDTO.getPayStatus().equals(PayStatusEnum.PAID.getCode())){
            //TODO
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        // check order status
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("[Finish Order] incorrect order status, orderId={}, orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        // update order status
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderManager orderManager=new OrderManager();
        BeanUtils.copyProperties(orderDTO,orderManager);
        OrderManager updateResult = orderManagerRepository.save(orderManager);
        if(updateResult==null){
            log.error("[Finish order] update failed, orderManager={},", orderManager);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        // check order status
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("[Finish Payment] incorrect order status, orderId={}, orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        // check pay status
        if(!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())){
            log.error("[Finish Payment] incorrect pay status, orderDTO={}",orderDTO);
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        //update pay status
        orderDTO.setPayStatus(PayStatusEnum.PAID.getCode());
        OrderManager orderManager=new OrderManager();
        BeanUtils.copyProperties(orderDTO,orderManager);
        OrderManager updateResult = orderManagerRepository.save(orderManager);
        if(updateResult==null){
            log.error("[Finish Payment] update failed, orderManager={},", orderManager);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(Pageable pageable) {
        Page<OrderManager> orderManagerPage = orderManagerRepository.findAll(pageable);
        List<OrderDTO> orderDTOList = OrderManagerToOrderDTO.convert(orderManagerPage.getContent());

        return  new PageImpl<OrderDTO>(orderDTOList,pageable,orderManagerPage.getTotalElements());

    }
}
