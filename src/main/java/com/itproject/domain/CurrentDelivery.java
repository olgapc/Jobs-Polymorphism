package com.itproject.domain;

import java.time.LocalDate;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.itproject.domain.enums.ExerciseState;
import com.itproject.domain.pks.DeliveryPK;

@Entity(name = "current_delivery")
public class CurrentDelivery {
	
	@EmbeddedId
	private DeliveryPK currentDeliveryPK;
	
	protected CurrentDelivery() {}
	
	public CurrentDelivery(Student student, Exercise exercise, ExerciseState state, LocalDate date) {
		currentDeliveryPK = new DeliveryPK(student, exercise, state, date);
	}
	
	public Student getStudent() {
		return currentDeliveryPK.getStudent();
	}
	
	public Exercise getExercise() {
		return currentDeliveryPK.getExercise();
	}
	
	public ExerciseState getState() {
		return currentDeliveryPK.getState();
	}
	
	public LocalDate getDate() {
		return currentDeliveryPK.getDate();
	}
	
}
