package com.lp.f2000.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.lp.f2000.interceptor.CsrfInterceptor;
import com.lp.f2000.interceptor.CustomerLoginInterceptor;
import com.lp.f2000.interceptor.AdminLoginInterceptor;

@Configuration
public class InterceptorConfiguraction implements WebMvcConfigurer {
    @Bean
    public CustomerLoginInterceptor customerLoginInterceptor(){
        return new CustomerLoginInterceptor();
    }
	
	//配置拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        //registry.addInterceptor此方法添加拦截器
    	//registry.addInterceptor(new CsrfInterceptor()).addPathPatterns("/**");
        //registry.addInterceptor(new AdminLoginInterceptor()).addPathPatterns("/admin/*");
        registry.addInterceptor(customerLoginInterceptor()).addPathPatterns("/customer/*");
    }
}