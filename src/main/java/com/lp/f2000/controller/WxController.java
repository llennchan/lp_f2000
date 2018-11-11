package com.lp.f2000.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lp.f2000.common.Response;
import com.lp.f2000.service.CategoryService;
import com.lp.f2000.service.UserService;

import weixin.popular.api.SnsAPI;
import weixin.popular.bean.sns.SnsToken;
import weixin.popular.bean.user.User;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
 
/**
 * 微信其他功能开发
 */
@RestController
@RequestMapping("/account")
public class WxController {
	private UserService userService;
	
    @Value("${wx.appId}")
    private  String appID ;
    @Value("${wx.appSecret}")
    private String appSecret;
 
    /**
     * 网页微信授权登录接口
     * @return
     */
    @RequestMapping(value = "/login")
    public void wxLogin(String url, HttpServletResponse response) throws Exception {
        System.out.println("授权登录url:"+url);
        String urls = SnsAPI.connectOauth2Authorize(appID, url, true, "STATE");
        response.sendRedirect(urls);
    }
 
    @RequestMapping(value = "/wx_login")
    public Response<Object> goToIndex(HttpServletRequest request) throws IOException {
        String code = request.getParameter("code");
        if(code==null||"".equals(code)){
            return Response.ofParamError("code为空");
        }
       
        SnsToken snsToken = SnsAPI.oauth2AccessToken(appID, appSecret, code);
        String errcode = snsToken.getErrcode();
        if(errcode!=null&&!"".equals(errcode)){
            return Response.ofParamError("微信出错");
        }
        weixin.popular.bean.user.User wxuser = SnsAPI.userinfo(snsToken.getAccess_token(), snsToken.getOpenid(), "zh_CN",1);  
		String errcode1 = wxuser.getErrcode();
		if(errcode1!=null&&!"".equals(errcode1)){
			return Response.ofParamError("微信获取信息出错");
		}
		
		//登录成功记录session
		String wxOpenid = wxuser.getOpenid();
		if(wxOpenid==null || wxOpenid.trim().equals("")) {
			return Response.ofParamError("微信获取信息出错");
		}
		
		com.lp.f2000.entity.User currentUser = userService.getByWxOpenid(wxOpenid);
		if(currentUser==null) {
			currentUser = new com.lp.f2000.entity.User();
			currentUser.setWxOpenid(wxOpenid);
			currentUser.setWxNicname(wxuser.getNickname());
			int uid = userService.insertUser(currentUser);
			currentUser.setId(uid);
		}
		
		request.getSession().setAttribute("current_user", currentUser);
		
		return Response.ofSuccess(wxuser);
     }
}