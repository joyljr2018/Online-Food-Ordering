package com.jjsushi.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Data
@ConfigurationProperties(prefix = "projecturl")
@Component
public class ProjectUrlConfig {


    public String wechatMpAuthorize;


    public String wechatOpenAuthorize;

    public String sell;
}
