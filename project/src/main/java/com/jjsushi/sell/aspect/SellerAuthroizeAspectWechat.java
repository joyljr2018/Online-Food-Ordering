//package com.jjsushi.sell.aspect;
//import com.jjsushi.sell.constant.CookieConstant;
//import com.jjsushi.sell.constant.RedisConstant;
//import com.jjsushi.sell.exception.SellerAuthorizeException;
//import com.jjsushi.sell.utils.CookieUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
////@Aspect
//@Component
//@Slf4j
//public class SellerAuthroizeAspectWechat {
//
//    @Autowired
//    JedisConnectionFactory jedisConnectionFactory;
//    @Autowired
//    private StringRedisTemplate redisTemplate;
//
//    @Pointcut("execution(public * com.jjsushi.sell.controller.Seller*.*(..))" +
//    "&& !execution(public * com.jjsushi.sell.controller.SellerUserController.*(..))")
//    public void verify() {}
//
//    @Before("verify()")
//    public void doVerify() {
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//
//        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
//        if (cookie == null) {
//            log.warn("Cookie cant find token");
//            throw new SellerAuthorizeException();
//        }
//
//        String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
//        if (StringUtils.isEmpty(tokenValue)) {
//            log.warn("Redis cant find the token");
//            throw new SellerAuthorizeException();
//        }
//    }
//}
