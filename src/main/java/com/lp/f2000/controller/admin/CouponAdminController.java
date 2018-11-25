package com.lp.f2000.controller.admin;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lp.f2000.common.Response;
import com.lp.f2000.entity.Coupon;
import com.lp.f2000.entity.CouponCode;
import com.lp.f2000.entity.CouponProduct;
import com.lp.f2000.entity.Product;
import com.lp.f2000.service.CouponService;
import com.lp.f2000.service.ProductService;
import com.lp.f2000.util.StringUtil;

@RestController
@RequestMapping(value = "admin", produces = "application/json;charset=UTF-8")
public class CouponAdminController {
	@Autowired
	private CouponService couponService;
	
	@Autowired
	private ProductService productService;

	@GetMapping(value = "coupon")
	public Response<Coupon> coupon(@RequestParam("coupon_id") int couponId) {
		Coupon c = couponService.getById(couponId);
		return Response.ofSuccess(c);
	}
	
	@GetMapping(value = "coupons")
	public Response<List<Coupon>> coupons() {
		List<Coupon> cs = null;
		cs = couponService.listAllCoupons();
		return Response.ofSuccess(cs);
	}

	
	@PostMapping(value = "add_coupon")
	public Response addCoupon(@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "description", required = true) String description,
			@RequestParam(value = "coupon_type", required = true) int couponType,
			@RequestParam(value = "universal", required = true) boolean universal,
			@RequestParam(value = "product_ids", required = true) String productIds,
			@RequestParam(value = "cut_money", required = true) BigDecimal cutMoney,
			@RequestParam(value = "discount_rate", required = true) float discountRate,
			@RequestParam(value = "min_cost", required = true) BigDecimal minCost,
			@RequestParam(value = "max_discount_price", required = true) BigDecimal maxDiscountPrice,
			@RequestParam(value = "num", required = true) int num,
			@RequestParam(value = "receive_start_time", required = true) Timestamp receiveStartTime,
			@RequestParam(value = "receive_end_time", required = true) Timestamp receiveEndTime,
			@RequestParam(value = "use_start_time", required = true) Timestamp useStartTime,
			@RequestParam(value = "use_end_time", required = true) Timestamp useEndTime,
			@RequestParam(value = "valid_day_num", required = false) int validDayNum,
			@RequestParam(value = "person_limit_num", required = true) int personLimitNum,
			@RequestParam(value = "person_day_limit_num", required = true) int personDayLimitNum
	) {
		if(num > 100000) {
			return Response.ofParamError("优惠券库存量超过上限100000");
		}
		Coupon c = new Coupon();
		c.setName(name);
		c.setDescription(description);
		c.setCouponType(couponType);
		c.setUniversal(universal);
		c.setCutMoney(cutMoney);
		c.setDiscountRate(discountRate);
		c.setMinCost(minCost);
		c.setMaxDiscountPrice(maxDiscountPrice);
		c.setNum(num);
		c.setReceiveStartTime(receiveStartTime);
		c.setReceiveEndTime(receiveEndTime);
		c.setUseStartTime(useStartTime);
		c.setUseEndTime(useEndTime);
		c.setValidDayNum(validDayNum);
		c.setPersonLimitNum(personLimitNum);
		c.setPersonDayLimitNum(personDayLimitNum);
		
		couponService.insert(c);
		int couponId = c.getId();
		
		if(!universal) {
			String[] pidStrs = productIds.split(",");
			int pid;
			Product p = null;
			CouponProduct cp = null;
			List<CouponProduct> cpList = new ArrayList<CouponProduct>();
			for(String pidStr : pidStrs) {
				pid = Integer.parseInt(pidStr); 
				p = productService.getById(pid);
				if(p!=null) {
					cp = new CouponProduct();
					cp.setCouponId(couponId);
					cp.setProductId(pid);
					cpList.add(cp);
				}
			}
			
			if(!cpList.isEmpty()) {
				couponService.insertCouponProducts(cpList);
			}
		}
		
		return Response.ofSuccess();
	}
	
	@PostMapping(value = "create_coupon_codes")
	public Response<Object> createCouponCodes(@RequestParam(value = "id", required = true) int id,
			@RequestParam(value = "num", required = true) int num) {
		Coupon c = couponService.getById(id);
		if(c==null) {
			return Response.ofParamError("优惠券不存在或已被删除");
		}
		int total = c.getNum();
		
		int codeNum = couponService.countCouponCodes(c.getId());
		if(codeNum + num > total) {
			return Response.ofParamError("生成数量超过库存,还可生成" + (total-codeNum));
		}
		
		CouponCode cc = null;
		HashSet<String> set = new HashSet<String>();
		StringUtil.randomCodeSet(num, set);
		List<CouponCode> ccList = new ArrayList<CouponCode>();
        for (String code : set) {  
        	cc = new CouponCode();
 		    cc.setCode(code);
 		    cc.setCouponId(c.getId());
 		    ccList.add(cc);
 		    if(ccList.size()>500) {
 			   couponService.insertCouponCodes(ccList);
 			   ccList.clear();
 		    }
        } 
		if(!ccList.isEmpty()) {
			  couponService.insertCouponCodes(ccList);
			  ccList.clear();
			  ccList = null;
		}
		return Response.ofSuccess(set);
	}
	
	@PostMapping(value = "update_coupon")
	public Response updateCoupon(@RequestParam(value = "id", required = true) int id,
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "description", required = true) String description,
			@RequestParam(value = "product_ids", required = true) String productIds,
			@RequestParam(value = "coupon_type", required = true) int couponType,
			@RequestParam(value = "universal", required = true) boolean universal,
			@RequestParam(value = "cut_money", required = true) BigDecimal cutMoney,
			@RequestParam(value = "discount_rate", required = true) float discountRate,
			@RequestParam(value = "min_cost", required = true) BigDecimal minCost,
			@RequestParam(value = "max_discount_price", required = true) BigDecimal maxDiscountPrice,
			//@RequestParam(value = "num", required = true) int num,
			@RequestParam(value = "receive_start_time", required = true) Timestamp receiveStartTime,
			@RequestParam(value = "receive_end_time", required = true) Timestamp receiveEndTime,
			@RequestParam(value = "use_start_time", required = true) Timestamp useStartTime,
			@RequestParam(value = "use_end_time", required = true) Timestamp useEndTime,
			@RequestParam(value = "valid_day_num", required = false) int validDayNum,
			@RequestParam(value = "person_limit_num", required = true) int personLimitNum,
			@RequestParam(value = "person_day_limit_num", required = true) int personDayLimitNum
	) {
		
		Coupon c = couponService.getById(id);
		if(c==null) {
			return Response.ofParamError("优惠券不存在或已被删除");
		}
		
		c.setName(name);
		c.setDescription(description);
		c.setCouponType(couponType);
		c.setUniversal(universal);
		c.setCutMoney(cutMoney);
		c.setDiscountRate(discountRate);
		c.setMinCost(minCost);
		c.setMaxDiscountPrice(maxDiscountPrice);
		//c.setNum(num);
		c.setReceiveStartTime(receiveStartTime);
		c.setReceiveEndTime(receiveEndTime);
		c.setUseStartTime(useStartTime);
		c.setUseEndTime(useEndTime);
		c.setValidDayNum(validDayNum);
		c.setPersonLimitNum(personLimitNum);
		c.setPersonDayLimitNum(personDayLimitNum);
		
		couponService.updateCoupon(c);
		
		//先删除关联的商品
		couponService.deleteCouponProducts(c.getId());
		
		if(!universal) {
			String[] pidStrs = productIds.split(",");
			int pid;
			Product p = null;
			CouponProduct cp = null;
			List<CouponProduct> cpList = new ArrayList<CouponProduct>();
			for(String pidStr : pidStrs) {
				pid = Integer.parseInt(pidStr); 
				p = productService.getById(pid);
				if(p!=null) {
					cp = new CouponProduct();
					cp.setCouponId(c.getId());
					cp.setProductId(pid);
					cpList.add(cp);
				}
			}
			if(!cpList.isEmpty()) {
				couponService.insertCouponProducts(cpList);
			}
		}
		
		return Response.ofSuccess();
	}
	
	@PostMapping(value = "delete_coupon")
	public Response deleteCoupon(@RequestParam(value = "coupon_id") int coupon_id) {
		couponService.deleteCoupon(coupon_id);
		couponService.deleteCouponProducts(coupon_id);
		couponService.deleteCouponCodes(coupon_id);
		return Response.ofSuccess();
	}
	
	@PostMapping(value = "set_coupon_can_use")
	public Response setCouponCanUse(@RequestParam(value = "coupon_id") int coupon_id) {
		couponService.setCouponCanUse(coupon_id);
		return Response.ofSuccess();
	}

	@PostMapping(value = "cancel_coupon_can_use")
	public Response cancelCouponCanUse(@RequestParam(value = "coupon_id") int coupon_id) {
		couponService.cancelCouponCanUse(coupon_id);
		return Response.ofSuccess();
	}
	
}
