package com.crm.auth.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.crm.auth.models.Principal;
import com.crm.auth.models.UserType;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	@Value("${jwt.secret}")
	private String SECRET_KEY;
	
	public String extractEmail(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
	
	public Date extractRole(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
	
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	public Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}
	
	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	public String generateToken(Principal principal) {
		Map<String, Object>claims = new HashMap<>();
		claims.put("id", (Integer)principal.getId());
		claims.put("role", (UserType)principal.getRole());
		claims.put("name", (String)principal.getName());
		return createToken(claims);
	}
	
	private String createToken(Map<String, Object> claims) {
		return Jwts.builder().setClaims(claims)
//				.setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				// set token restriction for 24 hours at most
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*60*24*7))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY)
				.compact();
	}
	
//	public Boolean validateToken(String token, UserDetails userDetails) {
//		final String email = extractEmail(token);
//		return (email.equals(userDetails.getEmail()) && !isTokenExpired(token));
//	}

	public Boolean validateToken(String token, Principal principal) {
		final Claims claims = extractAllClaims(token);
//		return (email.equals(userDetails.getEmail()) && !isTokenExpired(token));
		if(((Integer)claims.get("id")).intValue() == principal.getId() 
				&& claims.get("role").equals(principal.getRole())
				&& claims.get("name").equals(principal.getName())
				&& !isTokenExpired(token))
			return true;
		else 
			return false;
	}
}
