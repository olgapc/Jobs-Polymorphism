package com.itproject.domain;

import java.time.LocalDate;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.itproject.domain.pks.StudentAbsencePK;

@Entity(name = "student_absence")
public class StudentAbsence {
	
	@EmbeddedId
	private StudentAbsencePK studentAbsencePK;
	
	protected StudentAbsence() {}
	
	public StudentAbsence(Student student, LocalDate date) {
		studentAbsencePK = new StudentAbsencePK(student, date);
	}

	public Student getStudent() {
		return studentAbsencePK.getStudent();
	}
	
	public LocalDate getDate() {
		return studentAbsencePK.getDate();
	}
	
}
