package com.itproject.application;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.itproject.application.dto.UserDTO;
import com.itproject.domain.User;
import com.itproject.persistence.IUserRepository;
import com.itproject.utilities.InvalidParamException;
import com.itproject.utilities.NotFoundException;

@Controller
public class UserController {
	
	@Autowired
	private IUserRepository repository;
	
	public User save(User user) throws InvalidParamException  {
		if (user == null)
			throw new InvalidParamException();
		try {
			return repository.save(user);
		} catch (Exception e) {
			throw new InvalidParamException();
		}
	}
	
	public UserDTO register(UserDTO userDTO) throws InvalidParamException, NotFoundException {
		User user = new User(userDTO.getUsername(), userDTO.getPassword(), userDTO.getName(), userDTO.getSurnames(), userDTO.getRole());
		user = save(user);
		return new UserDTO(user);
	}
	
	public User getUser(UUID id) throws NotFoundException {
		return repository.findById(id).orElseThrow(() -> new NotFoundException());
	}
	
	public List<UserDTO> listUsers() throws NotFoundException {
		Iterable<User> userList = repository.findAll();
		List<UserDTO> userDTOList = new ArrayList<>();
		
		for (User user : userList) {
			userDTOList.add(new UserDTO(user));
		}
		
		return userDTOList;
	}
	
}
