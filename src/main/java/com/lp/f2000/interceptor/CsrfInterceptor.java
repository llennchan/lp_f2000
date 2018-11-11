package com.lp.f2000.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.lp.f2000.util.StringUtil;

/**
 * 登录拦截器
 */
public class CsrfInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getRequestURI().equals("/admin/login") || request.getRequestURI().equals("/admin/logout")) {
        	return  true;
        }
    	if(!request.getMethod().toLowerCase().equals("get")) {
	        String csrfToken=request.getHeader("csrfToken");
	        String csrfTime=request.getHeader("csrfTime");
	        if(StringUtils.isEmpty(csrfToken)){
	        	csrfToken=request.getParameter("csrfToken");
	        }
	        if(StringUtils.isEmpty(csrfTime)){
	        	csrfTime=request.getParameter("csrfTime");
	        }
	        //在这里实现自己的权限验证逻辑
	        if(!StringUtils.isEmpty(csrfToken) && !StringUtils.isEmpty(csrfTime) && StringUtil.checkCsrf(csrfToken, csrfTime)){//如果验证成功返回true（这里直接写false来模拟验证失败的处理）
	            return true;
	        }else{//如果验证失败
	        	response.setContentType("application/json");
	            response.getWriter().write("{status:-1, message:'csrf_token error'}");
	            return false;
	        }
    	}else {
    		return true;
    	}
    	
    	
    }

}