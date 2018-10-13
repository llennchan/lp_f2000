package com.lp.f2000.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lp.f2000.entity.Category;
import com.lp.f2000.mapper.CategoryMapper;

@Service
public class CategoryImpl implements CategoryService {
	@Autowired
	private CategoryMapper categoryMapper;
	
	@Override
	public Category getById(int id) {
		return categoryMapper.getCategoryById(id);
	}

	@Override
	public void insert(Category category) {
		// TODO Auto-generated method stub
		categoryMapper.insertCategory(category);
	}
	
	@Override
	public void updateCategory(Category category) {
		categoryMapper.updateCategory(category);
	}

	@Override
	public List<Category> listCategories(int pid) {
		// TODO Auto-generated method stub
		return categoryMapper.listCategoriesByPid(pid);
	}

	@Override
	public void deleteCategory(int id) {
		// TODO Auto-generated method stub
		categoryMapper.deleteCategory(id);
	}

}
