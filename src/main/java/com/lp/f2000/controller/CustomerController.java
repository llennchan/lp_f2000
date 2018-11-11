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
@RequestMapping(value = "customer", produces = "application/json;charset=UTF-8")
public class CustomerController {
	@Autowired
	private UserService userService;

	/*
	 * 加入购物车商品
	 */
	
	
	/*
	 * 删除购物车商品
	 */
	
	
	/*
	 * 查看购物车列表
	 */
	
	
	/*
	 * 下单
	 */
	
	
	/*
	 * 支付
	 */
	
	/*
	 * 取消订单
	 */
	
	
	/*
	 * 添加收货地址
	 */
	
	/*
	 * 删除收货地址
	 */
	
	/*
	 * 收货地址列表
	 */
	
	/*
	 * 我的订单
	 */

}
