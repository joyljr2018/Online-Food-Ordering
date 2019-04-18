package com.jjsushi.sell.service.implement;


import com.jjsushi.sell.dao.SellerInfo;
import com.jjsushi.sell.repository.SellerInfoRepository;
import com.jjsushi.sell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoRepository repository;

    @Override
    public SellerInfo findSellerInfoByUsername(String username) {
        return repository.findByUsername(username);
    }
}
