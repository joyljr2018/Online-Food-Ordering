package com.jjsushi.sell.controller;

import com.jjsushi.sell.dao.ProductInfo;
import com.jjsushi.sell.dto.OrderDTO;
import com.jjsushi.sell.service.ProductInFoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/seller/product")
public class ProductController {

    @Autowired
    private ProductInFoService productInFoService;
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value="page",defaultValue = "1") Integer page,
                             @RequestParam(value="size",defaultValue = "10") Integer size, Map<String,Object> map){
        Pageable pageable ;
        Page<ProductInfo> productInfoPage = productInFoService.findAll(PageRequest.of(page-1,size));
        map.put("productInfoPage",productInfoPage);
        map.put("currentPage",page);
        map.put("size",size);
        return new ModelAndView("product/list",map);
    }
}
