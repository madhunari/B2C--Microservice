package com.example.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.DTO.ProductResponse;

@FeignClient("Seller-Module")
public interface SellerFeign {

	@GetMapping("/product/getProduct/{id}")
	public ResponseEntity<ProductResponse> getProduct(@PathVariable("id") int id);
	
	@GetMapping("/product/getAllProduct")
	public ResponseEntity<ProductResponse> getProduct();
}
