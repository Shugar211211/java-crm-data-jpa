package com.crm.service;

import com.crm.auth.models.Principal;
import com.crm.auth.models.UserDetails;

public interface AuthService {
	
	/**
	 * Authentication service
	 * @param userDetails
	 * @return principal
	 * @throws UserNotFoundException if can not authenticate.
	 */
	public Principal authenticate(UserDetails userDetails);

}
