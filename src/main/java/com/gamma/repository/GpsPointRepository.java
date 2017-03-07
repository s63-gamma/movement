package com.gamma.repository;

import com.gamma.dal.entities.GpsPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource(collectionResourceRel = "gpspoint", path = "gpspoint")
public interface GpsPointRepository extends JpaRepository<GpsPoint, UUID> {

}
