package com.seller.Seller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.seller.Seller.dto.CartRequestDto;
import com.seller.Seller.dto.CartResponseDto;
import com.seller.Seller.dto.CommonApiResponse;
import com.seller.Seller.utility.CustomerFeign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/api/cart")
public class CustomerCartController {

	@Autowired
	private CustomerFeign customerCartFeign;
	
	@PostMapping("/api/add")
	@CircuitBreaker(fallbackMethod = "fallback", name = "Customer-Module")
	public ResponseEntity<CommonApiResponse> addCategory(@RequestBody CartRequestDto request){
		return customerCartFeign.addCategory(request);
	}

	@PutMapping("/api/update")
	@CircuitBreaker(fallbackMethod = "fallback", name = "Customer-Module")
	ResponseEntity<CartResponseDto> updateCart(@RequestBody CartRequestDto request){
		return customerCartFeign.updateCart(request);
	}

	@GetMapping("/api/fetch")
	@CircuitBreaker(fallbackMethod = "fallback" ,name = "Customer-Module")
	public ResponseEntity<CartResponseDto> fetchUserCart(@RequestParam("userId") int userId){
		return customerCartFeign.fetchUserCart(userId);
	}

	@DeleteMapping("/api/delete")
	@CircuitBreaker(fallbackMethod = "fallback", name = "Customer-Module")
	public ResponseEntity<CartResponseDto> deleteCart(@RequestBody CartRequestDto request){
		return customerCartFeign.deleteCart(request);
	}
	
	public ResponseEntity<?> fallback(java.lang.Throwable t){
		return ResponseEntity.ok("Delivery Module is Down");
	}
}
