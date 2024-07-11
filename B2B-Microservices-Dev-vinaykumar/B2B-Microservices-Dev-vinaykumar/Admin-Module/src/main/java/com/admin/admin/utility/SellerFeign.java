package com.admin.admin.utility;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.admin.admin.dto.CommonApiResponse;
import com.admin.admin.dto.ProductResponseDto;



@FeignClient("Seller")
public interface SellerFeign {
	
	@GetMapping(value = "api/user/fetch/all")
	public ResponseEntity<ProductResponseDto> fetchAllProducts();
	

    @GetMapping(value = "api/user/fetch")
	public ResponseEntity<ProductResponseDto> fetchProductById(@RequestParam(name = "productId") int productId);
    
	@DeleteMapping("api/user/delete/seller")
	public ResponseEntity<CommonApiResponse> deleteSeller(@RequestParam("sellerId") int sellerId);

}
