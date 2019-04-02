package com.jjsushi.sell.controller;

import com.jjsushi.sell.vo.ProductInFoVO;
import com.jjsushi.sell.vo.ProductVO;
import com.jjsushi.sell.vo.ResultVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * 2019/4/1
 * */
@RestController
@RequestMapping("/api/buyer/product")
public class BuyerProductController {
    @GetMapping("/list")
    public ResultVO list(){
        ResultVO  resultVO = new ResultVO();
        ProductVO productVO = new ProductVO();
        ProductInFoVO productInFoVO = new ProductInFoVO();
        productVO.setProductInFoVOList(Arrays.asList(productInFoVO));
        resultVO.setData(Arrays.asList(productVO));
        return resultVO;



    }

}
