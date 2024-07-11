package com.admin.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.admin.admin.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	User findByEmailId(String email);

	User findByEmailIdAndStatus(String email, String status);

	User findByRoleAndStatusIn(String role, List<String> status);
	
	User findByEmailIdAndRoleAndStatus(String emailId, String role, String status);

}
