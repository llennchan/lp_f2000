package com.lp.f2000.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lp.f2000.common.Response;
import com.lp.f2000.entity.User;
import com.lp.f2000.service.UserService;

@RestController
@RequestMapping(value = "user", produces = "application/json;charset=UTF-8")
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping(value = "user")
	public Response<User> user(@RequestParam("user_id") int userId) {
		User u = userService.getById(userId);
		if(u!=null) {
			System.out.println("============="+u.getWxNicname());
		}else {
			System.out.println("=============null");
		}
		return Response.ofSuccess(u);
	}

}
