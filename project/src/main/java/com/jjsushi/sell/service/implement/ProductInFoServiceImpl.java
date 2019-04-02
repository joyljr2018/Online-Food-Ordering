package com.jjsushi.sell.service.implement;

import com.jjsushi.sell.dao.ProductInfo;
import com.jjsushi.sell.enums.ProductStatusEnum;
import com.jjsushi.sell.repository.ProductInfoRepository;
import com.jjsushi.sell.service.ProductInFoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
public class ProductInFoServiceImpl implements ProductInFoService {

    @Autowired
    private ProductInfoRepository repository;


    @Override
    public ProductInfo findOne(String productId) {
        return repository.findById(productId).get();
    }

    @Override
    public List<ProductInfo> findAllUp() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }


    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }
}
