package com.seller.Seller.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.seller.Seller.dto.OrderResponseDto;
import com.seller.Seller.dto.UpdateDeliveryStatusRequest;

@FeignClient("Delivery-Module")
public interface DeliveryFeign {
	
	@PutMapping("/api/order/update/delivery-status")
	public ResponseEntity<OrderResponseDto> updateDeliveryStatus(@RequestBody UpdateDeliveryStatusRequest request);
		
	@GetMapping("/api/order/fetch/delivery-wise")
	public ResponseEntity<OrderResponseDto> fetchDeliveryOrders(
			@RequestParam("deliveryPersonId") int deliveryPersonId);

}
