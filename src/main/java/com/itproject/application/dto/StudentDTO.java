package com.itproject.application.dto;

import java.time.LocalDate;
import java.util.UUID;

import com.google.gson.annotations.Expose;
import com.itproject.domain.*;
import com.itproject.domain.enums.*;
import com.itproject.utilities.NotFoundException;

public class StudentDTO {
	
	@Expose
	private UUID id;
	private String username;
	private String password;
	@Expose
	private String name;
	@Expose
	private String surnames;
	private UserRole role;
	private String mail;
	private Sex sex;
	private Conclusion conclusion;
	@Expose
	private String startDate;
	@Expose
	private String deadline;
	@Expose
	private int desk;
	@Expose
	private Itinerary itinerary;
	@Expose
	private String interviewResult;
	@Expose
	private User interviewTeacher;
	
	public StudentDTO(Student student) throws NotFoundException {
		
		if (student == null) {
			throw new NotFoundException();
		}
		
		this.id = student.getId();
		this.username = student.getUsername();
		this.password = student.getPassword();
		this.name = student.getName();
		this.surnames = student.getSurnames();
		this.role = student.getRole();
		this.mail = student.getMail();
		this.sex = student.getSex();
		this.conclusion = student.getConclusion();
		this.startDate = student.getStartDate().toString();
		this.deadline = student.getDeadline().toString();
		this.desk = student.getDesk();
		this.itinerary = student.getItinerary();
		this.interviewResult = student.getInterviewResult();
		this.interviewTeacher = student.getInterviewTeacher();
		
	}
	
	public UUID getId() {
		return id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSurnames() {
		return surnames;
	}
	
	public UserRole getRole() {
		return role;
	}
	
	public String getMail() {
		return mail;
	}
	
	public Sex getSex() {
		return sex;
	}
	
	public Conclusion getConclusion() {
		return conclusion;
	}
	
	public LocalDate getStartDate() {
		return LocalDate.parse(startDate);
	}
	
	public LocalDate getDeadline() {
		return LocalDate.parse(deadline);
	}
	
	public int getDesk() {
		return desk;
	}
	
	public Itinerary getItinerary() {
		return itinerary;
	}
	
	public String getInterviewResult() {
		return interviewResult;
	}
	
	public User getInterviewTeacher() {
		return interviewTeacher;
	}
	
}
