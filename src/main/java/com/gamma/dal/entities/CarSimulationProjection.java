package com.gamma.dal.entities;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.util.UUID;

/**
 * Created by guushamm on 6-4-17.
 */

@Projection(name = "simulation", types = Car.class)
public interface CarSimulationProjection {
	UUID getUuid();

	String getLicensePlate();
	@Value(value = "#{target.trackerCar.stream().findFirst().get().tracker}")
	Tracker getTracker();
}
