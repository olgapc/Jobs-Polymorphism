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
	
	public UUID getId() {
		return id;
	}
	
	public void setId(UUID id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isCommon() {
		return isCommon;
	}
	
	public void setCommon(boolean isCommon) {
		this.isCommon = isCommon;
	}
	
	public int getOrder() {
		return order;
	}
	
	public void setOrder(int order) {
		this.order = order;
	}
	
	public Itinerary getItinerary() {
		return itinerary;
	}
	
	public void setItinerary(Itinerary itinerary) {
		this.itinerary = itinerary;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (isCommon ? 1231 : 1237);
		result = prime * result + ((itinerary == null) ? 0 : itinerary.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + order;
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
		Exercise other = (Exercise) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isCommon != other.isCommon)
			return false;
		if (itinerary == null) {
			if (other.itinerary != null)
				return false;
		} else if (!itinerary.equals(other.itinerary))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (order != other.order)
			return false;
		return true;
	}
	
}
