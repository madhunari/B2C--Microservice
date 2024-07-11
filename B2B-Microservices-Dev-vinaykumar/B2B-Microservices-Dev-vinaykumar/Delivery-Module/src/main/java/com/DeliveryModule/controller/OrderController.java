package com.DeliveryModule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.DeliveryModule.DTO.OrderResponseDto;
import com.DeliveryModule.DTO.UpdateDeliveryStatusRequest;
import com.DeliveryModule.resource.OrderResource;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/order")
//@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {

	@Autowired
	private OrderResource orderResource;

	
	@GetMapping("/deliveryModuleTest")
	public String testc() {
		return "Delivery Module Started Succesfully";
	}
	
	@GetMapping("/deliveryDbTest")
	public ResponseEntity<OrderResponseDto> fetchDeliveryOrders() {
		return orderResource.fetchDeliveryOrders(1);
	}
	
	@PutMapping("/update/delivery-status")
	@Operation(summary = "Api to update the delivery status of Order")
	public ResponseEntity<OrderResponseDto> updateDeliveryStatus(@RequestBody UpdateDeliveryStatusRequest request) {
		return orderResource.updateDeliveryStatus(request);
	}

	@GetMapping("/fetch/delivery-wise")
	@Operation(summary = "Api to fetch delivery person orders")
	public ResponseEntity<OrderResponseDto> fetchDeliveryOrders(
			@RequestParam("deliveryPersonId") int deliveryPersonId) {
		return orderResource.fetchDeliveryOrders(deliveryPersonId);
	}

	

}
