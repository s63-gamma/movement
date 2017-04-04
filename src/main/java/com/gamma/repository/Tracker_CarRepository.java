package com.gamma.repository;

import com.gamma.dal.entities.Tracker_Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource(collectionResourceRel = "trackerCar", path = "trackerCar")
public interface Tracker_CarRepository extends JpaRepository<Tracker_Car, UUID> {

}
