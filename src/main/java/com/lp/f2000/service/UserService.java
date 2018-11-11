package com.lp.f2000.service;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lp.f2000.entity.Address;
import com.lp.f2000.entity.CartProduct;
import com.lp.f2000.entity.PfProvince;
import com.lp.f2000.entity.User;

public interface UserService {
	public User getById(int id);
	public User getByWxOpenid(String wxOpenid);
	public int insertUser(User user);
	
	/*
	 * 加入购物车商品
	 */
	public int addCartProduct(CartProduct cartProduct);
	
	//查询购物车商品
	public CartProduct getCartProductById(int cartProductId);
	
	/*
	 * 删除购物车商品
	 */
	public void deleteCartProduct(int cpid);
	
	
	/*
	 * 查看购物车列表
	 */
	public List<CartProduct> ListCartProducts(int uid);
	
	public void updateCartProductNum(int cpid, int num);
	
	
	public int insertAddress(Address address);
	
	public void updateAddress(Address address);
	
	public List<Address> getAddressesByuid(int uid);
	
	public Address getDefaultAddressByuid(int uid);
	
	public Address getAddressByid(int id);
	
	public void setAddressDefault(int aid);
	
	public void cancelAddressesDefaultByUid(int uid);
	
	public void deleteAddress(int aid);
	
	public List<PfProvince> getPfProvincesAll();
	
	
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
