package com.jjsushi.sell.service.implement;

import com.jjsushi.sell.dao.OrderDetail;
import com.jjsushi.sell.dao.OrderManager;
import com.jjsushi.sell.dao.ProductInfo;
import com.jjsushi.sell.dto.CartDTO;
import com.jjsushi.sell.dto.OrderDTO;
import com.jjsushi.sell.enums.ResultEnum;
import com.jjsushi.sell.repository.OrderDetailRepository;
import com.jjsushi.sell.repository.OrderManagerRepository;
import com.jjsushi.sell.service.OrderService;
import com.jjsushi.sell.service.ProductInFoService;
import com.jjsushi.sell.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {

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
            orderAmount=orderDetail.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);

            // save order detail to database
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            // copy the data of productInfo to orderDetail
            BeanUtils.copyProperties(productInfo,orderDetail);
            orderDetailRepository.save(orderDetail);
            CartDTO cartDTO = new CartDTO(orderDetail.getProductId(),orderDetail.getProductQuantity());
            cartDTOList.add(cartDTO);

        }


        // 3. send to database (orderManage and orderDetail)
        OrderManager orderManager =new OrderManager();
        orderManager.setOrderId(orderId);
        orderManager.setOrderAmount(orderAmount);
        BeanUtils.copyProperties(orderDTO,orderManager);
        orderManagerRepository.save(orderManager);

//        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e ->
//                new CartDTO(e.getProductId(), e.getProductQuantity())
//        ).collect(Collectors.toList());
        // 4. update stock
        productInFoService.decreaseStock(cartDTOList);
        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        return null;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }
}
