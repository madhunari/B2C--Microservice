package com.admin.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.admin.admin.dto.CommonApiResponse;
import com.admin.admin.dto.ProductResponseDto;
import com.admin.admin.dto.UserRequestDto;
import com.admin.admin.model.Category;
import com.admin.admin.resource.UserResource;
import com.admin.admin.service.CategoryService;
import com.admin.admin.utility.SellerFeign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("api/user")
public class UserController {

	private final Logger LOG = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserResource userResource;

	@Autowired
	private SellerFeign feign;

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/adminModuleTest")
	public String test() {
		return "Admin Module Started Successfully...";
	}

	@GetMapping("/adminDbTest")
	public String fetchCategory() {
		Category c = categoryService.getCategoryById(1);
		String name = c.getName();
		return name;
	}

	@PostMapping("/admin/register")
	public ResponseEntity<CommonApiResponse> registerAdmin(@RequestBody UserRequestDto request) {
		LOG.info("Received request to register Admin");

		try {
			ResponseEntity<CommonApiResponse> responseEntity = userResource.registerAdmin(request);
			LOG.info("Admin registration request processed successfully");
			return responseEntity;
		} catch (Exception e) {
			LOG.error("Error occurred during admin registration: {}", e.getMessage(), e);
			CommonApiResponse errorResponse = new CommonApiResponse();
			errorResponse.setResponseMessage("Admin registration failed");
			errorResponse.setSuccess(false);
			return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@CircuitBreaker(fallbackMethod = "fallback", name = "Seller")
	@GetMapping(value = "fetch/all")
	public ResponseEntity<ProductResponseDto> fetchAllProduct() {
		return this.feign.fetchAllProducts();
	}

	public ResponseEntity<String> fallback(java.lang.Throwable t) {

		return new ResponseEntity<>("Seller Module is Down", HttpStatus.BAD_GATEWAY);
	}

	@GetMapping("fetch")
	public ResponseEntity<ProductResponseDto> fetchProductById(@RequestParam(name = "productId") int productId) {
		return this.feign.fetchProductById(productId);
	}

	@DeleteMapping("delete/seller")
	public ResponseEntity<CommonApiResponse> deleteSeller(@RequestParam("sellerId") int sellerId) {
		return this.feign.deleteSeller(sellerId);
	}

}
