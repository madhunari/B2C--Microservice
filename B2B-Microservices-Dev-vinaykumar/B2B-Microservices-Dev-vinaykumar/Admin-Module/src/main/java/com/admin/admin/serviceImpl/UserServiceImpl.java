package com.admin.admin.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.admin.model.User;
import com.admin.admin.repository.UserRepository;
import com.admin.admin.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userrepo;

	@Override
	public User getUserByEmailAndStatus(String emailId, String status) {
		try {
			return userrepo.findByEmailIdAndStatus(emailId, status);
		} catch (Exception e) {
			// LOG.error("An error occurred while retrieving user by email and status:
			// emailId={}, status={}", emailId, status, e);
			throw new RuntimeException("Error occurred while fetching user by email and status", e);
		}
	}

	@Override
	public User getUserByEmailid(String emailId) {
		return userrepo.findByEmailId(emailId);
	}

	@Override
	public User addUser(User user) {
		return userrepo.save(user);
	}

	@Override
	public User getUserByEmailIdAndRoleAndStatus(String emailId, String role, String status) {
		return this.userrepo.findByEmailIdAndRoleAndStatus(emailId, role, status);
	}

}
