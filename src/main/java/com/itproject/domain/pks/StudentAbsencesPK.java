package com.itproject.domain.pks;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.itproject.domain.Student;

@Embeddable
public class StudentAbsencesPK implements Serializable {
	
	@ManyToOne
	@JoinColumn(name="student")
	private Student student;
	private LocalDate date;
	
	protected StudentAbsencesPK() {}
	
	public StudentAbsencesPK(Student student, LocalDate date) {
		this.student = student;
		this.date = date;
	}
	
	public Student getStudent() {
		return student;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((student == null) ? 0 : student.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentAbsencesPK other = (StudentAbsencesPK) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (student == null) {
			if (other.student != null)
				return false;
		} else if (!student.equals(other.student))
			return false;
		return true;
	}
	
}
