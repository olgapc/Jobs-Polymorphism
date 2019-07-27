package com.itproject.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itproject.domain.User;

@Repository
public class UserRepository {
	
	@Autowired
	private IUserRepository repository;
	
	public User findById(UUID id) {
		if(repository.findById(id).isPresent()) {
			return repository.findById(id).get();
		} else {
			return (User)Optional.empty().get();
		}
	}
	
	public Iterable<User> findAll() {
		return repository.findAll(); 
	}
	
	public User save(User user) {
		return repository.save(user);
	}
	
}
