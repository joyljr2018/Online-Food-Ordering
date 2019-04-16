package com.jjsushi.sell.service;


import com.jjsushi.sell.dao.SellerInfo;

public interface SellerService {


    SellerInfo findSellerInfoByOpenid(String openid);
}
