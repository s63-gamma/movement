package com.gamma.service;

import com.gamma.dal.entities.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CarServiceImpl implements CarService {
	@Autowired
	private CarRepository carRepository;

	@Override
	public List<Car> findAll() {
		return carRepository.findAll();
	}

	@Override
	public void save(Car car) {
		carRepository.save(car);
	}

	@Override
	public Car findOne(UUID id) {
		return carRepository.findOne(id);
	}

	@Override
	public void delete(UUID id) {
		carRepository.delete(id);
	}
}
