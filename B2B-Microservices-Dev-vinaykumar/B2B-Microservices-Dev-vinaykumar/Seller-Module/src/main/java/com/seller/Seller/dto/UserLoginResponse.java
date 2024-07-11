package com.seller.Seller.dto;

public class UserLoginResponse extends CommonApiResponse {

	private UserDto user;

	private String jwtToken;

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

}
