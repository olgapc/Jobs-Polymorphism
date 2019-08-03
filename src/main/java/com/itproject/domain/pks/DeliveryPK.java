package com.itproject.domain.pks;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.itproject.domain.Exercise;
import com.itproject.domain.Student;
import com.itproject.domain.enums.ExerciseState;

@Embeddable
public class DeliveryPK implements Serializable {
	
	@ManyToOne
	@JoinColumn(name="student")
	private Student student;
	@ManyToOne
	@JoinColumn(name="exercise")
	private Exercise exercise;
	private ExerciseState state;
	private LocalDate date;
	
	protected DeliveryPK() {}
	
	public DeliveryPK(Student student, Exercise exercise, ExerciseState state, LocalDate date) {
		this.student = student;
		this.exercise = exercise;
		this.state = state;
		this.date = date;
	}
	
	public Student getStudent() {
		return student;
	}
	
	public Exercise getExercise() {
		return exercise;
	}
	
	public ExerciseState getState() {
		return state;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((exercise == null) ? 0 : exercise.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
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
		DeliveryPK other = (DeliveryPK) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (exercise == null) {
			if (other.exercise != null)
				return false;
		} else if (!exercise.equals(other.exercise))
			return false;
		if (state != other.state)
			return false;
		if (student == null) {
			if (other.student != null)
				return false;
		} else if (!student.equals(other.student))
			return false;
		return true;
	}
	
}
