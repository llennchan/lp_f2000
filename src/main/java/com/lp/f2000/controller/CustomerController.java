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
import com.lp.f2000.entity.CartProduct;
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
	public Response addCoupon(@RequestParam(value = "sku_id", required = true) int skuId,
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
	public Response deleteCartProduct(@RequestParam(value = "cart_product_id", required = true) int cartProductId,
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
	public Response cartProducts(
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
	
	/*
	 * 删除购物车商品
	 */
	@PostMapping(value = "update_cart_product_num")
	public Response updateCartProductNum(@RequestParam(value = "cart_product_id", required = true) int cartProductId,
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
	
	
	/*
	 * 下单
	 */
	
	
	/*
	 * 支付
	 */
	
	/*
	 * 取消订单
	 */
	
	
	
	/*
	 * 添加收货地址
	 */
	
	/*
	 * 删除收货地址
	 */
	
	/*
	 * 收货地址列表
	 */
	
	/*
	 * 我的订单
	 */

}
