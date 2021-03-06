package com.lp.f2000.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lp.f2000.entity.Coupon;
import com.lp.f2000.entity.CouponCode;
import com.lp.f2000.entity.CouponProduct;

public interface CouponService {
	public Coupon getById(int id);
	
	public int insert(Coupon coupon);
	public int insertCouponCode(CouponCode couponCode);
	public void insertCouponCodes(List<CouponCode> couponCodes);
	public void insertCouponProducts(List<CouponProduct> couponProducts);
	
	public void updateCoupon(Coupon coupon);
	
	public List<Coupon> listAllCoupons();
	
	public List<Coupon> listCoupons(int pid);
	
	public void deleteCoupon(int cid);
	public void setCouponCanUse(int cid);
	public void cancelCouponCanUse(int cid);
	
	public void deleteCouponProducts(int cid);
	public void deleteCouponCodes(int cid);
	public int countCouponCodes(int cid);
	
	public List<CouponProduct> ListCouponProductsByCid(int cid);
	
}
