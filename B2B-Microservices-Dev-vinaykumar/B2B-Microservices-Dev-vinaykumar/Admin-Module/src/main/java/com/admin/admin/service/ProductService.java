package com.admin.admin.service;

import java.util.List;

import com.admin.admin.model.Category;
import com.admin.admin.model.Product;

public interface ProductService {

	List<Product> getAllProductByCategoryAndStatusIn(Category category, List<String> status);

	List<Product> updateAllProduct(List<Product> products);

}
