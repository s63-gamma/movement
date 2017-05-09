package com.gamma.dal.entities;

import org.springframework.data.rest.core.config.Projection;

import java.util.UUID;

/**
 * Created by guushamm on 6-4-17.
 */

@Projection(name = "driver", types = Owner.class)
public interface OwnerProjection {
	UUID getUuid();

	Car_Owner getCarOwner();

	// @Value(value = "#{target.carOwner.stream().findFirst().get().car}")
	// Car getCar();
}
