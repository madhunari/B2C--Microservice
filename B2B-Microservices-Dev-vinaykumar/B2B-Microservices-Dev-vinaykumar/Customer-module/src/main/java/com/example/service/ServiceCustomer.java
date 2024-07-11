package com.example.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Exceptions.ApiError;
import com.example.Model.User;	
import com.example.customExceptions.CustomExceptions;
import com.example.repo.UserRepository;

@Service
public class ServiceCustomer {

	private final Logger log = LoggerFactory.getLogger(ServiceCustomer.class);

	@Autowired
	private UserRepository rc;

	public ResponseEntity<ApiError> add(User r) {

		log.info("Request Receieved for Registration");

		ApiError ae = new ApiError();
		if (r == null) {
			ae.setResponse("Inputs Missings");
			ae.setSuccess(true);
			return new ResponseEntity<ApiError>(ae, HttpStatus.BAD_REQUEST);
		}
		try {
			rc.save(r);
			ae.setResponse("Registration Successfull");
			ae.setSuccess(true);
			return new ResponseEntity<ApiError>(ae, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			log.info("Something Went Wrong During Registration");
			ae.setResponse("Something Went Wrong try again...");
			ae.setSuccess(false);
			return new ResponseEntity<ApiError>(ae, HttpStatus.BAD_REQUEST);
		}
	}

	public List<User> getUserDetails() {

		log.info("Request Receieved to get all Users");
		return rc.findAll();
	}

	public ResponseEntity<User> getById(int id) {

		log.info("Request Receieved to get users By ID");

		Optional<User> byId = rc.findById(id);
		if (byId.isPresent()) {
			return new ResponseEntity<User>(rc.findById(id).get(), HttpStatus.ACCEPTED);

		} else {
			System.out.println("exception in service");
			throw new CustomExceptions("ID not found");
		}
	}

	public ResponseEntity<String> deleteById(int id) {

		log.info("Request Receieved Delete user by ID");

		// ApiError ae = new ApiError();

		if (rc.findById(id).isPresent()) {
			rc.deleteById(id);
			return new ResponseEntity<String>(" User Deleted Successfully...", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("User doesn't Exist to Delete", HttpStatus.BAD_REQUEST);
		}

	}

	public ResponseEntity<ApiError> login(String emailid, String password) {

		log.info("Request Receieved for LOGIN");

		ApiError ae = new ApiError();

		List<User> list = rc.findByEmailIdAndPassword(emailid, password);
		if (list.size() == 0) {
			ae.setResponse("Login Failed");
			ae.setSuccess(false);
			return new ResponseEntity<ApiError>(ae, HttpStatus.BAD_REQUEST);
		} else {
			ae.setResponse("Login Success");
			ae.setSuccess(true);
			return new ResponseEntity<ApiError>(ae, HttpStatus.ACCEPTED);
		}
	}

	public ResponseEntity<ApiError> updates(User r) {
		log.info("Request ReceieveD to Update User");
		ApiError ae = new ApiError();

		List<User> ll = rc.findAll();
		if (ll.size() == 0) {
			ae.setResponse("Update Success");
			ae.setSuccess(true);
			rc.save(r);
			return new ResponseEntity<ApiError>(ae, HttpStatus.ACCEPTED);
		} else {
			ae.setResponse("Update Failed");
			ae.setSuccess(false);
			return new ResponseEntity<ApiError>(ae, HttpStatus.BAD_REQUEST);
		}

	}

}
