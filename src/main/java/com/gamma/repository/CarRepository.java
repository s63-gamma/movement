package com.gamma.repository;

import com.gamma.dal.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.UUID;

@RepositoryRestResource(collectionResourceRel = "car", path = "car")
public interface CarRepository extends JpaRepository<Car, UUID> {
	List<Car> findByBuildingYear(@Param("buildingYear") int buildingYear);
	List<Car> findByLicensePlate(@Param("licensePlate") String licensePlate);
	List<Car> findByIsStolen(@Param("stolen") Boolean isStolen);
}
