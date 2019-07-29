package com.itproject.application;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.itproject.domain.User;
import com.itproject.persistence.UserRepository;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository repository;
	
	public User save(User user) {
		return repository.save(user);
	}
	
	public User findById(UUID id) {
		return repository.findById(id);
	}
	
	public Iterable<User> findAll() {
		return repository.findAll(); 
	}
	
}
