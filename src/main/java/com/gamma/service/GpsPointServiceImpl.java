package com.gamma.service;

import com.gamma.dal.entities.GpsPoint;
import com.gamma.repository.GpsPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class GpsPointServiceImpl implements GpsPointService {
	@Autowired
	private GpsPointRepository gpsPointRepository;

	@Override
	public List<GpsPoint> findAll() {
		return gpsPointRepository.findAll();
	}

	@Override
	public void save(GpsPoint gpsPoint) {
		gpsPointRepository.save(gpsPoint);
	}

	@Override
	public GpsPoint findOne(UUID id) {
		return gpsPointRepository.findOne(id);
	}

	@Override
	public void delete(UUID id) {
		gpsPointRepository.delete(id);
	}
}
