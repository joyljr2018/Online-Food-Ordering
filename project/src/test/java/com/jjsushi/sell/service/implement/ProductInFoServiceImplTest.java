package com.jjsushi.sell.service.implement;

import com.jjsushi.sell.dao.ProductInfo;
import com.jjsushi.sell.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInFoServiceImplTest {

    @Autowired
    private ProductInFoServiceImpl productInFoService;
    @Test
    public void findOne() throws Exception {
        ProductInfo productInfo = productInFoService.findOne("123");
        Assert.assertEquals("123",productInfo.getProductId());
    }

    @Test
    public void findAllUp() throws Exception{
        List<ProductInfo> productInfoList = productInFoService.findAllUp();
        Assert.assertNotEquals(0,productInfoList.size());
    }

    @Test
    public void findAll()throws Exception {
        PageRequest request = new PageRequest(0,2);
        Page<ProductInfo> productInfoPage =productInFoService.findAll( request);
        System.out.println("INFORMATION:"+productInfoPage.getTotalElements());
        Assert.assertNotEquals(0,productInfoPage.getTotalElements());
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("1234567");
        productInfo.setProductName("MeatBalls2");
        productInfo.setProductPrice(new BigDecimal(3.2));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("ff");
        productInfo.setProductIcon("http:///");
        productInfo.setProductStatus(ProductStatusEnum.DOWND.getCode());
        productInfo.setCategoryType(3);
        ProductInfo result = productInFoService.save(productInfo);
        Assert.assertNotNull(result);
    }
}