package com.lp.f2000.interceptor;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.lp.f2000.service.UserService;

/**
 * 登录拦截器
 */
@Component
public class CustomerLoginInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private UserService userService;
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	com.lp.f2000.entity.User rsUser = (com.lp.f2000.entity.User) request.getSession().getAttribute("current_user");
        if (rsUser != null && rsUser.getId() > 0){
        	return  true;
        }
    
    	rsUser = null;
        Cookie[] cookies = request.getCookies();
        if(cookies!=null) {
	        //验证cookies是否合法
	        for (Cookie c : cookies){
	            System.out.println(c.getName());
	        	if (c.getName().equals("current_wx_openid")){
	               String wx_openid = c.getValue();
	               if(wx_openid!=null) {
	            	   if(userService!=null) {
	            		   rsUser = userService.getByWxOpenid(wx_openid);
	            	   }
	            	   
	               }
	               
	            }
	        }
        }
    	 
    	//com.lp.f2000.entity.User rsUser = (com.lp.f2000.entity.User) request.getSession().getAttribute("current_user");
        if (rsUser != null && rsUser.getId() > 0){
        	request.getSession().setAttribute("current_user", rsUser);
        	return  true;
        }
        response.setContentType("application/json");
    	response.getWriter().write("{status:-1, message:'please login customer account via weixin app'}");
        return  false;
        
    }

}