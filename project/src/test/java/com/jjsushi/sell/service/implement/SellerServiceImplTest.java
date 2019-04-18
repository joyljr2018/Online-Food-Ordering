package com.jjsushi.sell.service.implement;

import com.jjsushi.sell.dao.SellerInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SellerServiceImplTest {
    private  static final String username = "admin";

    @Autowired
    private SellerServiceImpl sellerService;

    @Test
    public void findSellerInfoByUsername() throws Exception{
        SellerInfo sellerInfo = sellerService.findSellerInfoByUsername(username);
        Assert.assertEquals(sellerInfo.getUsername(),"admin");

    }
}