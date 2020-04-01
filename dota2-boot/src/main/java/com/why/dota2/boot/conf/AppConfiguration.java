package com.why.dota2.boot.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfiguration implements WebMvcConfigurer {
    /**
     * WebMvcConfigurer配置类其实是Spring内部的一种配置方式，
     * 采用JavaBean的形式来代替传统的xml配置文件形式进行针对框架个性化定制。
     * 可以自定义一些 Handler，Interceptor，ViewResolver，MessageConverter，CORS。
     */
    public static final String pathPattern = "/api/**";
    public static final String origins = "/api/**";

    /** 解决跨域问题 **/
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        /*
           addMapping: 配置支持跨域的路径
           allowedOrigins: 配置允许的源
           allowedMethods: 配置支持跨域请求的方法, 如：GET、POST，一次性返回
           maxAge: 配置预检的有效时间
         */
        registry.addMapping(pathPattern)
                .allowedMethods("POST","GET")
                .allowedOrigins("http://localhost:4200");
    }
}
