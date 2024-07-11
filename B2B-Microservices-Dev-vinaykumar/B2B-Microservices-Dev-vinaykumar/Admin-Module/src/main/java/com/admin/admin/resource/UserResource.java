package com.admin.admin.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.admin.admin.dto.CommonApiResponse;
import com.admin.admin.dto.UserRequestDto;
import com.admin.admin.model.User;
import com.admin.admin.service.UserService;
import com.admin.admin.utility.Constants.UserRole;
import com.admin.admin.utility.Constants.UserStatus;

import jakarta.transaction.Transactional;


@Component
@Transactional
public class UserResource {

	
	private final Logger LOG = LoggerFactory.getLogger(UserResource.class); 
	
	@Autowired
	private UserService userService;

	
	
	/*
	 * @Autowired private PasswordEncoder passwordEncoder;
	 */
	 
	 

	public ResponseEntity<CommonApiResponse> registerAdmin(UserRequestDto registerRequest) {
		 LOG.info("Request received for Register Admin");

		CommonApiResponse response = new CommonApiResponse();

		if (registerRequest == null) {
			response.setResponseMessage("user is null");
			response.setSuccess(false);

			 LOG.error("Received a null request for Register Admin");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		if (registerRequest.getEmailId() == null || registerRequest.getPassword() == null) {
			response.setResponseMessage("missing input");
			response.setSuccess(false);

			 LOG.warn("Received a request with missing input for Register Admin");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		User existingUser = this.userService.getUserByEmailAndStatus(registerRequest.getEmailId(),
				UserStatus.ACTIVE.value());

		if (existingUser != null) {
			response.setResponseMessage("User already registered with this Email");
			response.setSuccess(false);

			 LOG.warn("User already registered with email: {}",
			 registerRequest.getEmailId());
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		User user = UserRequestDto.toUserEntity(registerRequest);

		user.setRole(UserRole.ROLE_ADMIN.value());
		user.setPassword(registerRequest.getPassword());
		user.setStatus(UserStatus.ACTIVE.value());

		existingUser = this.userService.addUser(user);

		if (existingUser == null) {
			response.setResponseMessage("Failed to register admin");
			response.setSuccess(false);

			 LOG.error("Failed to register admin");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		response.setResponseMessage("Admin registered successfully");
		response.setSuccess(true);

		 LOG.info("Admin registered Successfully");

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
