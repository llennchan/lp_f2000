package com.lp.f2000.interceptor;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.lp.f2000.entity.User;
import com.lp.f2000.service.UserService;
import com.lp.f2000.util.AESUtil;

/**
 * 登录拦截器
 */
@Component
public class CustomerLoginInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private UserService userService;
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	String f2000AccessToken = request.getHeader("f2000AccessToken");
    	User rsUser = null;
    	
    	if(f2000AccessToken!=null && !f2000AccessToken.trim().equals("")) {
    		String wx_openid = AESUtil.check(f2000AccessToken);
    		if(wx_openid!=null && !wx_openid.trim().equals("")) {
    			rsUser = userService.getByWxOpenid(wx_openid);
    		}
    	}
    	
        if (rsUser != null && rsUser.getId() > 0){
        	request.getSession().setAttribute("current_user", rsUser);
        	return  true;
        }
        response.setContentType("application/json");
    	response.getWriter().write("{status:-1, message:'please login customer account via weixin app'}");
        return  false;
        
    }

}