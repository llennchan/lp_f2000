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
import com.lp.f2000.entity.Category;
import com.lp.f2000.entity.Product;
import com.lp.f2000.service.CategoryService;
import com.lp.f2000.service.ProductService;
import com.lp.f2000.util.FileUtil;
import com.lp.f2000.util.StringUtil;

@RestController
@RequestMapping(value = "admin", produces = "application/json;charset=UTF-8")
public class CategoryAdminController {
	@Autowired
	private CategoryService categoryService;

	@GetMapping(value = "category")
	public Response<Category> category(@RequestParam("cat_id") int catId) {
		Category c = categoryService.getById(catId);
		return Response.ofSuccess(c);
	}
	
	@GetMapping(value = "categories")
	public Response<List<Category>> categories(@RequestParam("pcat_id") int pcatId) {
		List<Category> cs = categoryService.listCategories(pcatId);
		return Response.ofSuccess(cs);
	}

	
	@PostMapping(value = "add_category")
	public Response addCategory(@RequestParam(value = "name") String name, @RequestParam("pcat_id") int pcatId){
		Category c = new Category();
		c.setName(name);
		c.setParentId(pcatId);
		categoryService.insert(c);
		return Response.ofSuccess();
	}
	
	@PostMapping(value = "update_category")
	public Response updateCategory(@RequestParam(value = "name") String name, @RequestParam("pcat_id") int pcatId, @RequestParam("cat_id") int catId){
		Category c = new Category();
		c.setName(name);
		c.setId(catId);
		c.setParentId(pcatId);
		categoryService.updateCategory(c);
		return Response.ofSuccess();
	}
	
	@PostMapping(value = "delete_category")
	public Response deleteCategory(@RequestParam(value = "cat_id") int cat_id) {
		categoryService.deleteCategory(cat_id);
		return Response.ofSuccess();
	}
	


	
}
