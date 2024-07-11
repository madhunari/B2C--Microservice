package com.admin.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.admin.model.Category;
import com.admin.admin.model.Product;
import com.admin.admin.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository prodrepo;

	@Override
	public List<Product> getAllProductByCategoryAndStatusIn(Category category, List<String> status) {
		return this.prodrepo.findByCategoryAndStatusIn(category, status);
	}

	
	@Override
	public List<Product> updateAllProduct(List<Product> products) {
		return this.prodrepo.saveAll(products);
	}

}
