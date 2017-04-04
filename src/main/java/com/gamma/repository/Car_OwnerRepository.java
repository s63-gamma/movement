package com.gamma.repository;

import com.gamma.dal.entities.Car_Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource(collectionResourceRel = "carOwner", path = "carOwner")
public interface Car_OwnerRepository extends JpaRepository<Car_Owner, UUID> {

}
