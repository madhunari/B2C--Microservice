package com.seller.Seller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.seller.Seller.dto.CommonApiResponse;
import com.seller.Seller.dto.OrderResponseDto;
import com.seller.Seller.utility.CustomerFeign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/api/order")
public class CustomerOrderController {

	@Autowired
	private CustomerFeign customerCartFeign;

	@PostMapping("/api/order/add")
	@CircuitBreaker(fallbackMethod = "fallback", name = "Customer-Module")
	public ResponseEntity<CommonApiResponse> placeOrder(@RequestParam("userId") int userId) {
		return customerCartFeign.placeOrder(userId);
	}

	@GetMapping("/api/order/fetch/user-wise")
	public ResponseEntity<OrderResponseDto> fetchUserOrders(@RequestParam("userId") int userId) {
		return customerCartFeign.fetchUserOrders(userId);
	}
	
	public ResponseEntity<?> fallback(java.lang.Throwable t){
		return ResponseEntity.ok("Unable to Orders... Customer Module might be down");
	}
}
