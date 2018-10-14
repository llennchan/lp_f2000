package com.lp.f2000.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lp.f2000.entity.Category;
import com.lp.f2000.entity.Product;


public interface CategoryMapper {
	
	@Insert("INSERT INTO category(name, parent_id, cat_type) "
			+ "VALUES(#{name}, #{parentId}, #{catType})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public void insertCategory(Category cat);
	
	@Update("UPDATE category SET name=#{name}, parent_id=#{parentId} , cat_type=#{catType} WHERE id=#{id}")
	public void updateCategory(Category cat);
	
	@Select("SELECT * FROM category WHERE parent_id = #{pid} and is_valid=1 ")
	public List<Category> listCategoriesByPid(int pid);
	
	@Select("SELECT * FROM category WHERE id=#{id} AND is_valid=1")
	public Category getCategoryById(@Param("id") int id);
	
	@Update("UPDATE category SET is_valid = 0  WHERE id=#{id} ")
	public void deleteCategory(int id);

}
