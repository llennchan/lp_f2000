package com.lp.f2000.service;

import java.util.List;

import com.lp.f2000.entity.Coupon;
import com.lp.f2000.entity.CouponCode;

public interface CouponService {
	public Coupon getById(int id);
	
	public int insert(Coupon coupon);
	public int insertCouponCode(CouponCode couponCode);
	public void insertCouponCodes(List<CouponCode> couponCodes);
	
	public void updateCoupon(Coupon coupon);
	
	public List<Coupon> listAllCoupons();
	
	public List<Coupon> listCoupons(int pid);
	
	public void deleteCoupon(int cid);
	
}
