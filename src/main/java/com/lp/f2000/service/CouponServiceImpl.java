package com.lp.f2000.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lp.f2000.entity.Coupon;
import com.lp.f2000.entity.CouponCode;
import com.lp.f2000.entity.CouponProduct;
import com.lp.f2000.mapper.CouponMapper;

@Service
public class CouponServiceImpl implements CouponService {
	@Autowired
	private CouponMapper couponMapper;
	
	@Override
	public Coupon getById(int id) {
		return couponMapper.getCouponById(id);
	}

	@Override
	public int insert(Coupon coupon) {
		return couponMapper.insertCoupon(coupon);
	}
	
	@Override
	public int insertCouponCode(CouponCode couponCode) {
		return couponMapper.insertCouponCode(couponCode);
	}
	
	@Override
	public void insertCouponCodes(List<CouponCode> couponCodes) {
		couponMapper.insertCouponCodes(couponCodes);
	}
	
	@Override
	public void insertCouponProducts(List<CouponProduct> couponProducts) {
		couponMapper.insertCouponProducts(couponProducts);
	}

	@Override
	public void updateCoupon(Coupon coupon) {
		// TODO Auto-generated method stub
		couponMapper.updateCoupon(coupon);
	}

	@Override
	public List<Coupon> listAllCoupons() {
		// TODO Auto-generated method stub
		List<Coupon> coupons = couponMapper.listCoupons();
		List<CouponProduct> couponProducts;
		for(Coupon cp : coupons) {
			couponProducts = couponMapper.ListCouponProductsByCid(cp.getId());
			cp.setCouponProducts(couponProducts);
		}
		return coupons;
	}

	@Override
	public List<Coupon> listCoupons(int pid) {
		// TODO Auto-generated method stub
		return couponMapper.listCouponsByPid(pid);
	}

	@Override
	public void deleteCoupon(int cid) {
		// TODO Auto-generated method stub
		couponMapper.deleteCoupon(cid);
	}
	
	@Override
	public void deleteCouponProducts(int cid) {
		couponMapper.deleteCouponProducts(cid);
	}
	
	@Override
	public void deleteCouponCodes(int cid) {
		couponMapper.deleteCouponCodes(cid);
	}
	
	@Override
	public List<CouponProduct> ListCouponProductsByCid(int cid){
		return couponMapper.ListCouponProductsByCid(cid);
	}

}
