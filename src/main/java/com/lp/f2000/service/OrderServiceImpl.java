package com.lp.f2000.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.lp.f2000.mapper.OrderMapper;

public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderMapper orderMapper;
	
	@Override
	public int getPorderSkuSum(int skuId) {
		return orderMapper.getPorderSkuSum(skuId);
	}

}
