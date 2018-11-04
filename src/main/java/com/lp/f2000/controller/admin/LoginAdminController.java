package com.lp.f2000.controller.admin;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lp.f2000.common.Response;

@RestController
@RequestMapping(value = "admin", produces = "application/json;charset=UTF-8")
public class LoginAdminController {

	@PostMapping(value = "login")
	public Response login(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password, HttpServletRequest request) {
		if(username != null && password!=null && username.equals("lqf_admin") && password.equals("lp_lqf_2000")) {
			request.getSession().setAttribute("admin_user", true);
			return Response.ofSuccess();
		}
		return Response.ofParamError("用户名密码错误");
	}
	
	@PostMapping(value = "logout")
	public Response logout(HttpServletRequest request) {
		request.getSession().setAttribute("admin_user", null);
		return Response.ofSuccess();
	}

	
}
