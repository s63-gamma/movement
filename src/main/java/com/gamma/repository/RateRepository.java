package com.gamma.repository;

import com.gamma.dal.entities.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource(collectionResourceRel = "rate", path = "rate")
public interface RateRepository extends JpaRepository<Rate, UUID> {
}
