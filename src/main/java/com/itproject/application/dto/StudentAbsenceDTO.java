package com.itproject.application.dto;

import java.time.LocalDate;

import com.itproject.domain.Student;
import com.itproject.domain.StudentAbsence;

public class StudentAbsenceDTO {
	
	private Student student;
	private String date;
	
	public StudentAbsenceDTO(StudentAbsence studentAbsence) {
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
