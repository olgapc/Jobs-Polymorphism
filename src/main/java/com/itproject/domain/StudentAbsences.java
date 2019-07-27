package com.itproject.domain;

import java.time.LocalDate;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.itproject.domain.pks.StudentAbsencesPK;

@Entity(name = "student_absences")
public class StudentAbsences {
	
	@EmbeddedId
	private StudentAbsencesPK studentAbsencesPK;
	
	protected StudentAbsences() {}
	
	public StudentAbsences(Student student, LocalDate date) {
		studentAbsencesPK = new StudentAbsencesPK(student, date);
	}
	
}
