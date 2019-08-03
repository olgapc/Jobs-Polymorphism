package com.itproject.persistence;

import java.util.UUID;

import com.itproject.domain.User;

public interface IUserRepository extends IGenericRepository<User, UUID> {
	
}
