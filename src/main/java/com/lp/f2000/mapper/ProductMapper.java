package com.lp.f2000.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lp.f2000.entity.Category;
import com.lp.f2000.entity.Product;


public interface ProductMapper {

	@Select("SELECT * FROM product WHERE id=#{id} AND is_valid=#{isValid}")
	public Product getProductById(@Param("id") int id, @Param("isValid") int isValid);
	
	@Insert("INSERT INTO product(name, original_price, current_price, post_url) "
			+ "VALUES(#{name}, #{originalPrice}, #{currentPrice}, #{postUrl})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public void insert(Product product);
	
	@Select("SELECT * FROM product WHERE is_valid=1 ")
	public List<Product> listProducts();
	
	@Update("UPDATE product SET name=#{name}, original_price=#{originalPrice}, current_price=#{currentPrice}, post_url=#{postUrl}, update_time=#{updateTime} WHERE id=#{id}")
	public void updateProduct(Product product);
	
	@Update("UPDATE product SET is_valid = 0  WHERE id=#{id} ")
	public void deleteProduct(int id);
	
	
	@Insert("INSERT INTO category(name, parent_id) "
			+ "VALUES(#{name}, #{parentId})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public void insertCategory(Category cat);
	
	@Select("SELECT * FROM category WHERE id=#{id} AND is_valid=1")
	public Product getCategoryById(@Param("id") int id);
	
	@Select("SELECT * FROM product WHERE is_valid=1 ")
	public List<Product> listProductsByPid(int pid);
}
