package com.itproject.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import com.itproject.domain.enums.*;

@Entity(name = "student")
@PrimaryKeyJoinColumn(name = "id")
public class Student extends User {
	
	private String mail;
	@Enumerated(EnumType.STRING)
	private Sex sex;
	@Enumerated(EnumType.STRING)
	private Conclusion conclusion;
	@Column(name = "start_date")
	private LocalDate startDate;
	private LocalDate deadline;
	private int desk;
	@ManyToOne
	@JoinColumn(name="itinerary")
	private Itinerary itinerary;
	@Column(name = "interview_result")
	private String interviewResult;
	@ManyToOne
	@JoinColumn(name="interview_teacher")
	private User interviewTeacher;
	
	@OneToMany(mappedBy="studentAbsencePK.student")
	private List<StudentAbsence> absences;
	@OneToMany(mappedBy="currentDeliveryPK.student")
	private List<CurrentDelivery> currentDeliveries;
	@OneToMany(mappedBy="historicDeliveryPK.student")
	private List<HistoricDelivery> historicDeliveries;
	@OneToMany(mappedBy="historicStudentItineraryPK.student")
	private List<HistoricStudentItinerary> historicItineraries;
	
	protected Student() {}
	
	public Student(String username, String password, String name, String surnames,
			String mail, Sex sex, Conclusion conclusion, LocalDate startDate, LocalDate deadline) {
		super(username, password, name, surnames, UserRole.STUDENT);
		this.mail = mail;
		this.sex = sex;
		this.conclusion = conclusion;
		this.startDate = startDate;
		this.deadline = deadline;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public Conclusion getConclusion() {
		return conclusion;
	}

	public void setConclusion(Conclusion conclusion) {
		this.conclusion = conclusion;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getDeadline() {
		return deadline;
	}

	public void setDeadline(LocalDate deadline) {
		this.deadline = deadline;
	}

	public int getDesk() {
		return desk;
	}

	public void setDesk(int desk) {
		this.desk = desk;
	}

	public Itinerary getItinerary() {
		return itinerary;
	}

	public void setItinerary(Itinerary itinerary) {
		this.itinerary = itinerary;
	}

	public String getInterviewResult() {
		return interviewResult;
	}

	public void setInterviewResult(String interviewResult) {
		this.interviewResult = interviewResult;
	}

	public User getInterviewTeacher() {
		return interviewTeacher;
	}

	public void setInterviewTeacher(User interviewTeacher) {
		this.interviewTeacher = interviewTeacher;
	}
	
	public List<StudentAbsence> getAbsences() {
		return absences;
	}

	public void setAbsences(List<StudentAbsence> absences) {
		this.absences = absences;
	}

	public List<CurrentDelivery> getCurrentDeliveries() {
		return currentDeliveries;
	}

	public void setCurrentDeliveries(List<CurrentDelivery> currentDeliveries) {
		this.currentDeliveries = currentDeliveries;
	}

	public List<HistoricDelivery> getHistoricDeliveries() {
		return historicDeliveries;
	}

	public void setHistoricDeliveries(List<HistoricDelivery> historicDeliveries) {
		this.historicDeliveries = historicDeliveries;
	}

	public List<HistoricStudentItinerary> getHistoricItineraries() {
		return historicItineraries;
	}

	public void setHistoricItineraries(List<HistoricStudentItinerary> historicItineraries) {
		this.historicItineraries = historicItineraries;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((conclusion == null) ? 0 : conclusion.hashCode());
		result = prime * result + ((currentDeliveries == null) ? 0 : currentDeliveries.hashCode());
		result = prime * result + ((deadline == null) ? 0 : deadline.hashCode());
		result = prime * result + desk;
		result = prime * result + ((historicDeliveries == null) ? 0 : historicDeliveries.hashCode());
		result = prime * result + ((historicItineraries == null) ? 0 : historicItineraries.hashCode());
		result = prime * result + ((interviewResult == null) ? 0 : interviewResult.hashCode());
		result = prime * result + ((interviewTeacher == null) ? 0 : interviewTeacher.hashCode());
		result = prime * result + ((itinerary == null) ? 0 : itinerary.hashCode());
		result = prime * result + ((mail == null) ? 0 : mail.hashCode());
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (conclusion != other.conclusion)
			return false;
		if (currentDeliveries == null) {
			if (other.currentDeliveries != null)
				return false;
		} else if (!currentDeliveries.equals(other.currentDeliveries))
			return false;
		if (deadline == null) {
			if (other.deadline != null)
				return false;
		} else if (!deadline.equals(other.deadline))
			return false;
		if (desk != other.desk)
			return false;
		if (historicDeliveries == null) {
			if (other.historicDeliveries != null)
				return false;
		} else if (!historicDeliveries.equals(other.historicDeliveries))
			return false;
		if (historicItineraries == null) {
			if (other.historicItineraries != null)
				return false;
		} else if (!historicItineraries.equals(other.historicItineraries))
			return false;
		if (interviewResult == null) {
			if (other.interviewResult != null)
				return false;
		} else if (!interviewResult.equals(other.interviewResult))
			return false;
		if (interviewTeacher == null) {
			if (other.interviewTeacher != null)
				return false;
		} else if (!interviewTeacher.equals(other.interviewTeacher))
			return false;
		if (itinerary == null) {
			if (other.itinerary != null)
				return false;
		} else if (!itinerary.equals(other.itinerary))
			return false;
		if (mail == null) {
			if (other.mail != null)
				return false;
		} else if (!mail.equals(other.mail))
			return false;
		if (sex != other.sex)
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}
	
}
