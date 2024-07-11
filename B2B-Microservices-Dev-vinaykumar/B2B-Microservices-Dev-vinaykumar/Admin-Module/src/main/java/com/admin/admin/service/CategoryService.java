package com.admin.admin.service;

import java.util.List;

import com.admin.admin.model.Category;



public interface CategoryService {

	Category addCategory(Category category);

	Category updateCategory(Category category);

	Category getCategoryById(int category);

	List<Category> getCategoriesByStatusIn(List<String> status);

}
