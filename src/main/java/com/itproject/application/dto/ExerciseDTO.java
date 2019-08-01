package com.itproject.application.dto;

import java.util.UUID;

import com.google.gson.annotations.Expose;
import com.itproject.domain.Exercise;
import com.itproject.domain.Itinerary;
import com.itproject.utilities.NotFoundException;

public class ExerciseDTO {
	
	@Expose
	private UUID id;
	@Expose
	private String name;
	@Expose
	private boolean isCommon;
	@Expose
	private int order;
	@Expose
	private Itinerary itinerary;
	
	public ExerciseDTO(Exercise exercise) throws NotFoundException {
		
		if (exercise == null) {
			throw new NotFoundException();
		}
		
		this.id = exercise.getId();
		this.name = exercise.getName();
		this.isCommon = exercise.isCommon();
		this.order = exercise.getOrder();
		this.itinerary = exercise.getItinerary();
	}
	
	public UUID getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isCommon() {
		return isCommon;
	}
	
	public int getOrder() {
		return order;
	}
	
	public Itinerary getItinerary() {
		return itinerary;
	}
	
}
