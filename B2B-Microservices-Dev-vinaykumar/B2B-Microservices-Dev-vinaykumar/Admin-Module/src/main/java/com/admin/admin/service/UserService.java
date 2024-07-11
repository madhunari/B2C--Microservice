package com.admin.admin.service;

import com.admin.admin.model.User;

public interface UserService {

	User addUser(User user);

	User getUserByEmailAndStatus(String emailId, String status);

	User getUserByEmailid(String emailId);
	
	public User getUserByEmailIdAndRoleAndStatus(String emailId, String role, String status);
}
