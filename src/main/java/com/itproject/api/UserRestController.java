package com.itproject.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.itproject.application.UserController;
import com.itproject.application.dto.UserDTO;
import com.itproject.utilities.InvalidParamException;
import com.itproject.utilities.NotFoundException;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserRestController {
	
	@Autowired
	private UserController controller;
	
	// Object to Json converter
	private String toJson(Object object){
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		return gson.toJson(object);
	}
	
	// Saves a new user in Database
	// Call from a Rest client http://localhost:8080/users/new
	@PostMapping(value = "/new", produces = "application/json;charset=UTF-8")
	public String register(@RequestBody String jUser) throws InvalidParamException, NotFoundException {
		UserDTO newUser = new Gson().fromJson(jUser, UserDTO.class);
		UserDTO user = controller.register(newUser);
		return toJson(user);
	}
	
	// Returns a list of the users in Database
	// Call from the web browser http://localhost:8080/users/list
	@GetMapping(value = "/list", produces = "application/json;charset=UTF-8")
	public String listUsers() throws NotFoundException {
		List<UserDTO> users = controller.listUsers();
		return toJson(users);
	}
	
}
