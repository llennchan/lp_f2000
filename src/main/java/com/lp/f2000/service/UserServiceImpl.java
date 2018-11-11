package com.lp.f2000.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lp.f2000.entity.CartProduct;
import com.lp.f2000.entity.User;
import com.lp.f2000.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User getById(int id) {
		// TODO Auto-generated method stub
		return userMapper.getUserById(id, 1);
	}
	
	@Override
	public User getByWxOpenid(String wxOpenid) {
		return userMapper.getByWxOpenid(wxOpenid);
	}
	
	@Override
	public int insertUser(User user) {
		return userMapper.insertUser(user);
	}

	@Override
	public int addCartProduct(CartProduct cartProduct) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteCartProduct(int cpid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CartProduct> ListCartProducts(int uid) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
