package com.itproject.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "exercise")
public class Exercise {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private UUID id;
	private String name;
	@Column(name = "is_common")
	private boolean isCommon;
	private int order;
	@ManyToOne
	@JoinColumn(name="itinerary")
	private Itinerary itinerary;
	
	protected Exercise() {}
	
	public Exercise(String name, boolean isCommon, int order, Itinerary itinerary) {
		this.name = name;
		this.isCommon = isCommon;
		this.order = order;
		this.itinerary = itinerary;
	}
	
}
