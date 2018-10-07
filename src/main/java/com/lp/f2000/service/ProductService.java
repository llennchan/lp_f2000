package com.lp.f2000.service;

import java.util.List;

import com.lp.f2000.entity.Product;

public interface ProductService {
	public Product getById(int id);
	
	public void insert(Product product);
	
	public List<Product> listProducts();
	
	public void updateProduct(Product product);
	
	public void deleteProduct(int id);
	
}
