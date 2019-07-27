package com.itproject.domain;

import java.time.LocalDate;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.itproject.domain.enums.ExerciseState;
import com.itproject.domain.pks.DeliveryPK;

@Entity(name = "historic_delivery")
public class HistoricDelivery {
	
	@EmbeddedId
	private DeliveryPK historicDeliveryPK;
	
	protected HistoricDelivery() {}
	
	public HistoricDelivery(Student student, Exercise exercise, ExerciseState state, LocalDate date) {
		historicDeliveryPK = new DeliveryPK(student, exercise, state, date);
	}
	
}
