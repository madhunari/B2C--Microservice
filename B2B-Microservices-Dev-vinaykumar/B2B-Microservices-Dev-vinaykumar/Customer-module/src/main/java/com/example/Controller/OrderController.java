package com.example.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.DTO.CommonApiResponse;
import com.example.DTO.OrderResponseDto;
import com.example.resources.OrderResource;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("api/order")
public class OrderController {

	@Autowired
	private OrderResource orderResource;

	@PostMapping("/add")
	@Operation(summary = "Api to order the products in Cart")
	public ResponseEntity<CommonApiResponse> placeOrder(@RequestParam("userId") int userId) {
		return orderResource.orderProductsFromCart(userId);
	}
	
	
	
	@GetMapping("/fetch/user-wise")
	@Operation(summary = "Api to fetch user orders")
	public ResponseEntity<OrderResponseDto> fetchUserOrders(@RequestParam("userId") int userId) {
		return orderResource.fetchUserOrders(userId);
	}
}
