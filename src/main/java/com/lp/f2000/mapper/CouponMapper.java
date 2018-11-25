package com.lp.f2000.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lp.f2000.entity.Coupon;
import com.lp.f2000.entity.CouponCode;
import com.lp.f2000.entity.CouponProduct;


public interface CouponMapper {
	
	@Insert("INSERT INTO coupon(name, description, coupon_type, cut_money, discount_rate, min_cost, max_discount_price, num, receive_start_time, receive_end_time, use_start_time, use_end_time, valid_day_num, person_limit_num, person_day_limit_num, universal) "
			+ "VALUES(#{name}, #{description}, #{couponType}, #{cutMoney}, #{discountRate}, #{minCost}, #{maxDiscountPrice}, #{num}, #{receiveStartTime}, #{receiveEndTime}, #{useStartTime}, #{useEndTime}, #{validDayNum}, #{personLimitNum}, #{personDayLimitNum}, #{universal})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public int insertCoupon(Coupon coupon);
	
	@Update("UPDATE coupon SET name=#{name}, description=#{description}, coupon_type=#{couponType}, cut_money=#{cutMoney}, discount_rate=#{discountRate}, min_cost=#{minCost}, max_discount_price=#{maxDiscountPrice},  receive_start_time=#{receiveStartTime}, receive_end_time=#{receiveEndTime}, use_start_time=#{useStartTime}, use_end_time=#{useEndTime}, valid_day_num=#{validDayNum}, person_limit_num=#{personLimitNum}, person_day_limit_num=#{personDayLimitNum}, update_time=#{updateTime}, universal=#{universal} WHERE id=#{id}")
	public void updateCoupon(Coupon coupon);
	
	@Select("SELECT * FROM coupon WHERE is_valid=1 order by id desc")
	public List<Coupon> listCoupons();
	
	@Select("SELECT * FROM coupon WHERE is_valid=1 order by id desc ")
	public List<Coupon> listCouponsByPid(@Param("id") int pid);
	
	@Select("SELECT * FROM coupon WHERE id=#{id} AND is_valid=1")
	public Coupon getCouponById(@Param("id") int id);
	
	@Update("UPDATE coupon SET is_valid = 0  WHERE id=#{id} ")
	public void deleteCoupon(@Param("id") int id);
	
	@Update("UPDATE coupon SET can_use = 1  WHERE id=#{id} ")
	public void setCouponCanUse(@Param("id") int id);
	
	@Update("UPDATE coupon SET can_use = 0  WHERE id=#{id} ")
	public void cancelCouponCanUse(@Param("id") int id);
	
	@Insert("INSERT INTO coupon_code(code, coupon_id) "
			+ "VALUES(#{code}, #{couponId})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public int insertCouponCode(CouponCode couponCode);
	
	@Update("UPDATE coupon_code SET pay_order_id=#{payOrderId}, product_order_id=#{productOrderId}, product_id=#{productId}, user_id=#{userId}, receive_time=#{receiveTime}, use_time=#{useTime} WHERE id=#{id}")
	public void updateCouponCode(CouponCode couponCode);
	
    @InsertProvider(type = CouponProvider.class, method = "insertCouponCodes")  
	public void insertCouponCodes(@Param("list") List<CouponCode> codes);
    
    @InsertProvider(type = CouponProvider.class, method = "insertCouponProducts")  
	public void insertCouponProducts(@Param("list") List<CouponProduct> couponProducts);
    
	@Update("UPDATE coupon_code SET is_valid = 0  WHERE coupon_id=#{cid} ")
	public void deleteCouponCodes(@Param("cid") int cid);
	
	@Select("SELECT COUNT(*) FROM coupon_code WHERE coupon_id=#{cid} AND is_valid=1")
	public int countCouponCodes(@Param("cid") int cid);
    
	@Update("UPDATE coupon_product SET is_valid = 0  WHERE coupon_id=#{cid} ")
	public void deleteCouponProducts(@Param("cid") int cid);
	
	@Select("SELECT * FROM coupon_product WHERE coupon_id=#{cid} AND is_valid=1")
	public List<CouponProduct> ListCouponProductsByCid(@Param("cid") int cid);

}
