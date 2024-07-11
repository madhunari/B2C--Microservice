package com.seller.Seller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.seller.Seller.Feign.DeliveryFeign;
import com.seller.Seller.dto.OrderResponseDto;
import com.seller.Seller.dto.UpdateDeliveryStatusRequest;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/api/order")
public class DeliveryControllerFeign {

	@Autowired
	private DeliveryFeign deliveryFeign;
	
	@PutMapping("/update/delivery-status")
	@CircuitBreaker(fallbackMethod = "fallback", name = "Delivery-Module")
	public ResponseEntity<OrderResponseDto> updateDeliveryStatus(@RequestBody UpdateDeliveryStatusRequest request){
		return deliveryFeign.updateDeliveryStatus(request);
	}
		
	@GetMapping("/fetch/delivery-wise")
	@CircuitBreaker(fallbackMethod = "fallback", name = "Delivery-Module")
	public ResponseEntity<OrderResponseDto> fetchDeliveryOrders(
			@RequestParam("deliveryPersonId") int deliveryPersonId){
		return deliveryFeign.fetchDeliveryOrders(deliveryPersonId);
	}
	
	public ResponseEntity<?> fallback(java.lang.Throwable t){
		return ResponseEntity.ok("Delivery Module is Down");
	}
}
