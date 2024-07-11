package com.example.DTO;

import com.example.Exceptions.ApiError;

public class LoginRequest extends ApiError {
	
	private String emailid;
	
	private String password;

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

}
