package com.lp.f2000.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lp.f2000.entity.CartProduct;


public interface CartProductMapper {

	@Select("SELECT * FROM cart_product WHERE id=#{id} AND is_valid=1")
	public CartProduct getCartProductById(@Param("id") int id);
	
	@Select("SELECT * FROM cart_product WHERE user_id=#{uid} AND is_valid=1 order by id desc")
	public List<CartProduct> getCartProductsByUid(@Param("uid") int uid);
	
	@Insert("INSERT INTO cart_product(user_id, product_id, sku_id, num) "
			+ "VALUES(#{userId}, #{productId}, #{skuId}, #{num})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public int insertCartProduct(CartProduct cartProduct);
	
	@Update("update cart_product set is_valid = 0  WHERE id=#{id}")
	public void deleteCartProduct(@Param("id") int id);
	
}
