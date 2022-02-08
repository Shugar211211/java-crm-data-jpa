package com.crm.auth.models;

import org.springframework.stereotype.Component;

@Component
public class Principal {
	
	private int id;
	private String name;
	private UserType role;
	
	public Principal() {
	}
	public Principal(int id, String name, UserType role) {
		this.id = id;
		this.name = name;
		this.role = role;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public UserType getRole() {
		return role;
	}
	public void setRole(UserType role) {
		this.role = role;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
