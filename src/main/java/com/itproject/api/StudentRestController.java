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

import com.itproject.application.StudentController;
import com.itproject.application.dto.StudentDTO;

@CrossOrigin
@RestController
@RequestMapping("/students")
public class StudentRestController {
	
	@Autowired
	private StudentController controller;
	
    // Saves a new student in Database
	// Call from the web browser http://localhost:8080/students/new
	@PostMapping(value = "/new", produces = "application/json;charset=UTF-8")
	public String register(@RequestBody String jStudent) {
		StudentDTO newStudent = new Gson().fromJson(jStudent, StudentDTO.class);
		StudentDTO student = controller.register(newStudent);
		return toJson(student);
	}
	
    // Returns a list of the students in Database
	// Call from the web browser http://localhost:8080/students/list
	@GetMapping(value = "/list", produces = "application/json;charset=UTF-8")
	public String listStudents() {
		List<StudentDTO> students = controller.listStudents();
		return toJson(students);
	}
	
	// Object to Json converter
	private String toJson(Object object){
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		return gson.toJson(object);
	}
    
}