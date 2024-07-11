package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Model.Product;
import com.example.repo.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productrepo;

	@Override
	public Product addProduct(Product product) {
		return productrepo.save(product);
	}

	@Override
	public Product updateProduct(Product product) {
		return productrepo.save(product);
	}

	@Override
	public List<Product> updateAllProduct(List<Product> products) {
		return this.productrepo.saveAll(products);
	}

	@Override
	public Product getProductById(int productId) {

		Optional<Product> optionalProduct = productrepo.findById(productId);

		if (optionalProduct.isPresent()) {
			return optionalProduct.get();
		} else {
			return null;
		}

	}

}
