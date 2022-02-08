package com.crm.auth.models;

public enum UserType 
{
	ADMINISTRATOR ("administrator"),
	POWER_USER("power user");
	
	/**
	 * Constructor.
	 * @param userType
	 */
	private UserType(String userType) 
	{
		this.userType = userType;
	}

	private String userType;
	
	/**
	 * userType getter
	 * @return userType
	 */
	public String getUserType()
	{
		return userType;
	}
}
