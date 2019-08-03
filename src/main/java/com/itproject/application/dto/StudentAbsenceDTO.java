package com.itproject.application.dto;

import java.time.LocalDate;
import java.util.UUID;

import com.google.gson.annotations.Expose;
import com.itproject.domain.StudentAbsence;
import com.itproject.utilities.NotFoundException;

public class StudentAbsenceDTO {
	
	@Expose
	private UUID student;
	@Expose
	private String date;
	
	public StudentAbsenceDTO(StudentAbsence studentAbsence) throws NotFoundException {
		
		if (studentAbsence == null) {
			throw new NotFoundException();
		}
		
		this.student = studentAbsence.getStudent().getId();
		this.date = studentAbsence.getDate().toString();
	}
	
	public UUID getStudent() {
		return student;
	}
	
	public LocalDate getDate() {
		return LocalDate.parse(date);
	}
	
}
