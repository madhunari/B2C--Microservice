package com.example.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.DTO.ProductResponse;
import com.example.Exceptions.ApiError;
import com.example.Model.User;
import com.example.feign.SellerFeign;
import com.example.service.ServiceCustomer;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/customer")
public class ControllerRegister {

	@Autowired
	private ServiceCustomer sc;

	@Autowired
	private SellerFeign fi;

	// Register customer to db
	// localhost:8080/Customer/register
	@PostMapping("register")
	public ResponseEntity<ApiError> add(@RequestBody User r) {

		return sc.add(r);

	}

	@GetMapping("/Login")
	public ResponseEntity<ApiError> login(@RequestParam("emailId") String emailid,
			@RequestParam("password") String password) {
		ApiError ae = new ApiError();
		if (emailid == null || password == null) {
			log.info("Missing fields");
			ae.setResponse("Enter all Fields");
			ae.setSuccess(false);
			return new ResponseEntity<ApiError>(ae, HttpStatus.BAD_REQUEST);
		}
		return sc.login(emailid, password);
	}

	// Get all details of users from db
	// localhost:8080/Customer/getDetails
	@GetMapping("/getDetails")
	public List<User> getUsersDetails() {

		return sc.getUserDetails();
	}

	// Get users from id
	// localhost:8080/Customer/getById?id=1
	@GetMapping("/getById")
	public ResponseEntity<User> getById(@RequestParam("id") int id) {
		return sc.getById(id);
	}

	@PutMapping("/update")
	public ResponseEntity<ApiError> update(@RequestBody User r) {
		return sc.updates(r);
	}

	// localhost:8080/Customer/deleteCustomer?id=0
	@DeleteMapping("/deleteCustomer")
	public ResponseEntity<String> deleteById(@RequestParam("id") int id) {
		return sc.deleteById(id);

	}

	@CircuitBreaker(fallbackMethod = "fallback", name = "Seller-Module")
	@GetMapping("/getProduct/{id}")
	public ResponseEntity<ProductResponse> getProduct(@PathVariable("id") int id) {

		return fi.getProduct(id);

	}

	@CircuitBreaker(fallbackMethod = "fallback", name = "Seller-Module")
	@GetMapping("/getAllProducts")
	public ResponseEntity<ProductResponse> getProduct() {

		return fi.getProduct();

	}

	public ResponseEntity<?> fallback(java.lang.Throwable t) {
		return ResponseEntity.ok("Seller Module is Down");
	}

}
