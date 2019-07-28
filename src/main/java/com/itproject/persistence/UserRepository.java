package com.itproject.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itproject.domain.User;

@Repository
public class UserRepository {
	
	@Autowired
	private IUserRepository repository;
	
	public User save(User user) {
		return repository.save(user);
	}
	
	public User findById(UUID id) {
		if(repository.findById(id).isPresent()) {
			return repository.findById(id).get();
		} else {
			return (User)Optional.empty().get();
		}
	}
	
	public List<User> findAll() {
		return (List<User>) repository.findAll(); 
	}
	
}
