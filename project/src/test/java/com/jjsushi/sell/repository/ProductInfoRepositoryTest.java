package com.jjsushi.sell.repository;

import com.jjsushi.sell.dao.ProductCategory;
import com.jjsushi.sell.dao.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * By jiarong
 * 2019/4/1
 * */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {
    @Autowired
    private ProductInfoRepository repository;

    @Test
    public void saveTest(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123");
        productInfo.setProductName("MeatBalls");
        productInfo.setProductPrice(new BigDecimal(3.2));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("ff");
        productInfo.setProductIcon("http:///");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(3);
        ProductInfo result =repository.save(productInfo);
        Assert.assertNotNull(result);
    }
    @Test
    public void findByProductStatus() throws Exception{
        List<ProductInfo> productInfosList = repository.findByProductStatus(0);
        Assert.assertNotEquals(0,productInfosList.size());

    }
}