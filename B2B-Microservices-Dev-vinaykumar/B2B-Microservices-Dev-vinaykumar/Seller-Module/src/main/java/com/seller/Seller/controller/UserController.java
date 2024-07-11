package com.seller.Seller.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.seller.Seller.dto.CommonApiResponse;
import com.seller.Seller.dto.ProductAddRequest;
import com.seller.Seller.dto.ProductDetailUpdateRequest;
import com.seller.Seller.dto.ProductResponseDto;
import com.seller.Seller.dto.RegisterUserRequestDto;
import com.seller.Seller.dto.UserLoginRequest;
import com.seller.Seller.dto.UserLoginResponse;
import com.seller.Seller.dto.UserResponseDto;
import com.seller.Seller.resource.ProductResource;
import com.seller.Seller.resource.UserResource;
import com.seller.Seller.service.UserServiceImpl;

@RestController
@RequestMapping("api/user")
public class UserController {

	private final Logger LOG = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserResource userResource;

	@Autowired
	private UserServiceImpl service;

	@Autowired
	private ProductResource productResource;
	
	/*
	 * @GetMapping("/sellerModuleTest") public String testSeller() { return
	 * "Seller Module Started Succesfully"; }
	 * 
	 * @GetMapping("/sellerDbTest") public ResponseEntity<ProductResponseDto>
	 * fetchProductById() { return this.productResource.fetchProductById(1); }
	 */

	@PostMapping("register")
	public ResponseEntity<CommonApiResponse> registerUser(@RequestBody RegisterUserRequestDto request) {
		LOG.info("Received request to register user");
		try {
			ResponseEntity<CommonApiResponse> responseEntity = this.userResource.registerUser(request);
			LOG.info("User registration completed successfully");
			return responseEntity;
		} catch (Exception e) {
			LOG.error("Error occurred during user registration: {}", e.getMessage(), e);
			CommonApiResponse errorResponse = new CommonApiResponse();
			errorResponse.setResponseMessage("User registration failed");
			errorResponse.setSuccess(false);
			return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("login")
	public ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginRequest userLoginRequest) {
		LOG.info("Received request to login user");

		try {
			ResponseEntity<UserLoginResponse> responseEntity = userResource.login(userLoginRequest);
			LOG.info("User login request processed successfully");
			return responseEntity;
		} catch (Exception e) {
			LOG.error("Error occurred during user login: {}", e.getMessage(), e);
			UserLoginResponse errorResponse = new UserLoginResponse();
			errorResponse.setResponseMessage("User login failed");
			errorResponse.setSuccess(false);
			return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/fetch/role")
	public ResponseEntity<UserResponseDto> fetchAllUsersByRole(@RequestParam("role") String role)
			throws JsonProcessingException {
		LOG.info("Received request to fetch users by role: {}", role);

		try {
			ResponseEntity<UserResponseDto> responseEntity = userResource.getUsersByRole(role);
			LOG.info("User fetch by role request processed successfully");
			return responseEntity;
		} catch (Exception e) {
			LOG.error("Error occurred while fetching users by role: {}", e.getMessage(), e);
			// Handle the error and return an appropriate response
			UserResponseDto errorResponse = new UserResponseDto();
			errorResponse.setResponseMessage("Failed to fetch users by role");
			errorResponse.setSuccess(false);
			return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("add")
	public ResponseEntity<CommonApiResponse> addProduct(ProductAddRequest productDto) {
		return this.productResource.addProduct(productDto);
	}

	@GetMapping("fetch/all")
	public ResponseEntity<ProductResponseDto> fetchAllProduct() {
		return this.productResource.fetchAllProducts();
	}

	@GetMapping("fetch")
	public ResponseEntity<ProductResponseDto> fetchProductById(@RequestParam(name = "productId") int productId) {
		return this.productResource.fetchProductById(productId);
	}

	@PutMapping("update/detail")
	public ResponseEntity<CommonApiResponse> updateProductDetails(@RequestBody ProductDetailUpdateRequest request) {
		System.out.println(request);
		return this.productResource.updateProductDetail(request);
	}

	@PostMapping("/forgot-password")
	public String forgotPass(@RequestParam String email) {
		String response = service.forgotPass(email);

		if (!response.startsWith("Invalid")) {
			response = "http://localhost:8080/reset-password?token=" + response;
		}
		return response;
	}

	@PutMapping("/reset-password")
	public String resetPass(@RequestParam String token, @RequestParam String password) {
		return service.resetPass(token, password);
	}

	@GetMapping("/fetch/seller/delivery-person")
	public ResponseEntity<UserResponseDto> fetchDeliveryPerson(@RequestParam("sellerId") int sellerId) {
		return userResource.getDeliveryPersonsBySeller(sellerId);
	}

	@DeleteMapping("delete/seller/delivery-person")
	public ResponseEntity<CommonApiResponse> deleteDeliveryPerson(@RequestParam("deliveryId") int deliveryId) {
		return userResource.deleteDeliveryPerson(deliveryId);
	}

	@DeleteMapping("delete")
	public ResponseEntity<CommonApiResponse> deleteProduct(@RequestParam("productId") int productId,
			@RequestParam("sellerId") int sellerId) {
		return this.productResource.deleteProduct(productId, sellerId);
	}

	@PutMapping("update/image")
	public ResponseEntity<CommonApiResponse> updateProductDetails(ProductAddRequest request) {
		return this.productResource.updateProductImage(request);
	}

}
