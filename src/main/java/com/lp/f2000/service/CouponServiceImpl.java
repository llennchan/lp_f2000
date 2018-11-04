package com.lp.f2000.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lp.f2000.entity.Coupon;
import com.lp.f2000.entity.CouponCode;
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
	public void updateCoupon(Coupon coupon) {
		// TODO Auto-generated method stub
		couponMapper.updateCoupon(coupon);
	}

	@Override
	public List<Coupon> listAllCoupons() {
		// TODO Auto-generated method stub
		return couponMapper.listCoupons();
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

}
