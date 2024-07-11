package com.seller.Seller.dto;

import java.util.ArrayList;
import java.util.List;

public class UserResponseDto extends CommonApiResponse {

	private List<UserDto> users = new ArrayList<>();

	public List<UserDto> getUsers() {
		return users;
	}

	public void setUsers(List<UserDto> users) {
		this.users = users;
	}

}
