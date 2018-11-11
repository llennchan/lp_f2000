package com.lp.f2000.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lp.f2000.common.Response;
import com.lp.f2000.entity.Address;
import com.lp.f2000.entity.CartProduct;
import com.lp.f2000.entity.PfProvince;
import com.lp.f2000.entity.Sku;
import com.lp.f2000.entity.User;
import com.lp.f2000.service.OrderService;
import com.lp.f2000.service.ProductService;
import com.lp.f2000.service.UserService;

@RestController
@RequestMapping(value = "customer", produces = "application/json;charset=UTF-8")
public class CustomerController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderService orderService;

	/*
	 * 加入购物车商品
	 */
	@PostMapping(value = "add_cart_product")
	public Response<Object> addCoupon(@RequestParam(value = "sku_id", required = true) int skuId,
			@RequestParam(value = "num", required = true) int num,
			HttpServletRequest request
	) {
		User user = (User) request.getSession().getAttribute("current_user");
		if(user == null) {
			return Response.ofParamError("请先登录");
		}
		
		if(num<=0) {
			return Response.ofParamError("sku数量不正确");
		}
		
		Sku sku = productService.getSkuById(skuId);
		if(sku==null) {
			return Response.ofParamError("sku不存在");
		}
		//判断是否有库存
		int porderSkuSum = orderService.getPorderSkuSum(skuId);
		if (sku.getNum() < (porderSkuSum + num)) {
			return Response.ofParamError("商品库存不足");
		}
		
		CartProduct cartProduct = new CartProduct();
		cartProduct.setSkuId(skuId);
		cartProduct.setNum(num);
		userService.addCartProduct(cartProduct);
		return Response.ofSuccess();
	}
	
	
	
	/*
	 * 删除购物车商品
	 */
	@PostMapping(value = "delete_cart_product")
	public Response<Object> deleteCartProduct(@RequestParam(value = "cart_product_id", required = true) int cartProductId,
			HttpServletRequest request
	) {
		User user = (User) request.getSession().getAttribute("current_user");
		if(user == null) {
			return Response.ofParamError("请先登录");
		}
		
		CartProduct cartProduct = userService.getCartProductById(cartProductId);
		if(cartProduct==null || cartProduct.getUserId()!=user.getId()) {
			return Response.ofParamError("购物车商品不存在");
		}
		
		userService.deleteCartProduct(cartProductId);
		return Response.ofSuccess();
	}
	
	
	/*
	 * 查看购物车列表
	 */
	@GetMapping(value = "cart_products")
	public Response<Object> cartProducts(
			HttpServletRequest request
	) {
		User user = (User) request.getSession().getAttribute("current_user");
		if(user == null) {
			return Response.ofParamError("请先登录");
		}
		
		List<CartProduct> cartProducts = userService.ListCartProducts(user.getId());
		return Response.ofSuccess(cartProducts);
	}
	
	
	/*
	 * 更新购物商品数量
	 */
	@PostMapping(value = "update_cart_product_num")
	public Response<Object> updateCartProductNum(@RequestParam(value = "cart_product_id", required = true) int cartProductId,
			@RequestParam(value = "num", required = true) int num,
			HttpServletRequest request
	) {
		User user = (User) request.getSession().getAttribute("current_user");
		if(user == null) {
			return Response.ofParamError("请先登录");
		}
		
		if(num < 0) {
			return Response.ofParamError("商品数量错误");
		}
		
		CartProduct cartProduct = userService.getCartProductById(cartProductId);
		if(cartProduct==null || cartProduct.getUserId()!=user.getId()) {
			return Response.ofParamError("购物车商品不存在");
		}
		
		Sku sku = productService.getSkuById(cartProduct.getSkuId());
		if(sku==null) {
			return Response.ofParamError("sku不存在");
		}
		//判断是否有库存
		int porderSkuSum = orderService.getPorderSkuSum(cartProduct.getSkuId());
		if (sku.getNum() < (porderSkuSum + num)) {
			return Response.ofParamError("商品库存不足");
		}
		
		userService.updateCartProductNum(cartProductId, num);
		return Response.ofSuccess();
	}
	
	@PostMapping(value = "add_address")
	public Response<Object> insertAddress(
			@RequestParam(value = "rec_province", required = true) String recProvince,
			@RequestParam(value = "rec_city", required = true) String recCity,
			@RequestParam(value = "rec_area", required = true) String recArea,
			@RequestParam(value = "rec_address", required = true) String recAddress,
			@RequestParam(value = "contact_name", required = true) String contactName,
			@RequestParam(value = "contact_phone", required = true) String contactPhone,
			HttpServletRequest request
	) {
		User user = (User) request.getSession().getAttribute("current_user");
		if(user == null) {
			return Response.ofParamError("请先登录");
		}
		
		Address a = new Address();
		a.setUserId(user.getId());
		a.setRecProvince(recProvince);
		a.setRecCity(recCity);
		a.setRecArea(recArea);
		a.setRecAddress(recAddress);
		a.setContactName(contactName);
		a.setContactPhone(contactPhone);
		userService.insertAddress(a);
		return Response.ofSuccess();
		
	}
	
	@PostMapping(value = "update_address")
	public Response<Object> updateAddress(
			@RequestParam(value = "id", required = true) int id,
			@RequestParam(value = "rec_province", required = true) String recProvince,
			@RequestParam(value = "rec_city", required = true) String recCity,
			@RequestParam(value = "rec_area", required = true) String recArea,
			@RequestParam(value = "rec_address", required = true) String recAddress,
			@RequestParam(value = "contact_name", required = true) String contactName,
			@RequestParam(value = "contact_phone", required = true) String contactPhone,
			HttpServletRequest request
	) {
		User user = (User) request.getSession().getAttribute("current_user");
		if(user == null) {
			return Response.ofParamError("请先登录");
		}
		Address a = userService.getAddressByid(id);
		if(a == null || a.getUserId()!=user.getId()) {
			return Response.ofParamError("地址不存在");
		}
		
		a.setUserId(user.getId());
		a.setRecProvince(recProvince);
		a.setRecCity(recCity);
		a.setRecArea(recArea);
		a.setRecAddress(recAddress);
		a.setContactName(contactName);
		a.setContactPhone(contactPhone);
		userService.updateAddress(a);
		return Response.ofSuccess();
		
	}
	
	@GetMapping(value = "addresses")
	public Response<Object> getAddressesByuid(HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("current_user");
		if(user == null) {
			return Response.ofParamError("请先登录");
		}	
		List<Address> ads = userService.getAddressesByuid(user.getId());
		return Response.ofSuccess(ads);
	}
	
	@GetMapping(value = "default_address")
	public Response<Object> getDefaultAddressByuid(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("current_user");
		if(user == null) {
			return Response.ofParamError("请先登录");
		}	
		Address a = userService.getDefaultAddressByuid(user.getId());
		return Response.ofSuccess(a);
	}
	
	@PostMapping(value = "set_default_address")
	public Response<Object> setAddressDefault(
			@RequestParam(value = "id", required = true) int id,
			HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("current_user");
		if(user == null) {
			return Response.ofParamError("请先登录");
		}	
		Address a = userService.getAddressByid(id);
		if(a == null || a.getUserId()!=user.getId()) {
			return Response.ofParamError("地址不存在");
		}
		
		userService.cancelAddressesDefaultByUid(user.getId());
		userService.setAddressDefault(id);
		return Response.ofSuccess();
	}
	
	
	@PostMapping(value = "delete_address")
	public Response<Object> deleteAddress(@RequestParam(value = "id", required = true) int id,
			HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("current_user");
		if(user == null) {
			return Response.ofParamError("请先登录");
		}	
		Address a = userService.getAddressByid(id);
		if(a == null || a.getUserId()!=user.getId()) {
			return Response.ofParamError("地址不存在");
		}
		
		userService.deleteAddress(id);
		return Response.ofSuccess();
	}
	
	@GetMapping(value = "all_areas")
	public Response<Object> allAreas(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("current_user");
		if(user == null) {
			return Response.ofParamError("请先登录");
		}	
		List<PfProvince> areas = userService.getPfProvincesAll();
		return Response.ofSuccess(areas);
	}
	
	/*
	 * 下单
	 */
	
	
	/*
	 * 支付
	 */
	
	/*
	 * 取消订单
	 */
	
	

}
