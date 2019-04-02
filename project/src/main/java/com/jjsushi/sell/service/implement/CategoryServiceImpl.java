package com.jjsushi.sell.service.implement;

import com.jjsushi.sell.dao.ProductCategory;
import com.jjsushi.sell.repository.ProductCategoryRepository;
import com.jjsushi.sell.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/**
 * By Jiarong
 * 2019/4/1
 * */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private ProductCategoryRepository repository;
    @Override
    public ProductCategory findOne(Integer  categoryId) {

     return   repository.findById(categoryId).get();

    }

    @Override
    public List<ProductCategory> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return repository.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return repository.save(productCategory);
    }
}
