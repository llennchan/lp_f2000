package com.lp.f2000.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
