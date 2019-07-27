package com.itproject.persistence;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.itproject.domain.User;

public interface IUserRepository extends CrudRepository<User, UUID> {
	
	/* CrudRepository methods:
	 * <S extends User> S save(S entity);
	 * <S extends User> Iterable<S> saveAll(Iterable<S> entities);
	 * Optional<User> findById(byte[] id);
	 * boolean existsById(byte[] id);
	 * Iterable<User> findAll();
	 * Iterable<User> findAllById(Iterable<byte[]> ids);
	 * long count();
	 * void deleteById(byte[] id);
	 * void delete(User entity);
	 * void deleteAll(Iterable<? extends User> entities);
	 * void deleteAll();
	*/
}


