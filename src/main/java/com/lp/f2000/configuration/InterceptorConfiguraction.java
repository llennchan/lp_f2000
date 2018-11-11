package com.lp.f2000.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.lp.f2000.interceptor.CsrfInterceptor;
import com.lp.f2000.interceptor.CustomLoginInterceptor;
import com.lp.f2000.interceptor.AdminLoginInterceptor;

@Configuration
public class InterceptorConfiguraction implements WebMvcConfigurer {
    //配置拦截器
    public void addInterceptors(InterceptorRegistry registry){
        //registry.addInterceptor此方法添加拦截器
    	registry.addInterceptor(new CsrfInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new AdminLoginInterceptor()).addPathPatterns("/admin/*");
        registry.addInterceptor(new CustomLoginInterceptor()).addPathPatterns("/custom/*");
    }
}