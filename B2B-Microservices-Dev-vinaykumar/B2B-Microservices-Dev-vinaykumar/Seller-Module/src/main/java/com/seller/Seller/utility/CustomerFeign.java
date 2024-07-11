package com.seller.Seller.utility;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.seller.Seller.dto.AddReviewRequest;
import com.seller.Seller.dto.CartRequestDto;
import com.seller.Seller.dto.CartResponseDto;
import com.seller.Seller.dto.CommonApiResponse;
import com.seller.Seller.dto.OrderResponseDto;

@FeignClient("Customer-Module")
public interface CustomerFeign {
	
	@PostMapping("/api/add")
	public ResponseEntity<CommonApiResponse> addCategory(@RequestBody CartRequestDto request);

	@PutMapping("/api/update")
	ResponseEntity<CartResponseDto> updateCart(@RequestBody CartRequestDto request);

	@GetMapping("/api/fetch")
	public ResponseEntity<CartResponseDto> fetchUserCart(@RequestParam("userId") int userId);

	@DeleteMapping("/api/delete")
	public ResponseEntity<CartResponseDto> deleteCart(@RequestBody CartRequestDto request);
	
	//Order API'S
	

	@PostMapping("/api/order/add")
	public ResponseEntity<CommonApiResponse> placeOrder(@RequestParam("userId") int userId);
	
	@GetMapping("/api/order/fetch/user-wise")
	public ResponseEntity<OrderResponseDto> fetchUserOrders(@RequestParam("userId") int userId);

	@PostMapping("/api/product/review/add")
	public ResponseEntity<CommonApiResponse> addProductReview(AddReviewRequest review);

}
