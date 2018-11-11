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
import com.lp.f2000.service.ProductService;

@RestController
@RequestMapping(value = "home", produces = "application/json;charset=UTF-8")
public class HomeController {
	@Autowired
	private ProductService productService;
	@Autowired
	private ImageService imageService;

	@GetMapping(value = "products")
	public Response<List<Product>> products() {
		List<Product> ps = productService.listProducts();
		for(Product p : ps) {
			p.setThumbImages(imageService.getImagesByType(p.getId(), Constant.IMAGE_PRODUCT_THUMB));
		}
		return Response.ofSuccess(ps);
	}
	
	@GetMapping(value = "product")
	public Response<Product> product(@RequestParam("product_id") int productId) {
		Product p = productService.getById(productId);
		p.setBroadcastImages(imageService.getImagesByType(p.getId(), Constant.IMAGE_PRODUCT_BROADCAST));
		p.setSmallImages(imageService.getImagesByType(p.getId(), Constant.IMAGE_PRODUCT_SMALL));
		p.setThumbImages(imageService.getImagesByType(p.getId(), Constant.IMAGE_PRODUCT_THUMB));
		p.setDetailImages(imageService.getImagesByType(p.getId(), Constant.IMAGE_PRODUCT_DETAIL));
		List<Sku> skus = productService.listProductSkus(p.getId());
		p.setSkus(skus);
		return Response.ofSuccess(p);
	}
	

}
