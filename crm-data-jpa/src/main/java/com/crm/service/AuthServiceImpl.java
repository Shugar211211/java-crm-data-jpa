package com.crm.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.crm.auth.models.Principal;
import com.crm.auth.models.UserDetails;
import com.crm.auth.models.UserType;
import com.crm.entities.User;
import com.crm.repo.UserRepository;

@Component
public class AuthServiceImpl implements AuthService {

	private String admin_email;
	private String admin_password;
	private UserRepository userRepository;
	
	@Autowired
	public AuthServiceImpl(@Value("${admin.email}")String admin_email, 
						   @Value("${admin.password}")String admin_password, 
						   UserRepository userRepository) {
		this.admin_email = admin_email;
		this.admin_password = admin_password;
		this.userRepository = userRepository;
	}
	
	@Override
	public Principal authenticate(UserDetails userDetails) {
		
		UserType role = userDetails.getUserType();
		
		switch (role) {
		case ADMINISTRATOR:
			return this.authAdmin(userDetails);
		case POWER_USER:
			return this.authPowerUser(userDetails);
		default: throw new IllegalArgumentException("Unexpected role: " + role);
		}
	}

	private Principal authAdmin(UserDetails userDetails) {
		if(userDetails.getEmail().equals(this.admin_email) 
				&& userDetails.getPassword().equals(this.admin_password)) {
			return new Principal(0, "Administrator", UserType.ADMINISTRATOR);
		}
		else { throw new RuntimeException("Wrong email or password"); }
	}

	private Principal authPowerUser(UserDetails userDetails) {
		Optional<User> optionalUser = 
				userRepository.findByEmailAndPassword(userDetails.getEmail(), userDetails.getPassword());
		if(optionalUser.isPresent())
		{
			User thisUser = optionalUser.get();
			return new Principal(thisUser.getId(), 
								 thisUser.getFirstName().concat(" ").concat(thisUser.getLastName()), 
								 UserType.POWER_USER);
		}
		else { throw new RuntimeException("Wrong email or password"); }
	}
}
