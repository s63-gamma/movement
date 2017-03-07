package com.gamma.service;

import com.gamma.dal.entities.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

public interface CarService {
	List<Car> findAll();
	void save(Car account);
	Car findOne(UUID id);
	void delete(UUID id);
}
