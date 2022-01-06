package com.crm.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.entities.User;
import com.crm.service.UserService;

@RestController
@RequestMapping("/api")
public class UserRestController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public List<User> findAll() {
		return userService.findAll();
	}
	
	@GetMapping("/users/{id}")
	public User getUser(@PathVariable int id) {
		User user = userService.findById(id);
		if(user == null) {
			throw new RuntimeException("User not found: " + id);
		}
		return user;
	}
	
	@PostMapping("/users")
	public User addUser(@RequestBody User user) {
		user.setId(0);
		userService.save(user);
		return user;
	}
	
	@PutMapping("/users")
	public User updateUser(@RequestBody User user) {
		userService.save(user);
		return user;
	}
	
	@DeleteMapping("/users/{id}")
	public String deleteUser(@PathVariable int id) {
		User tempUser = userService.findById(id);
		if(tempUser == null) {
			throw new RuntimeException("User id not found: " + id);
		}
		userService.deleteById(id);
		return "Deleted user id: " + id;
	}
}
