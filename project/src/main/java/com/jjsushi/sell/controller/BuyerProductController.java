package com.jjsushi.sell.controller;

import com.jjsushi.sell.dao.ProductCategory;
import com.jjsushi.sell.dao.ProductInfo;
import com.jjsushi.sell.service.CategoryService;
import com.jjsushi.sell.service.ProductInFoService;
import com.jjsushi.sell.utils.ResultVOUtil;
import com.jjsushi.sell.vo.ProductInFoVO;
import com.jjsushi.sell.vo.ProductVO;
import com.jjsushi.sell.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 2019/4/1
 * */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductInFoService productInFoService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResultVO list(){
        //1. lookup all the products that are up
        List<ProductInfo> productInfoList = productInFoService.findAllUp();
        //2. category search (once)
        List<Integer> categoryTypeList = new ArrayList<>();
        // classic way
        for(ProductInfo productInfo : productInfoList){
            categoryTypeList.add(productInfo.getCategoryType());
        }
//        // easy way(java8, lambda)
//        productInfoList.stream().map(e->e.getCategoryType()).collect(Collectors.toList());
       List<ProductCategory > productCategoryList= categoryService.findByCategoryTypeIn(categoryTypeList);

        //3 data format
        List<ProductVO> productVOList = new ArrayList<>();
        for(ProductCategory productCategory : productCategoryList){
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());


            List<ProductInFoVO> productInFoVOList = new ArrayList<>();
            for(ProductInfo productInfo: productInfoList){
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInFoVO productInFoVO = new ProductInFoVO();
                    BeanUtils.copyProperties(productInfo,productInFoVO);
                    productInFoVOList.add(productInFoVO);

                }
            }
            productVO.setProductInFoVOList(productInFoVOList);
            productVOList.add(productVO);
        }
//        ProductVO productVO = new ProductVO();
//        ProductInFoVO productInFoVO = new ProductInFoVO();
//        productVO.setProductInFoVOList(Arrays.asList(productInFoVO));
//        resultVO.setData(Arrays.asList(productVO));

        return ResultVOUtil.success(productVOList);

    }

}
