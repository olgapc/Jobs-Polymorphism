package com.itproject.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.itproject.application.StudentController;
import com.itproject.application.dto.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/global")
public class GlobalRestController {
	
	@Autowired
	private StudentController controller;
	
    // Saves a new student in Database
	// Call from the web browser http://localhost:8080/students/new
	@PostMapping(value = "/import", produces = "application/json;charset=UTF-8")
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