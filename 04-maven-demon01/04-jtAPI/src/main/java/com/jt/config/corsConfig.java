package com.jt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 完成cors跨域配置：实现思路在请求的响应头中添加访问信息
 */
@Configuration
public class corsConfig implements WebMvcConfigurer {//web项目的全局


    @Override
    public void addCorsMappings(CorsRegistry registry) {
         registry.addMapping("/**")
                 .allowedOrigins("*")
                 .allowCredentials(true);
    }
}
