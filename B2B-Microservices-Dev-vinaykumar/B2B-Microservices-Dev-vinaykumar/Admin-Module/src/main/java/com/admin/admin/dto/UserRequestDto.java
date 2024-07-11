package com.admin.admin.dto;

import org.springframework.beans.BeanUtils;

import com.admin.admin.model.User;

public class UserRequestDto {
	
		private String firstName;

		private String lastName;

		private String emailId;

		private String password;

		private String phoneNo;

		private String role;

		private String street;

		private String city;

		private int pincode;

		private int sellerId; 

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getEmailId() {
			return emailId;
		}

		public void setEmailId(String emailId) {
			this.emailId = emailId;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getPhoneNo() {
			return phoneNo;
		}

		public void setPhoneNo(String phoneNo) {
			this.phoneNo = phoneNo;
		}

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}

		public String getStreet() {
			return street;
		}

		public void setStreet(String street) {
			this.street = street;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public int getPincode() {
			return pincode;
		}

		public void setPincode(int pincode) {
			this.pincode = pincode;
		}

		public int getSellerId() {
			return sellerId;
		}

		public void setSellerId(int sellerId) {
			this.sellerId = sellerId;
		}

		public static User toUserEntity(UserRequestDto userRequestDto) {
			User user = new User();
			BeanUtils.copyProperties(userRequestDto, user);
			return user;
		}

	}

