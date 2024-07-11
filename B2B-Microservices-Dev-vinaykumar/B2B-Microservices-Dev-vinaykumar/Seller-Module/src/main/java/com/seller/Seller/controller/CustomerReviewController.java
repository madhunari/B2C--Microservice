package com.seller.Seller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seller.Seller.dto.AddReviewRequest;
import com.seller.Seller.dto.CommonApiResponse;
import com.seller.Seller.utility.CustomerFeign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/api/product/review")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerReviewController {

	@Autowired
	private CustomerFeign customerFeign;

	@PostMapping("/add")
	@CircuitBreaker(fallbackMethod = "fallback", name = "Customer-Module")
	public ResponseEntity<CommonApiResponse> addProductReview(@RequestBody AddReviewRequest review){
		return customerFeign.addProductReview(review);
	}
	
	public ResponseEntity<?> fallback(java.lang.Throwable t){
		return ResponseEntity.ok("Unable to add Product might be Customer module is down");
	}

	

}
