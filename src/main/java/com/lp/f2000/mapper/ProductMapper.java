package com.lp.f2000.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
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
	
	@Insert("INSERT INTO product(name, desc1, desc2, desc3) "
			+ "VALUES(#{name}, #{desc1}, #{desc2}, #{desc3})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public int insert(Product product);
	
	@Select("SELECT * FROM product WHERE is_valid=1 ")
	public List<Product> listProducts();
	
	@Update("UPDATE product SET name=#{name}, desc1=#{desc1}, desc2=#{desc2}, desc3=#{desc3}, update_time=#{updateTime} WHERE id=#{id}")
	public void updateProduct(Product product);
	
	@Update("UPDATE product SET is_valid = 0  WHERE id=#{id} ")
	public void deleteProduct(int id);
	
	@Insert("INSERT INTO categorize(resource_id, resource_type, category_id) "
			+ "VALUES(#{rid}, #{rtype}, #{cid})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public void insertProductCat(int rid, int rtype, int cid);	
	
	@Select("SELECT cat.* "
			+ "FROM category AS cat, categorize as catize  "
			+ "WHERE cat.id = catize.category_id AND catize.resource_id = #{rid} AND catize.resource_type = #{rtype} ")
	public List<Category> listProductCats(int rid, int rtype);
	
	@Delete("DELETE FROM categorize WHERE resource_id=#{pid} and resource_type = #{rtype} and category_id = #{cid} ")
	public void deleteProductCat(int rid, int rtype, int cid);
	
}
