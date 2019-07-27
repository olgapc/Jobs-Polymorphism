package com.itproject.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.itproject.domain.enums.ItineraryName;

@Entity(name = "itinerary")
public class Itinerary {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private UUID id;
	@Enumerated(EnumType.STRING)
	private ItineraryName name;
	@ManyToOne
	@JoinColumn(name="teacher")
	private User teacher;
	
	protected Itinerary() {}
	
	public Itinerary(ItineraryName name, User teacher) {
		this.name = name;
		this.teacher = teacher;
	}
	
}
