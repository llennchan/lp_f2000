package com.lp.f2000.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lp.f2000.entity.Product;
import com.lp.f2000.entity.Sku;
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
	public int insert(Product product) {
		// TODO Auto-generated method stub
		return productMapper.insert(product);
		
	}
	
	@Override
	public int countProducts() {
		return productMapper.countProducts();
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
		Product p = productMapper.getProductById(id, 1);
		if(p!=null) {
			productMapper.deleteProduct(id);
			productMapper.resetDeleteProductPos(p.getPosition());
		}
		
	}
	
	@Override
	public void updateProductStatus(int id, int status, Timestamp updateTime) {
		productMapper.updateProductStatus(id, status, updateTime);
	}
	
	@Override
	public void upProduct(Product product) {
		int pos = product.getPosition();
		Product p = productMapper.getProductByPos(pos-1, 1);
		if(p!=null) {
			productMapper.updateProductPos(product.getId(), pos-1, product.getUpdateTime());
			productMapper.updateProductPos(p.getId(), pos, p.getUpdateTime());
		}

	}
	
	@Override
	public void downProduct(Product product) {
		int pos = product.getPosition();
		Product p = productMapper.getProductByPos(pos+1, 1);
		if(p!=null) {
			productMapper.updateProductPos(product.getId(), pos+1, product.getUpdateTime());
			productMapper.updateProductPos(p.getId(), pos, p.getUpdateTime());
		}
	}
	
	@Override
	public void saveSku(Sku sku) {
		if(sku.getId() > 0) {
			productMapper.updateSku(sku);
		}else {
			productMapper.insertSku(sku);
		}
		
	}
	
	@Override
	public Sku getSkuById(int skuid) {
		return productMapper.getSkuById(skuid);
	}
	
	@Override
	public List<Sku> listProductSkus(int product_id){
		return productMapper.listProductSkus(product_id);
	}
	
	@Override
	public void deleteSku(int skuid) {
		productMapper.deleteSku(skuid);
	}

}
