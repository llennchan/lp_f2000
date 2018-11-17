package com.lp.f2000.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lp.f2000.common.Response;
import com.lp.f2000.constant.Constant;
import com.lp.f2000.entity.Product;
import com.lp.f2000.entity.Sku;
import com.lp.f2000.service.ImageService;
import com.lp.f2000.service.OrderService;
import com.lp.f2000.service.ProductService;

@RestController
@RequestMapping(value = "home", produces = "application/json;charset=UTF-8")
public class HomeController {
	@Autowired
	private ProductService productService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private ImageService imageService;

	@GetMapping(value = "products")
	public Response<List<Product>> products() {
		List<Product> ps = productService.listProducts();
		List<Sku> skus = null;
		for(Product p : ps) {
			p.setBroadcastImages(imageService.getImagesByType(p.getId(), Constant.IMAGE_PRODUCT_BROADCAST));
			p.setSmallImages(imageService.getImagesByType(p.getId(), Constant.IMAGE_PRODUCT_SMALL));
			p.setThumbImages(imageService.getImagesByType(p.getId(), Constant.IMAGE_PRODUCT_THUMB));
			p.setDetailImages(imageService.getImagesByType(p.getId(), Constant.IMAGE_PRODUCT_DETAIL));
			
			skus = productService.listProductSkus(p.getId());
			p.setSkus(skus);
		}
		return Response.ofSuccess(ps);
	}
	
	@GetMapping(value = "product")
	public Response<Object> product(@RequestParam("product_id") int productId) {
		Product p = productService.getById(productId);
		if(p==null) {
			return Response.ofParamError("商品不存在或已被删除");
		}
		p.setBroadcastImages(imageService.getImagesByType(p.getId(), Constant.IMAGE_PRODUCT_BROADCAST));
		p.setSmallImages(imageService.getImagesByType(p.getId(), Constant.IMAGE_PRODUCT_SMALL));
		p.setThumbImages(imageService.getImagesByType(p.getId(), Constant.IMAGE_PRODUCT_THUMB));
		p.setDetailImages(imageService.getImagesByType(p.getId(), Constant.IMAGE_PRODUCT_DETAIL));
		List<Sku> skus = productService.listProductSkus(p.getId());
		//每个sku查询库存量
		int restNum = 0;
		int porderSkuSum = 0;
		for(Sku sku : skus) {
			porderSkuSum = orderService.getPorderSkuSum(sku.getId());
			restNum = sku.getNum() - porderSkuSum;
			if(restNum < 0) {
				restNum = 0;
			}
			sku.setRestNum(restNum);
		}
		p.setSkus(skus);
		return Response.ofSuccess(p);
	}
	

}
