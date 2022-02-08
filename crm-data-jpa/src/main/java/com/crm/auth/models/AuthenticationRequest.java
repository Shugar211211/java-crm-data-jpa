package com.crm.auth.models;

import org.springframework.stereotype.Component;

@Component
public class AuthenticationRequest {
	private String email;
	private String password;
	private UserType role;
	
	public AuthenticationRequest() {
	}

	public AuthenticationRequest(String email, String password, UserType role) {
		super();
		this.email = email;
		this.password = password;
		this.role = role;
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

	public UserType getRole() {
		return role;
	}

	public void setRole(UserType role) {
		this.role = role;
	}
}