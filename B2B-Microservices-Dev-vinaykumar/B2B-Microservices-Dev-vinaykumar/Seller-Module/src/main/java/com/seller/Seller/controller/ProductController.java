package com.seller.Seller.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seller.Seller.dto.CommonApiResponse;
import com.seller.Seller.dto.ProductAddRequest;
import com.seller.Seller.model.Product;
import com.seller.Seller.repository.ProductRepository;
import com.seller.Seller.resource.ProductResource;

@RestController
@RequestMapping("api/product")
public class ProductController {

	@Autowired
	private ProductResource productResource;

	@Autowired
	private ProductRepository productrepo;

	@GetMapping("/SellerModuleTest")
	public String tests() {
		return "Seller module started successfully";
	}

	@GetMapping("/SellerDbTest")
	public String getProductById(){

		Optional<Product> optionalProduct = productrepo.findById(1);

		if (optionalProduct.isPresent()) {
			Product product = optionalProduct.get();
			return product.getName();
		} else {
			return "No products Available";
		}
	}

	@PostMapping("add")
	// @Operation(summary = "Api to add product")
	public ResponseEntity<CommonApiResponse> addProduct(ProductAddRequest productDto) {
		return this.productResource.addProduct(productDto);
	}

}
