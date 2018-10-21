package com.lp.f2000.controller.admin;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lp.f2000.common.Response;
import com.lp.f2000.component.QiniuUtil;
import com.lp.f2000.constant.Constant;
import com.lp.f2000.entity.Image;
import com.lp.f2000.entity.Product;
import com.lp.f2000.service.ImageService;
import com.lp.f2000.service.ProductService;
import com.lp.f2000.util.StringUtil;

@RestController
@RequestMapping(value = "admin", produces = "application/json;charset=UTF-8")
public class ProductAdminController {
	@Autowired
	private ProductService productService;
	@Autowired
	private ImageService imageService;
	
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
		for(Product p : ps) {
			p.setBroadcastImages(imageService.getImagesByType(p.getId(), Constant.IMAGE_PRODUCT_BROADCAST));
			p.setSmallImages(imageService.getImagesByType(p.getId(), Constant.IMAGE_PRODUCT_SMALL));
			p.setThumbImages(imageService.getImagesByType(p.getId(), Constant.IMAGE_PRODUCT_THUMB));
			p.setDetailImages(imageService.getImagesByType(p.getId(), Constant.IMAGE_PRODUCT_DETAIL));
		}
		return Response.ofSuccess(ps);
	}

	
	@PostMapping(value = "add_product")
	public Response addProduct(@RequestParam(value = "name") String name,
			@RequestParam(value = "desc1", required = false) String desc1,
			@RequestParam(value = "desc2", required = false) String desc2,
			@RequestParam(value = "desc3", required = false) String desc3,
			@RequestParam(value = "images1", required = false) String images1,
			@RequestParam(value = "images2", required = false) String images2,
			@RequestParam(value = "images3", required = false) String images3,
			@RequestParam(value = "images4", required = false) String images4
	) {
		Product p = new Product();
		p.setName(name);
		p.setDesc1(desc1);
		p.setDesc2(desc2);
		p.setDesc3(desc3);
		int pid = productService.insert(p);
		//插入图片
		//1缩略图1  2轮播图 5 3商品小图1 4详情图10
		if(images1!=null) {
			int pt_count = imageService.imageCountByType(pid, Constant.IMAGE_PRODUCT_THUMB);
			if(pt_count<1) {
				String[] strs = images1.split(",");
				for(String str : strs) {
					Image image = new Image();
					image.setPostUrl(str);
					image.setResourceId(pid);
					image.setResourceType(Constant.IMAGE_PRODUCT_THUMB);
					imageService.insert(image);
				}
			}
		}
		
		if(images2!=null) {
			int pt_count = imageService.imageCountByType(pid, Constant.IMAGE_PRODUCT_BROADCAST);
			if(pt_count<5) {
				String[] strs = images1.split(",");
				for(String str : strs) {
					Image image = new Image();
					image.setPostUrl(str);
					image.setResourceId(pid);
					image.setResourceType(Constant.IMAGE_PRODUCT_BROADCAST);
					imageService.insert(image);
				}
			}
		}
		if(images3!=null) {
			int pt_count = imageService.imageCountByType(pid, Constant.IMAGE_PRODUCT_SMALL);
			if(pt_count<1) {
				String[] strs = images1.split(",");
				for(String str : strs) {
					Image image = new Image();
					image.setPostUrl(str);
					image.setResourceId(pid);
					image.setResourceType(Constant.IMAGE_PRODUCT_SMALL);
					imageService.insert(image);
				}
			}
		}
		if(images4!=null) {
			int pt_count = imageService.imageCountByType(pid, Constant.IMAGE_PRODUCT_DETAIL);
			if(pt_count<10) {
				String[] strs = images1.split(",");
				for(String str : strs) {
					Image image = new Image();
					image.setPostUrl(str);
					image.setResourceId(pid);
					image.setResourceType(Constant.IMAGE_PRODUCT_DETAIL);
					imageService.insert(image);
				}
			}
		}
		return Response.ofSuccess();
	}
	
	@PostMapping(value = "update_product")
	public Response updateProduct(@RequestParam(value = "id") int id,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "desc1", required = false) String desc1,
			@RequestParam(value = "desc2", required = false) String desc2,
			@RequestParam(value = "desc3", required = false) String desc3
	) {
		Product p = new Product();
		p.setName(name);
		p.setDesc1(desc1);
		p.setDesc2(desc2);
		p.setDesc3(desc3);
		p.setId(id);
		Timestamp updateTime = new Timestamp(System.currentTimeMillis()); 
		p.setUpdateTime(updateTime);
		productService.updateProduct(p);
		return Response.ofSuccess();
	}
	
	@PostMapping(value = "add_product_image")
	public Response addProductImage(@RequestParam(value = "product_id") int productId,
			@RequestParam(value = "image_type") int imageType,
			@RequestParam(value = "post_url") String postUrl
	) {
		if(imageType!=Constant.IMAGE_PRODUCT_THUMB&&imageType!=Constant.IMAGE_PRODUCT_BROADCAST&&imageType!=Constant.IMAGE_PRODUCT_SMALL&&imageType!=Constant.IMAGE_PRODUCT_DETAIL) {
			return Response.ofParamError("图片类型错误！");
		}
		
		Image image = new Image();
		image.setPostUrl(postUrl);
		image.setResourceId(productId);
		image.setResourceType(imageType);
		imageService.addImageUrl(image);
		return Response.ofSuccess();
	}
	
	@PostMapping(value = "update_product_image")
	public Response updateProductImage(@RequestParam(value = "image_id") int imageId,
			@RequestParam(value = "post_url") String postUrl
	) {
		imageService.updateImageUrl(imageId, postUrl);
		return Response.ofSuccess();
	}
	
	@PostMapping(value = "delete_product_image")
	public Response deleteProductImage(@RequestParam(value = "image_id") int imageId
	) {
		imageService.deleteImage(imageId);
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
