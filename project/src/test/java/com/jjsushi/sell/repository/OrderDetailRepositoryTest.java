package com.jjsushi.sell.repository;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.jjsushi.sell.dao.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void saveTest(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductQuantity(new Integer(3));
        orderDetail.setProductPrice(new BigDecimal(3.3));
        orderDetail.setDetailId("111");
        orderDetail.setProductIcon("ffff");
        orderDetail.setProductName("ssss");
        orderDetail.setOrderId("111111");

        OrderDetail  result = repository.save(orderDetail);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByOrderId(){
        List<OrderDetail> orderDetailList = repository.findByOrderId("111111");
        Assert.assertNotEquals(0,orderDetailList.size());

    }
}