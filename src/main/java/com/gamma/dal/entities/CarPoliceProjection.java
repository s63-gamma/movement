package com.gamma.dal.entities;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.util.UUID;

/**
 * Created by guushamm on 6-4-17.
 */

@Projection(name = "police", types = Car.class)
public interface CarPoliceProjection {
	UUID getUuid();

	String getLicensePlate();

	int getBuildingYear();

	int getWeight();

	@Value(value = "#{target.trackerCar.stream().findFirst().get().tracker}")
	Tracker getTracker();


	/*@Value(value = "#{target.carOwner.stream().findFirst().get().owner}")
	Owner getOwner();*/

	@Value(value = "#{target.trackerCar.stream().findFirst().get().tracker.gpsPoints}")
	GpsPoint[] getGpsPoints();
}
