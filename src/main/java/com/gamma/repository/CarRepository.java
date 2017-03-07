package com.gamma.repository;

import com.gamma.dal.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource(collectionResourceRel = "car", path = "car")
public interface CarRepository extends JpaRepository<Car, UUID> {

}
