package com.itproject.application;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.itproject.domain.Student;
import com.itproject.persistence.StudentRepository;

@Controller
public class StudentController {
	
	@Autowired
	private StudentRepository repository;
	
	public Student findById(UUID id) {
		return repository.findById(id);
	}
	
	public Iterable<Student> findAll() {
		return repository.findAll(); 
	}
	
	public Student save(Student user) {
		return repository.save(user);
	}
	
}
