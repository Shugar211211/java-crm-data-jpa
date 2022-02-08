package com.crm.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.crm.auth.models.AuthenticationRequest;
import com.crm.auth.models.Principal;
import com.crm.auth.models.UserDetails;
import com.crm.auth.utils.JwtUtil;
import com.crm.service.AuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class AuthRestController {
	
	@Autowired
	private UserDetails userDetails;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private Principal principal;
	
	// endpoint for POST "/authenticate" - user authentication
	@RequestMapping(value="/authenticate", method=RequestMethod.POST)
	public ResponseEntity<?> createAuthJwtToken(@RequestBody AuthenticationRequest authenticationRequest) throws JsonProcessingException {
		
		// create userDetails object
		userDetails.setEmail(authenticationRequest.getEmail());
		userDetails.setPassword(authenticationRequest.getPassword());
		userDetails.setUserType(authenticationRequest.getRole());
		
		// perform authentication
		principal = authService.authenticate(userDetails);
		
		// if authorization is successful, then create token
		final String jwtToken = jwtUtil.generateToken(principal);
		Map<String, Object> params = new HashMap<>();
		params.put("token", jwtToken);
		String payload = new ObjectMapper().writeValueAsString(params);
		return ResponseEntity.ok(payload);
	}
}
