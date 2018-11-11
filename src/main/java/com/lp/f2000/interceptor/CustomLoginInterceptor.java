package com.lp.f2000.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 登录拦截器
 */
public class CustomLoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

    	com.lp.f2000.entity.User rsUser = (com.lp.f2000.entity.User) request.getSession().getAttribute("current_user");
        if (rsUser != null && rsUser.getId() > 0){
        	return  true;
        }
        response.setContentType("application/json");
    	response.getWriter().write("{status:-1, message:'please login custom account via weixin app'}");
        return  false;
        
    }

}