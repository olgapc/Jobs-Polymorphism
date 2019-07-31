package com.itproject.application;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.itproject.domain.Student;
import com.itproject.application.dto.StudentDTO;
import com.itproject.persistence.IUserGenericRepository;

@Controller
public class StudentController {
	
	@Autowired
	private IUserGenericRepository<Student> repository;
	
	public StudentDTO register(StudentDTO studentDTO) {
		Student student = new Student(studentDTO.getUsername(), studentDTO.getPassword(), studentDTO.getName(), studentDTO.getSurnames(), 
				studentDTO.getMail(), studentDTO.getSex(), studentDTO.getConclusion(), studentDTO.getStartDate(), studentDTO.getDeadline());
		student = repository.save(student);
		return new StudentDTO(student);
	}
	
	public Student save(Student student) {
		return repository.save(student);
	}
	
	public Student findById(UUID id) {
		return repository.findById(id).get();
	}
	
	public List<StudentDTO> listStudents() {
		Iterable<Student> studentList = repository.findAll();
		List<StudentDTO> studentDTOList = new ArrayList<>();
		
		for (Student student : studentList) {
			studentDTOList.add(new StudentDTO(student));
		}
		
		return studentDTOList;
	}
	
}
