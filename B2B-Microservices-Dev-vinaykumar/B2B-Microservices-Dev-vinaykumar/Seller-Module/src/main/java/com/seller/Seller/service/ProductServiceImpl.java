package com.seller.Seller.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seller.Seller.model.Product;
import com.seller.Seller.repository.ProductRepository;


@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productrepo;

	@Override
	public Product addProduct(Product product) {
		return productrepo.save(product);
	}


	@Override
	public List<Product> getAllProductByStatusIn(List<String> status) {
		return this.productrepo.findByStatusIn(status);
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
	
	@Override
	public Product updateProduct(Product product) {
		return productrepo.save(product);
	}



}
