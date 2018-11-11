package com.lp.f2000.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 登录拦截器
 */
public class AdminLoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getRequestURI().equals("/admin/login") || request.getRequestURI().equals("/admin/logout")) {
        	return  true;
        }else {
        	Boolean obj = (Boolean) request.getSession().getAttribute("admin_user");
            if (obj != null && obj == true){
            	return  true;
            }
            response.setContentType("application/json");
        	response.getWriter().write("{status:-1, message:'please login admin account'}");
            return  false;
        }
        	

        
    }

}