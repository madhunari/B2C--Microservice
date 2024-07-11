package com.example.Controller;

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

import com.example.DTO.CartRequestDto;
import com.example.DTO.CartResponseDto;
import com.example.DTO.CommonApiResponse;
import com.example.resources.CartResource;

@RestController
@RequestMapping("/api/cart")
public class CartController {

	@Autowired
	private CartResource cartResource;
	
	@GetMapping("/customerModuleTest")
	public String testc() {
		return "Customer Module Started Succesfully";
	}
	
	@GetMapping("/customerDbTest")
	public ResponseEntity<CartResponseDto> dbTest() {
		return cartResource.fetchUserCartDetails(1);
	}

	@PostMapping("/add")
	public ResponseEntity<CommonApiResponse> addCategory(@RequestBody CartRequestDto request) {
		return cartResource.addToCart(request);
	}

	@PutMapping("/update")
	ResponseEntity<CartResponseDto> updateCart(@RequestBody CartRequestDto request) {
		return cartResource.updateCart(request);
	}

	@GetMapping("/fetch")
	public ResponseEntity<CartResponseDto> fetchUserCart(@RequestParam("userId") int userId) {
		return cartResource.fetchUserCartDetails(userId);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<CartResponseDto> deleteCart(@RequestBody CartRequestDto request) {
		return cartResource.deleteCart(request);
	}

}
