package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Model.User;
import com.example.repo.UserRepository;

@Service

public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userrepo;

	@Override
	public User getUserById(int userId) {

		Optional<User> optionalUser = this.userrepo.findById(userId);

		if (optionalUser.isPresent()) {
			return optionalUser.get();
		} else {
			return null;
		}

	}

}
