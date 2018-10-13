package com.lp.f2000.service;

import java.util.List;

import com.lp.f2000.entity.Category;

public interface CategoryService {
	public Category getById(int id);
	
	public void insert(Category category);
	
	public void updateCategory(Category category);
	
	public List<Category> listCategories(int pid);
	
	public void deleteCategory(int id);
	
}
