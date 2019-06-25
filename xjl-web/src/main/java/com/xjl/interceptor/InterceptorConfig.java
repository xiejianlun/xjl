package com.xjl.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by lenovo on 2019/6/12.
 */

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new HeaderInterceptor()).addPathPatterns("/**")
                /**
                 * swagger
                 */
                .excludePathPatterns("/rest/login.do/info")
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**","/configuration/**")
        ;
        registry.addInterceptor(new TokenInterceptor()).addPathPatterns("/**")
                /**
                 * swagger
                 */
                .excludePathPatterns("/rest/login.do/info")
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**","/configuration/**")
        ;
    }
}


