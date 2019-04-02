package com.jjsushi.sell.service.implement;
/**
 * By Jiarong
 * 2019/4/1
 *
 * */
import com.jjsushi.sell.dao.ProductCategory;
import com.jjsushi.sell.service.CategoryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Test
    public void findOne() throws Exception {
        ProductCategory productCategory = categoryService.findOne(1);
        Assert.assertEquals(new Integer(1),productCategory.getCategoryId());

    }

    @Test
    public void findAll() {
        List<ProductCategory> productCategoryList = categoryService.findAll();
        Assert.assertNotEquals(0,((List) productCategoryList).size());
    }

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(Arrays.asList(2,3,4));
        Assert.assertNotEquals(0,((List) productCategoryList).size());
    }

    @Test
    public void save() {
        ProductCategory productCategory = new ProductCategory("test",10);
        ProductCategory result =categoryService.save(productCategory);
        Assert.assertNotNull(result);

    }
}