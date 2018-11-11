package com.lp.f2000.service;

import java.util.List;

import com.lp.f2000.entity.CartProduct;
import com.lp.f2000.entity.User;

public interface UserService {
	public User getById(int id);
	public User getByWxOpenid(String wxOpenid);
	public int insertUser(User user);
	
	/*
	 * 加入购物车商品
	 */
	public int addCartProduct(CartProduct cartProduct);
	
	/*
	 * 删除购物车商品
	 */
	public void deleteCartProduct(int cpid);
	
	
	/*
	 * 查看购物车列表
	 */
	public List<CartProduct> ListCartProducts(int uid);
	
	
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
	 * 下单
	 */
	
	
	/*
	 * 支付
	 */
	
	/*
	 * 取消订单
	 */
	
	/*
	 * 我的订单
	 */
	
	

}
