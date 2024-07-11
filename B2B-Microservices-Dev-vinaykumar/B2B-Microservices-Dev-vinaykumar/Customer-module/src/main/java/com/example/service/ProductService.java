package com.example.service;

import java.util.List;

import com.example.Model.Product;

public interface ProductService {

	Product addProduct(Product product);

	Product updateProduct(Product product);

	List<Product> updateAllProduct(List<Product> products);

	Product getProductById(int productId);

}
