package com.gamma.service;

import com.gamma.dal.entities.GpsPoint;

import java.util.List;
import java.util.UUID;

public interface GpsPointService {
	List<GpsPoint> findAll();
	void save(GpsPoint gpsPoint);
	GpsPoint findOne(UUID id);
	void delete(UUID id);
}
