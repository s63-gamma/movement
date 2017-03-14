package com.gamma.repository;

import com.gamma.dal.entities.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

/**
 * Created by rick on 3/14/17.
 */
@RepositoryRestResource(collectionResourceRel = "region", path = "region")
public interface RegionRepository extends JpaRepository<Region, UUID>{
}
