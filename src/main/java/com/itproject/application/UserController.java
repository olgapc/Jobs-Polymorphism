package com.itproject.application;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.itproject.domain.User;
import com.itproject.persistence.IUserGenericRepository;

@Controller
public class UserController {
	
	@Autowired
	private IUserGenericRepository<User> repository;
	
	public User save(User user) {
		return repository.save(user);
	}
	
	public User findById(UUID id) {
		return repository.findById(id).get();
	}
	
	public List<User> findAll() {
		return (List<User>) repository.findAll(); 
	}
	
}
