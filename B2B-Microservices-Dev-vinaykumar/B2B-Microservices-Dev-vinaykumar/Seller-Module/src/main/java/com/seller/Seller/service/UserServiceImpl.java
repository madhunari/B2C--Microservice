package com.seller.Seller.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seller.Seller.model.User;
import com.seller.Seller.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService{
	
	private final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
	
	   private static final long EXPIRE_TOKEN=30;
	
	@Autowired
	private UserRepository userrepo;

	@Override
	public User addUser(User user) {
	
		return userrepo.save(user);
	}

	@Override
	public User getUserByEmailAndStatus(String emailId, String status) {
		try {
	        return userrepo.findByEmailIdAndStatus(emailId, status);
	    } catch (Exception e) {
	        LOG.error("An error occurred while retrieving user by email and status: emailId={}, status={}", emailId, status, e);
	        throw new RuntimeException("Error occurred while fetching user by email and status", e);
	    }
	}
	

	@Override
	public User getUserByEmailid(String emailId) {
		return userrepo.findByEmailId(emailId);
	}

	@Override
	public List<User> getUserByRole(String role) {
		return userrepo.findByRole(role);
	}

	@Override
	public User getUserById(int userId) {

		Optional<User> optionalUser = this.userrepo.findById(userId);

		if (optionalUser.isPresent()) {
			return optionalUser.get();
		} else {
			return null;
		}
	}
	

	@Override
	public User getUserByEmailIdAndRoleAndStatus(String emailId, String role, String status) {
		return this.userrepo.findByEmailIdAndStatus(emailId, status);
	}
	

	@Override
	public List<User> getUserByRoleAndStatus(String role, String status) {
		return this.userrepo.findByRoleAndStatus(role, status);
	}

    public String forgotPass(String email){
        Optional<User> userOptional = Optional.ofNullable(userrepo.findByEmailId(email));

        if(!userOptional.isPresent()){
            return "Invalid email id.";
        }

        User user=userOptional.get();
        user.setToken(generateToken());
        user.setTokenCreationDate(LocalDateTime.now());

        user=userrepo.save(user);
        return user.getToken();
    }

    public String resetPass(String token, String password){
        Optional<User> userOptional= Optional.ofNullable(userrepo.findByToken(token));

        if(!userOptional.isPresent()){
            return "Invalid token";
        }
        LocalDateTime tokenCreationDate = userOptional.get().getTokenCreationDate();

        if (isTokenExpired(tokenCreationDate)) {
            return "Token expired.";

        }

        User user = userOptional.get();

        user.setPassword(password);
        user.setToken(null);
        user.setTokenCreationDate(null);

        userrepo.save(user);

        return "Your password successfully updated.";
    }

    private String generateToken() {
        StringBuilder token = new StringBuilder();

        return token.append(UUID.randomUUID().toString())
                .append(UUID.randomUUID().toString()).toString();
    }

    private boolean isTokenExpired(final LocalDateTime tokenCreationDate) {

        LocalDateTime now = LocalDateTime.now();
        Duration diff = Duration.between(tokenCreationDate, now);

        return diff.toMinutes() >=EXPIRE_TOKEN;
    }
    
    
	@Override
	public List<User> getUserBySellerAndRoleAndStatusIn(User seller, String role, List<String> status) {
		return this.userrepo.findBySellerAndRoleAndStatusIn(seller, role, status);
	}
	
	@Override
	public User updateUser(User user) {
		return userrepo.save(user);
	}


}



