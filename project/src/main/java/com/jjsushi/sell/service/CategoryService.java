package com.jjsushi.sell.service;

import com.jjsushi.sell.dao.ProductCategory;

import java.util.List;

public interface CategoryService {
   ProductCategory findOne(Integer categoryId);

   List<ProductCategory> findAll();

   List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

   ProductCategory save(ProductCategory productCategory);


}
