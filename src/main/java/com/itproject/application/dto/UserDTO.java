package com.itproject.application.dto;

import java.util.UUID;

import com.google.gson.annotations.Expose;
import com.itproject.domain.User;
import com.itproject.domain.enums.UserRole;

public class UserDTO {
	
	@Expose
    private UUID id;
	private String username;
	private String password;
	@Expose
	private String name;
	@Expose
	private String surnames;
	private UserRole role;
	
	public UserDTO(User user) {
		
		this.id = user.getId();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.name = user.getName();
		this.surnames = user.getSurnames();
		this.role = user.getRole();
		
	}
	
	public UUID getId() {
		return id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSurnames() {
		return surnames;
	}
	
	public UserRole getRole() {
		return role;
	}
	
}
