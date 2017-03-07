package com.gamma.Controller;

import com.gamma.dal.entities.Car;
import com.gamma.dal.entities.GpsPoint;
import com.gamma.dal.entities.Owner;
import com.gamma.dal.entities.Trip;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Created by rick on 3/7/17.
 */
@RestController
@RequestMapping("/api/v1.0/car")
public class CarController {

	//todo authentication authorisation

	@RequestMapping("/")
	String[] index(){
		return new String[]{UUID.randomUUID().toString(), UUID.randomUUID().toString()};
	}

	@RequestMapping("/{caruuid}")
	Car getCar(@PathVariable("caruuid") String caruuid){
		//todo find the car
		return new Car(2016, "AB-12-AD", 1.0, 1.0, caruuid);
	}

	@RequestMapping("/{caruuid}/owners")
	Map<String, Owner> getCarOwners(@PathVariable("caruuid") String caruuid){
		Map<String, Owner> owners = new HashMap<>();
		owners.put("2015-01-01>", new Owner());
		owners.put("2009-01-01>2015-01-01", new Owner());
		return owners;
	}

	@RequestMapping("/{caruuid}/trips")
	List<Trip> getTrips(@PathVariable("caruuid") String caruuid){
		return Arrays.asList(new Trip(), new Trip());
	}

	@RequestMapping("/{caruuid}/trips/{tripuuid}")
	List<GpsPoint> getGpsPoints(@PathVariable("caruuid") String caruuid, @PathVariable("tripuuid") String tripuuid){
		return Arrays.asList(new GpsPoint(), new GpsPoint(), new GpsPoint());
	}

	public static void main(String[] args){
		SpringApplication.run(CarController.class, args);
	}
}
