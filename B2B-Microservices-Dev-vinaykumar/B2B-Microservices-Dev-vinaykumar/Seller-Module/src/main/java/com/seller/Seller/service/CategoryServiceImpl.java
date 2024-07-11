package com.seller.Seller.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seller.Seller.model.Category;
import com.seller.Seller.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryrepo;

	@Override
	public Category addCategory(Category category) {
			return this.categoryrepo.save(category);
		}

	@Override
	public Category getCategoryById(int categoryId) {

		Optional<Category> optionalCategory = this.categoryrepo.findById(categoryId);

		if (optionalCategory.isPresent()) {
			return optionalCategory.get();
		} else {
			return null;
		}
	}
	}

