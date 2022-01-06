package com.crm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.entities.User;
import com.crm.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public List<User> findAll() {
		return userRepo.findAll();
	}

	@Override
	public User findById(int id) {
		Optional<User> result= userRepo.findById(id);
		User user = null;
		if(result.isPresent()) {
			user = result.get();
		}
		return user;
	}

	@Override
	public void save(User user) {
		userRepo.save(user);
	}

	@Override
	public void deleteById(int id) {
		userRepo.deleteById(id);
	}
}