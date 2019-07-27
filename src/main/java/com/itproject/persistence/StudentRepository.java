package com.itproject.persistence;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itproject.domain.Student;

@Repository
public class StudentRepository {
	
	@Autowired
	private IStudentRepository repository;
	
	public Student findById(UUID id) {
		return repository.findById(id).get();
	}
	
	public Iterable<Student> findAll() {
		return repository.findAll(); 
	}
	
	public Student save(Student student) {
		return repository.save(student);
	}
	
}
