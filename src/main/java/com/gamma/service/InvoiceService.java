package com.gamma.service;

import com.gamma.dal.entities.*;
import com.gamma.dal.util.InvoiceGenerator;
import com.gamma.repository.InvoiceRepository;

import java.io.*;
import java.time.temporal.ChronoField;
import java.util.*;

import com.itextpdf.text.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by frank on 21/03/2017.
 */
@RestController
public class InvoiceService {
	@Autowired
	InvoiceRepository invoiceRepository;

	@RequestMapping(value="/generatePdf/{id}")
	public ResponseEntity<byte[]> getPDF(@PathVariable("id") UUID id) throws IOException, DocumentException {
		Invoice invoice = invoiceRepository.getOne(id);

		if (invoice == null)
			throw new NullPointerException();

		byte[] contents = InvoiceGenerator.createInvoice(invoice).toByteArray();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		String filename = "invoice.pdf";
		headers.setContentDispositionFormData(filename, filename);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(contents, headers, HttpStatus.OK);
		return response;
	}

	@RequestMapping(value = "/quickRecalculation/{id]")
	public Invoice quickRecalculation(@PathVariable("id") UUID id){
		Invoice invoice = invoiceRepository.getOne(id);

		List<Trip> trips = getRandomTrips(invoice);

		double totalDistance = trips.stream().mapToDouble(Trip::getTotalDistance).sum();

		if(invoice.getDistance() != totalDistance){
			invoice = fullRecalculationInternal(invoice, trips);
		}
		invoice.setDistance(totalDistance);
		invoiceRepository.saveAndFlush(invoice);
		return invoice;
	}

	@RequestMapping(value = "/fullRecalculation/{id}")
	public Invoice fullRecalculation(@PathVariable("id") UUID id){
		Invoice invoice = invoiceRepository.getOne(id);
//		List<Trip> trips = getRandomTrips(invoice);
//		invoice = fullRecalculationInternal(invoice, trips);
		return invoice;
	}

	private List<Trip> getRandomTrips(Invoice invoice){
		Date date = invoice.getDate();
		List<Car> cars = invoice.getOwner().getCarOwner().stream().filter(car_owner ->
				date.getMonth() == car_owner.getStartDate().getMonth() && date.getMonth() == car_owner.getEndDate().getMonth())
				.map(Car_Owner::getCar).collect(Collectors.toList());

		List<Tracker> trackers = cars.stream().flatMap(car -> car.getTrackerCar().stream()).map(Tracker_Car::getTracker).collect(Collectors.toList());

		List<Trip> trips = trackers.stream().flatMap(tracker -> tracker.getTrips().stream())
				.filter(t -> t.getStartPoint().getDate().get(ChronoField.MONTH_OF_YEAR) == date.getMonth()).collect(Collectors.toList());

		Random rand = new Random();
		int count = rand.nextInt(daysOfMonth(date.getMonth()) * 2);
		Set<Trip> tripSet = new HashSet<>();
		for (int i = 0; i < count; i ++){
			tripSet.add(trips.get(rand.nextInt(trips.size())));
		}
		return new ArrayList<>(tripSet);
	}

	private Invoice fullRecalculationInternal(Invoice invoice, List<Trip> trips){
		double totalDistance = 0;
		double totalPrice = 0;
		double ctPerKilometer = 1.2; //0.012£/KM

		List<GpsPoint> endPoints = trips.stream().map(Trip::getEndPoint).collect(Collectors.toList());
		List<GpsPoint> allGpsPoints = trips.stream().map(Trip::getTracker)
				.flatMap(tracker -> tracker.getGpsPoints().stream()).collect(Collectors.toList());

		GpsPoint lastGpsPoint = null;
		for (GpsPoint point: allGpsPoints) {
			if (lastGpsPoint == null) {
				//first gpspoint (of trip)
				lastGpsPoint = point;
				continue;
			}

			double distance = distance(point, lastGpsPoint);
			double price = ctPerKilometer * (distance / 1000);

			Region region = point.getRegion();
			if(region != null){
				price = region.getCostMultiplier() * price;
			}

			totalDistance += distance;
			totalPrice += price;
			if(endPoints.stream().anyMatch(p -> p.getUuid().equals(point.getUuid()))){
				lastGpsPoint = null;//new trip
			}
			lastGpsPoint = point;
		}

		invoice.setDistance(totalDistance);
		invoice.setPriceTotal(totalPrice / 100); //convert cents to pounds

		invoiceRepository.saveAndFlush(invoice);
		return invoice;
	}


	private int daysOfMonth(int month){
		if(month == 2)
			return 28;
		if(month < 8){
			return month % 2 == 0 ? 30 : 31;
		}
		return month % 2 == 0 ? 31 : 30;
	}

	/**
	 * Calculate distance between two points in latitude and longitude taking
	 * into account height difference. If you are not interested in height
	 * difference pass 0.0. Uses Haversine method as its base.
	 *
	 * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
	 * el2 End altitude in meters
	 * @return Distance in Meters
	 */
	public static double distance(GpsPoint gp1, GpsPoint gp2) { //double lat1, double lat2, double lon1, double lon2, double el1, double el2) {
		//https://stackoverflow.com/a/16794680
		final int R = 6371; // Radius of the earth

		double latDistance = Math.toRadians(gp2.getLatitude() - gp1.getLatitude());
		double lonDistance = Math.toRadians(gp2.getLongitude() - gp1.getLongitude());
		double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
				+ Math.cos(Math.toRadians(gp1.getLatitude())) * Math.cos(Math.toRadians(gp2.getLatitude()))
				* Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double distance = R * c * 1000; // convert to meters

//		double height = el1 - el2;

//		distance = Math.pow(distance, 2) + Math.pow(height, 2);

		return distance;//Math.sqrt(distance);
	}

}
