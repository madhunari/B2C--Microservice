package com.seller.Seller.service;

import java.util.List;

import com.seller.Seller.model.Product;

public interface ProductService {

	Product addProduct(Product Product);

	List<Product> getAllProductByStatusIn(List<String> status);

	public Product getProductById(int productId);

	public Product updateProduct(Product product);
}
