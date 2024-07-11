package com.seller.Seller.service;

import java.util.List;

import com.seller.Seller.model.User;

public interface UserService {
	
    User addUser(User user);

	User getUserByEmailAndStatus(String emailId, String status);

	User getUserByEmailid(String emailId);

	List<User> getUserByRole(String role);
	
	User getUserById(int userId);
	
	User getUserByEmailIdAndRoleAndStatus(String emailId, String role, String status);
	
	public List<User> getUserByRoleAndStatus(String role, String status);
	
	public List<User> getUserBySellerAndRoleAndStatusIn(User seller, String role, List<String> status);
	
	 User updateUser(User user);


}
