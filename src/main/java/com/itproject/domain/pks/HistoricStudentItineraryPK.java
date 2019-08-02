package com.itproject.domain.pks;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.itproject.domain.Itinerary;
import com.itproject.domain.Student;

@Embeddable
public class HistoricStudentItineraryPK implements Serializable {
	
	@ManyToOne
	@JoinColumn(name="student")
	private Student student;
	@ManyToOne
	@JoinColumn(name="itinerary")
	private Itinerary itinerary;
	
	protected HistoricStudentItineraryPK() {}
	
	public HistoricStudentItineraryPK(Student student, Itinerary itinerary) {
		this.student = student;
		this.itinerary = itinerary;
	}
	
	public Student getStudent() {
		return student;
	}
	
	public Itinerary getItinerary() {
		return itinerary;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itinerary == null) ? 0 : itinerary.hashCode());
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
		HistoricStudentItineraryPK other = (HistoricStudentItineraryPK) obj;
		if (itinerary == null) {
			if (other.itinerary != null)
				return false;
		} else if (!itinerary.equals(other.itinerary))
			return false;
		if (student == null) {
			if (other.student != null)
				return false;
		} else if (!student.equals(other.student))
			return false;
		return true;
	}
	
}
