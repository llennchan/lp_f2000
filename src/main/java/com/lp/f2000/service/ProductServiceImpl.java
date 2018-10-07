package com.lp.f2000.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lp.f2000.entity.Product;
import com.lp.f2000.mapper.ProductMapper;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductMapper productMapper;
	
	@Override
	public Product getById(int id) {
		// TODO Auto-generated method stub
		return productMapper.getProductById(id, 1);
	}

	@Override
	public void insert(Product product) {
		// TODO Auto-generated method stub
		productMapper.insert(product);
		
	}

	@Override
	public List<Product> listProducts() {
		// TODO Auto-generated method stub
		return productMapper.listProducts();
	}

	@Override
	public void updateProduct(Product product) {
		// TODO Auto-generated method stub
		productMapper.updateProduct(product);
	}

	@Override
	public void deleteProduct(int id) {
		// TODO Auto-generated method stub
		productMapper.deleteProduct(id);
	}
	
	

}
