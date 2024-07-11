package com.seller.Seller.service;

import com.seller.Seller.model.Category;

public interface CategoryService {
	

	Category addCategory(Category category);
	
	Category getCategoryById(int category);


}
