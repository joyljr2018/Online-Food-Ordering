package com.jjsushi.sell.controller;


import com.jjsushi.sell.config.ProjectUrlConfig;
import com.jjsushi.sell.constant.CookieConstant;
import com.jjsushi.sell.constant.RedisConstant;
import com.jjsushi.sell.dao.SellerInfo;
import com.jjsushi.sell.enums.ResultEnum;
import com.jjsushi.sell.form.CategoryForm;
import com.jjsushi.sell.form.UserForm;
import com.jjsushi.sell.service.SellerService;
import com.jjsushi.sell.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


@Controller
@RequestMapping("/seller")
public class SellerUserController {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ProjectUrlConfig projectUrlConfig;
    @GetMapping("/index")
    public ModelAndView index(){

        return new ModelAndView("user/index");
    }
//
//    @PostMapping("/index")
//    public ModelAndView index(@Valid UserForm form,
//                              BindingResult bindingResult,
//                              Map<String, Object> map){
//        map.put("user",bindingResult);
//
//        return new ModelAndView("user/index",map);
//    }
    @PostMapping("/login")
    public ModelAndView login(@Valid UserForm form,
                              BindingResult bindingResult,
                              HttpServletResponse response,
                              Map<String, Object> map) {
        String username = form.getUsername();
        String password = form.getPassword();
        //1. username match up with database
        SellerInfo sellerInfo = sellerService.findSellerInfoByUsername(username);
        if (sellerInfo == null||!sellerInfo.getPassword().equals(password)) {
            map.put("msg", ResultEnum.LOGIN_FAIL.getMessage());
            map.put("url", "/sell/seller/index");
            return new ModelAndView("common/error");
        }

        //2. set token to redis
        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;
       redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), username, expire, TimeUnit.SECONDS);

        //3. set cookies as token
        CookieUtil.set(response, CookieConstant.TOKEN, token, expire);

        return new ModelAndView("redirect:" + projectUrlConfig.getSell() + "/sell/seller/order/list");

    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Map<String, Object> map) {
        //1. search from cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie != null) {
            //2. clean up redis
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));

            //3. clean up cookie
            CookieUtil.set(response, CookieConstant.TOKEN, null, 0);
        }

        map.put("msg", ResultEnum.LOGOUT_SUCCESS.getMessage());
        map.put("url", "/sell/seller/index");
        return new ModelAndView("common/success", map);
    }
}
