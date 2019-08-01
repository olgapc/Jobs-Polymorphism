package com.itproject.application.dto;

import java.time.LocalDate;

import com.google.gson.annotations.Expose;
import com.itproject.domain.Student;
import com.itproject.domain.StudentAbsence;
import com.itproject.utilities.NotFoundException;

public class StudentAbsenceDTO {
	
	private Student student;
	@Expose
	private String date;
	
	public StudentAbsenceDTO(StudentAbsence studentAbsence) throws NotFoundException {
		
		if (studentAbsence == null) {
			throw new NotFoundException();
		}
		
		this.student = studentAbsence.getStudent();
		this.date = studentAbsence.getDate().toString();
	}
	
	public Student getStudent() {
		return student;
	}
	
	public LocalDate getDate() {
		return LocalDate.parse(date);
	}
	
}
