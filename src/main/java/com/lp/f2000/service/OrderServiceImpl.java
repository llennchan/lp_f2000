package com.lp.f2000.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lp.f2000.mapper.OrderMapper;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderMapper orderMapper;
	
	@Override
	public int getPorderSkuSum(int skuId) {
		Integer sum = orderMapper.getPorderSkuSum(skuId);
		if(sum==null) {
			sum = 0;
		}
		return sum;
	}

}
