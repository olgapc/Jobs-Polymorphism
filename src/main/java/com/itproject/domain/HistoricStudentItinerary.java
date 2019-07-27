package com.itproject.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.itproject.domain.pks.HistoricStudentItineraryPK;

@Entity(name = "historic_student_itinerary")
public class HistoricStudentItinerary {
	
	@EmbeddedId
	private HistoricStudentItineraryPK historicStudentItineraryPK;
	@Column(name = "end_date")
	private LocalDate endDate;
	
	protected HistoricStudentItinerary() {}
	
	public HistoricStudentItinerary(Student student, Itinerary itinerary, LocalDate endDate) {
		this.endDate = endDate;
		historicStudentItineraryPK = new HistoricStudentItineraryPK(student, itinerary);
	}
	
}
