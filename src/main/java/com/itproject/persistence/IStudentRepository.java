package com.itproject.persistence;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.itproject.domain.Student;

public interface IStudentRepository extends CrudRepository<Student, UUID> {
	
	/* CrudRepository methods:
	 * <S extends Student> S save(S entity);
	 * <S extends Student> Iterable<S> saveAll(Iterable<S> entities);
	 * Optional<Student> findById(byte[] id);
	 * boolean existsById(byte[] id);
	 * Iterable<Student> findAll();
	 * Iterable<Student> findAllById(Iterable<byte[]> ids);
	 * long count();
	 * void deleteById(byte[] id);
	 * void delete(Student entity);
	 * void deleteAll(Iterable<? extends Student> entities);
	 * void deleteAll();
	*/
	
}