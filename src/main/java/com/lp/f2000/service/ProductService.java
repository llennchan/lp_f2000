package com.lp.f2000.service;

import java.sql.Timestamp;
import java.util.List;

import com.lp.f2000.entity.Product;
import com.lp.f2000.entity.Sku;

public interface ProductService {
	public Product getById(int id);
	
	public int insert(Product product);
	
	public int countProducts();
	
	public List<Product> listProducts();
	
	public void updateProduct(Product product);
	
	public void deleteProduct(int id);
	
	public void updateProductStatus(int id, int status, Timestamp updateTime);
	
	public void upProduct(Product product);
	
	public void downProduct(Product product);
	
	public Sku getSkuById(int skuid);
	
	public void saveSku(Sku sku);
	
	public List<Sku> listProductSkus(int product_id);
	
	public void deleteSku(int skuid);
}
