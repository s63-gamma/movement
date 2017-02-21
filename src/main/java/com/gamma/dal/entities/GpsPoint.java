package com.gamma.dal.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Model for GPS Points on a map
 */
@Entity
public class GpsPoint {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(unique = true)
	private String uuid;

	private double longitude;
	private double latitude;
	private int sequenceNumber;

	public GpsPoint() {
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	@ManyToOne
	@JoinColumn(name = "region_id")
	private Region region;

	public GpsPoint(double longitude, double latitude, int sequenceNumber) {
		this.longitude = longitude;
		this.latitude = latitude;
		this.sequenceNumber = sequenceNumber;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public int getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public Long getId() {

		return id;
	}

	public String getUuid() {
		return uuid;
	}
}
