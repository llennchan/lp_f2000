package com.lp.f2000.controller.admin;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lp.f2000.common.Response;
import com.lp.f2000.component.QiniuUtil;
import com.lp.f2000.entity.Product;
import com.lp.f2000.service.ProductService;
import com.lp.f2000.util.FileUtil;
import com.lp.f2000.util.StringUtil;

@RestController
@RequestMapping(value = "admin", produces = "application/json;charset=UTF-8")
public class ProductAdminController {
	@Autowired
	private ProductService productService;
	
	@Autowired
	private QiniuUtil qiniuUtil;

	@GetMapping(value = "product")
	public Response<Product> product(@RequestParam("product_id") int productId) {
		Product p = productService.getById(productId);
		return Response.ofSuccess(p);
	}
	
	@GetMapping(value = "products")
	public Response<List<Product>> products() {
		List<Product> ps = productService.listProducts();
		return Response.ofSuccess(ps);
	}

	
	@PostMapping(value = "add_product")
	public Response addProduct(@RequestParam(value = "name") String name,
			@RequestParam(value = "original_price", required = false) BigDecimal originalPrice,
			@RequestParam(value = "current_price", required = false) BigDecimal currentPrice,
			@RequestParam(value = "post_url", required = false) String postUrl
	) {
		Product p = new Product();
		p.setName(name);
		p.setOriginalPrice(originalPrice);
		p.setCurrentPrice(currentPrice);
		p.setPostUrl(postUrl);
		productService.insert(p);
		return Response.ofSuccess();
	}
	
	@PostMapping(value = "update_product")
	public Response updateProduct(@RequestParam(value = "id") int id,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "original_price", required = false) BigDecimal originalPrice,
			@RequestParam(value = "current_price", required = false) BigDecimal currentPrice,
			@RequestParam(value = "post_url", required = false) String postUrl
	) {
		Product p = new Product();
		p.setName(name);
		p.setOriginalPrice(originalPrice);
		p.setCurrentPrice(currentPrice);
		p.setPostUrl(postUrl);
		p.setId(id);
		Timestamp updateTime = new Timestamp(System.currentTimeMillis()); 
		p.setUpdateTime(updateTime);
		productService.updateProduct(p);
		return Response.ofSuccess();
	}
	
	@PostMapping(value = "delete_product")
	public Response deleteProduct(@RequestParam(value = "id") int id) {
		productService.deleteProduct(id);
		return Response.ofSuccess();
	}
	
	
	 
    /**
                   * 上传文件到七牛云存储
     * @param multipartFile
     * @return
     * @throws IOException
     */
    @PostMapping("/upload_img")
    public String uploadImgQiniu(@RequestParam("editormd-image-file") MultipartFile multipartFile) throws IOException {
        FileInputStream inputStream = (FileInputStream) multipartFile.getInputStream();
        String path = qiniuUtil.uploadImg(inputStream, StringUtil.randomUUID());
        return path;
    }

	
}
