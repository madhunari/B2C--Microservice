package com.admin.admin.dto;

import java.util.ArrayList;
import java.util.List;

import com.admin.admin.model.Product;





public class ProductResponseDto extends CommonApiResponse {

	private List<Product> products = new ArrayList<>();

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
