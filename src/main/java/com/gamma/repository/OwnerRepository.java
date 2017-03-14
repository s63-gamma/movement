package com.gamma.repository;

import com.gamma.dal.entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource(collectionResourceRel = "owner", path = "owner")
public interface OwnerRepository extends JpaRepository<Owner, UUID> {
}
