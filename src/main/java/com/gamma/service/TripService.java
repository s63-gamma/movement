package com.gamma.service;

import com.gamma.dal.entities.Trip;

import java.util.List;
import java.util.UUID;

public interface TripService {
	List<Trip> findAll();
	void save(Trip trip);
	Trip findOne(UUID id);
	void delete(UUID id);
}
