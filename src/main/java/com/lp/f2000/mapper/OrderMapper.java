package com.lp.f2000.mapper;

import org.apache.ibatis.annotations.Select;

public interface OrderMapper {
	@Select("SELECT sum(num) FROM product_order WHERE sku_id=#{skuId} AND is_valid=1")
	public Integer getPorderSkuSum(int skuId);
	
}
