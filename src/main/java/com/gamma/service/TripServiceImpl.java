package com.gamma.service;

import com.gamma.dal.entities.Trip;
import com.gamma.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class TripServiceImpl implements TripService {
	@Autowired
	private TripRepository tripRepository;

	@Override
	public List<Trip> findAll() {
		return tripRepository.findAll();
	}

	@Override
	public void save(Trip trip) {
		tripRepository.save(trip);
	}

	@Override
	public Trip findOne(UUID id) {
		return tripRepository.findOne(id);
	}

	@Override
	public void delete(UUID id) {
		tripRepository.delete(id);
	}
}
