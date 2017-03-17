package com.gamma.repository;

import com.gamma.dal.entities.Tracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource(collectionResourceRel = "tracker", path = "tracker")
public interface TrackerRepository extends JpaRepository<Tracker, UUID> {
}
