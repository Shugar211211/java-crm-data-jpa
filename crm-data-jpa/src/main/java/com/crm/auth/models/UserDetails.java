package com.crm.auth.models;

import org.springframework.stereotype.Component;

@Component
public class UserDetails {
	private String email;
	private String password;
	private UserType userType;
	
	public UserDetails() {
	}
	public UserDetails(String email, String password, UserType userType) {
		this.email = email;
		this.password = password;
		this.userType = userType;
	}
	public UserDetails(String email, String password, UserType userType, int id) {
		this.email = email;
		this.password = password;
		this.userType = userType;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
}
