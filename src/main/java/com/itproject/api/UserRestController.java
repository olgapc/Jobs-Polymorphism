package com.itproject.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.itproject.application.UserController;

@RestController
public class UserRestController {
	
	@Autowired
	private UserController controller;
	
}
