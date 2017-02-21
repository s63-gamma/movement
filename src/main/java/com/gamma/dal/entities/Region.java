package com.gamma.dal.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by requinard on 2/21/17.
 */
@Entity
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Region() {}

	public int getLongitude() {
		return longitude;
	}

	public void setLongitude(int longitude) {
		this.longitude = longitude;
	}

	public int getLatitude() {
		return latitude;
	}

	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public int getCostMultiplier() {
		return costMultiplier;
	}

	public void setCostMultiplier(int costMultiplier) {
		this.costMultiplier = costMultiplier;
	}

	public Region(String name, int longitude, int latitude, int radius, int costMultiplier) {
		this.name = name;
		this.longitude = longitude;
		this.latitude = latitude;
		this.radius = radius;
		this.costMultiplier = costMultiplier;
	}

	public Region() {
	}


	public Long getId() {
		return id;
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
