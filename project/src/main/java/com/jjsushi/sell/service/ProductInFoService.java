package com.jjsushi.sell.service;

import com.jjsushi.sell.dao.ProductInfo;
import com.jjsushi.sell.dto.CartDTO;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ProductInFoService {
    ProductInfo findOne(String productId);
    /**
     * @return products that are up
     * */
    List<ProductInfo> findAllUp();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    // add stock
    void increaseStock(List<CartDTO> cartDTOList);

    // minus stock
    void decreaseStock(List<CartDTO> cartDTOList);

    // back to sell
    ProductInfo onSale(String productId);

    // off
    ProductInfo offSale(String productId);
}
