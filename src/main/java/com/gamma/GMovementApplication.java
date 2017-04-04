package com.gamma;

import com.gamma.dal.entities.*;
import com.gamma.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

@SpringBootApplication
public class GMovementApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(GMovementApplication.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(GMovementApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(InvoiceRepository invoiceRepository, CarRepository carRepository, OwnerRepository ownerRepository, RateRepository rateRepository, RegionRepository regionRepository, TrackerRepository trackerRepository, GpsPointRepository gpsPointRepository, TripRepository tripRepository, Car_OwnerRepository car_ownerRepository, Tracker_CarRepository tracker_carRepository) {
		return (args) -> {

			ArrayList<Owner> owners = new ArrayList<>();
			Owner owner1 = new Owner("username1", "emailadres1", "name1", "surname1", "0123456789", "residence1", new ArrayList<>());
			Owner owner2 = new Owner("username2", "emailadres2", "name2", "surname2", "0223456789", "residence2", new ArrayList<>());
			Owner owner3 = new Owner("username3", "emailadres3", "name3", "surname3", "0323456789", "residence3", new ArrayList<>());
			owners.add(owner1);
			owners.add(owner2);
			owners.add(owner3);
			owners.forEach(ownerRepository::save);

			ArrayList<Rate> rates = new ArrayList<>();
			Rate rate1 = new Rate("Passenger", 10, "carTax");
			Rate rate2 = new Rate("Truck", 50, "carTax");
			Rate rate3 = new Rate("InnerCity", 2, "regionTax");
			Rate rate4 = new Rate("OuterCity", 1, "regionTax");
			rates.add(rate1);
			rates.add(rate2);
			rates.add(rate3);
			rates.add(rate4);
			rates.forEach(rateRepository::save);

			ArrayList<Region> regions = new ArrayList<>();
			Region region1 = new Region("InnerCity", 51.55533, 1.12312, 1012.12, 1);
			Region region2 = new Region("OuterCity", 52.55533, 2.12312, 2012.12, 2);
			regions.add(region1);
			regions.add(region2);
			regions.forEach(regionRepository::save);

			ArrayList<Tracker> trackers = new ArrayList<>();
			Tracker tracker1 = new Tracker(1, 1, "GPS", new ArrayList<>());
			Tracker tracker2 = new Tracker(2, 2, "GPS", new ArrayList<>());
			Tracker tracker3 = new Tracker(3, 3, "Camera", new ArrayList<>());
			trackers.add(tracker1);
			trackers.add(tracker2);
			trackers.add(tracker3);
			trackers.forEach(trackerRepository::save);

			ArrayList<GpsPoint> gpsPoints = new ArrayList<>();
			GpsPoint gpsPoint1 = new GpsPoint(51.12312, 1.12312, 1, region1, tracker1);
			GpsPoint gpsPoint2 = new GpsPoint(52.12312, 2.12312, 2, region1, tracker1);
			GpsPoint gpsPoint3 = new GpsPoint(53.12312, 3.12312, 3, region2, tracker1);
			GpsPoint gpsPoint4 = new GpsPoint(54.12312, 4.12312, 4, region2, tracker1);
			gpsPoints.add(gpsPoint1);
			gpsPoints.add(gpsPoint2);
			gpsPoints.add(gpsPoint3);
			gpsPoints.add(gpsPoint4);
			gpsPoints.forEach(gpsPointRepository::save);

			ArrayList<Car> cars = new ArrayList<>();
			Car car1 = new Car(2001,"NE-UK-01",10000.0,100.85,"Sedan1",false, new ArrayList<>(), rate1,  new ArrayList<>());
			Car car2 = new Car(2002,"NE-UK-02",20000.0,200.85,"Sedan2",false, new ArrayList<>(), rate2,  new ArrayList<>());
			Car car3 = new Car(2003,"NE-UK-03",30000.0,300.85,"Sedan2",false, new ArrayList<>(), rate2,  new ArrayList<>());
			cars.add(car1);
			cars.add(car2);
			cars.add(car3);
			cars.forEach(carRepository::save);

			ArrayList<Trip> trips = new ArrayList<>();
			Trip trip1 = new Trip(gpsPoint1, gpsPoint3, 2, car1);
			Trip trip2 = new Trip(gpsPoint2, gpsPoint4, 2, car2);
			trips.add(trip1);
			trips.add(trip2);
			trips.forEach(tripRepository::save);

			ArrayList<Invoice> invoices = new ArrayList<>();
			invoices.add(new Invoice(new Date(), 0, 200, 0, "Hallo", owner1));
			invoices.add(new Invoice(new Date(), 0, 40, 1, "Hallo", owner2));
			invoices.add(new Invoice(new Date(), 0, 600, 0, "Hallo", owner3));
			invoices.add(new Invoice(new Date(), 0, 275, 1, "Hallo", owner1));
			invoices.add(new Invoice(new Date(), 0, 500, 0, "Hallo", owner2));
			invoices.add(new Invoice(new Date(), 0, 25, 1, "Hallo", owner3));
			invoices.add(new Invoice(new Date(), 0, 245, 1, "Hallo", owner1));
			invoices.add(new Invoice(new Date(), 0, 365, 0, "Hallo", owner2));
			invoices.forEach(invoiceRepository::save);

			ArrayList<Car_Owner> carOwners = new ArrayList<>();
			Car_Owner carOwner1 = new Car_Owner(new Date(Instant.now().toEpochMilli()), new Date(Instant.now().toEpochMilli() + 100000));
			Car_Owner carOwner2 = new Car_Owner(new Date(Instant.now().toEpochMilli()), new Date(Instant.now().toEpochMilli() + 200000));
			Car_Owner carOwner3 = new Car_Owner(new Date(Instant.now().toEpochMilli()), new Date(Instant.now().toEpochMilli() + 300000));
			carOwners.add(carOwner1);
			carOwners.add(carOwner2);
			carOwners.add(carOwner3);
			carOwners.forEach(car_ownerRepository::save);

			ArrayList<Tracker_Car> trackerCars = new ArrayList<>();
			Tracker_Car trackerCar1 = new Tracker_Car(new Date(Instant.now().toEpochMilli()), new Date(Instant.now().toEpochMilli() + 100000));
			Tracker_Car trackerCar2 = new Tracker_Car(new Date(Instant.now().toEpochMilli()), new Date(Instant.now().toEpochMilli() + 200000));
			Tracker_Car trackerCar3 = new Tracker_Car(new Date(Instant.now().toEpochMilli()), new Date(Instant.now().toEpochMilli() + 300000));
			trackerCars.add(trackerCar1);
			trackerCars.add(trackerCar2);
			trackerCars.add(trackerCar3);
			trackerCars.forEach(tracker_carRepository::save);

			car1.setCarOwner(new LinkedList<Car_Owner>(){{add(carOwner1);}});
			car2.setCarOwner(new LinkedList<Car_Owner>(){{add(carOwner2);}});
			car3.setCarOwner(new LinkedList<Car_Owner>(){{add(carOwner3);}});
			owner1.setCarOwner(new LinkedList<Car_Owner>(){{add(carOwner1);}});
			owner2.setCarOwner(new LinkedList<Car_Owner>(){{add(carOwner2);}});
			owner3.setCarOwner(new LinkedList<Car_Owner>(){{add(carOwner3);}});
			cars.forEach(carRepository::save);
			owners.forEach(ownerRepository::save);
			carOwners.forEach(car_ownerRepository::save);

			car1.setTrackerCar(new LinkedList<Tracker_Car>(){{add(trackerCar1);}});
			car2.setTrackerCar(new LinkedList<Tracker_Car>(){{add(trackerCar2);}});
			car3.setTrackerCar(new LinkedList<Tracker_Car>(){{add(trackerCar3);}});
			tracker1.setTrackerCar(new LinkedList<Tracker_Car>(){{add(trackerCar1);}});
			tracker2.setTrackerCar(new LinkedList<Tracker_Car>(){{add(trackerCar2);}});
			tracker3.setTrackerCar(new LinkedList<Tracker_Car>(){{add(trackerCar3);}});
			cars.forEach(carRepository::save);
			trackers.forEach(trackerRepository::save);
			trackerCars.forEach(tracker_carRepository::save);

		};
	}


}
