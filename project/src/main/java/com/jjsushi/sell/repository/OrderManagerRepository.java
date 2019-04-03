package com.jjsushi.sell.repository;

import com.jjsushi.sell.dao.OrderManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderManagerRepository extends JpaRepository<OrderManager,String> {
    Page<OrderManager> findByBuyerOpenid(String buyerOpenid, Pageable pageable);
}
