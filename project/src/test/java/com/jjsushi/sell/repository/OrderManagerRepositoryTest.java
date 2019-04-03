package com.jjsushi.sell.repository;

import com.jjsushi.sell.dao.OrderManager;
import org.hibernate.criterion.Order;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderManagerRepositoryTest {
    @Autowired
    private OrderManagerRepository repository;

    private final String OPENID = "123456";
    @Test
    public void saveTest(){
        OrderManager orderManager = new OrderManager();
        orderManager.setBuyerOpenid(OPENID);
        orderManager.setBuyerName("Edison");
        orderManager.setBuyerPhone("415-415-4155");
        orderManager.setOrderId("12345");
        orderManager.setOrderAmount(new BigDecimal(2.3));
        orderManager.setBuyerAddress("Heaven");
        OrderManager result =repository.save(orderManager);

        Assert.assertNotNull(result);

    }
    @Test
    public void findByBuyerOpenId() throws Exception{
        PageRequest request = new PageRequest(0,1);
        Page<OrderManager> result = repository.findByBuyerOpenid(OPENID,request);
        System.out.println(result.getTotalElements());
        Assert.assertNotEquals(0,result.getTotalElements());

    }

}