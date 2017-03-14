package com.gamma.repository;

import com.gamma.dal.entities.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource(collectionResourceRel = "trip", path = "trip")
public interface TripRepository extends JpaRepository<Trip, UUID> {
}
