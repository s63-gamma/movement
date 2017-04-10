package com.gamma

import com.gamma.dal.entities.*
import com.gamma.repository.*
import com.github.javafaker.Faker
import org.aspectj.weaver.bcel.FakeAnnotation
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.support.SpringBootServletInitializer
import org.springframework.context.annotation.Bean

import java.time.Instant
import java.util.ArrayList
import java.util.Date
import java.util.LinkedList

@SpringBootApplication
class GMovementApplication : SpringBootServletInitializer() {

    override fun configure(application: SpringApplicationBuilder): SpringApplicationBuilder {
        return application.sources(GMovementApplication::class.java)
    }

    @Bean
    fun demo(invoiceRepository: InvoiceRepository, carRepository: CarRepository, ownerRepository: OwnerRepository, rateRepository: RateRepository, regionRepository: RegionRepository, trackerRepository: TrackerRepository, gpsPointRepository: GpsPointRepository, tripRepository: TripRepository, car_ownerRepository: Car_OwnerRepository, tracker_carRepository: Tracker_CarRepository): () -> Unit {
        return {
            val faker: Faker = Faker()
            val owners = ArrayList<Owner>()
            for (i in 1..20) {
                val firstName = faker.name().firstName()
                val lastName = faker.name().lastName()
                owners.add(Owner(
                        username = "$firstName$lastName",
                        emailadres = faker.internet().emailAddress(),
                        name = firstName,
                        surname = lastName,
                        phoneNumber = faker.phoneNumber().cellPhone(),
                        residence = faker.address().fullAddress(),
                        carOwner = ArrayList()))
            }
            ownerRepository.save(owners)

            val rates = ArrayList<Rate>()

            rateType: RateType.PASSENGER

            for (RateType{
                rates.add(Rate(type = "Passenger", cost = 10, name = "carTax"))
            }

            val rate2 = Rate("Truck", 50, "carTax")
            val rate3 = Rate("InnerCity", 2, "regionTax")
            val rate4 = Rate("OuterCity", 1, "regionTax")
            rates.add(rate1)
            rates.add(rate2)
            rates.add(rate3)
            rates.add(rate4)
            rates.forEach(Consumer<Rate> { rateRepository.save(it) })

            val regions = ArrayList<Region>()
            val region1 = Region("InnerCity", 51.55533, 1.12312, 1012.12, 1.0)
            val region2 = Region("OuterCity", 52.55533, 2.12312, 2012.12, 2.0)
            regions.add(region1)
            regions.add(region2)
            regions.forEach(Consumer<Region> { regionRepository.save(it) })

            val trackers = ArrayList<Tracker>()
            val tracker1 = Tracker(1, 1, "GPS", ArrayList<Tracker_Car>())
            val tracker2 = Tracker(2, 2, "GPS", ArrayList<Tracker_Car>())
            val tracker3 = Tracker(3, 3, "Camera", ArrayList<Tracker_Car>())
            trackers.add(tracker1)
            trackers.add(tracker2)
            trackers.add(tracker3)
            trackers.forEach(Consumer<Tracker> { trackerRepository.save(it) })

            val gpsPoints = ArrayList<GpsPoint>()
            val gpsPoint1 = GpsPoint(51.12312, 1.12312, 1, region1, tracker1)
            val gpsPoint2 = GpsPoint(52.12312, 2.12312, 2, region1, tracker1)
            val gpsPoint3 = GpsPoint(53.12312, 3.12312, 3, region2, tracker1)
            val gpsPoint4 = GpsPoint(54.12312, 4.12312, 4, region2, tracker1)
            gpsPoints.add(gpsPoint1)
            gpsPoints.add(gpsPoint2)
            gpsPoints.add(gpsPoint3)
            gpsPoints.add(gpsPoint4)
            gpsPoints.forEach(Consumer<GpsPoint> { gpsPointRepository.save(it) })

            val cars = ArrayList<Car>()
            val car1 = Car(2001, "NE-UK-01", 10000.0, 100.85, "Sedan1", false, ArrayList<Car_Owner>(), rate1, ArrayList<Tracker_Car>())
            val car2 = Car(2002, "NE-UK-02", 20000.0, 200.85, "Sedan2", false, ArrayList<Car_Owner>(), rate2, ArrayList<Tracker_Car>())
            val car3 = Car(2003, "NE-UK-03", 30000.0, 300.85, "Sedan2", false, ArrayList<Car_Owner>(), rate2, ArrayList<Tracker_Car>())
            cars.add(car1)
            cars.add(car2)
            cars.add(car3)
            cars.forEach(Consumer<Car> { carRepository.save(it) })

            val trips = ArrayList<Trip>()
            val trip1 = Trip(gpsPoint1, gpsPoint3, 2.0, car1)
            val trip2 = Trip(gpsPoint2, gpsPoint4, 2.0, car2)
            trips.add(trip1)
            trips.add(trip2)
            trips.forEach(Consumer<Trip> { tripRepository.save(it) })

            val invoices = ArrayList<Invoice>()
            invoices.add(Invoice(Date(), 0.0, 200.0, 0, "Hallo", owner1))
            invoices.add(Invoice(Date(), 0.0, 40.0, 1, "Hallo", owner2))
            invoices.add(Invoice(Date(), 0.0, 600.0, 0, "Hallo", owner3))
            invoices.add(Invoice(Date(), 0.0, 275.0, 1, "Hallo", owner1))
            invoices.add(Invoice(Date(), 0.0, 500.0, 0, "Hallo", owner2))
            invoices.add(Invoice(Date(), 0.0, 25.0, 1, "Hallo", owner3))
            invoices.add(Invoice(Date(), 0.0, 245.0, 1, "Hallo", owner1))
            invoices.add(Invoice(Date(), 0.0, 365.0, 0, "Hallo", owner2))
            invoices.forEach(Consumer<Invoice> { invoiceRepository.save(it) })

            val carOwners = ArrayList<Car_Owner>()
            val carOwner1 = Car_Owner(Date(Instant.now().toEpochMilli()), Date(Instant.now().toEpochMilli() + 100000))
            val carOwner2 = Car_Owner(Date(Instant.now().toEpochMilli()), Date(Instant.now().toEpochMilli() + 200000))
            val carOwner3 = Car_Owner(Date(Instant.now().toEpochMilli()), Date(Instant.now().toEpochMilli() + 300000))
            carOwners.add(carOwner1)
            carOwners.add(carOwner2)
            carOwners.add(carOwner3)
            carOwners.forEach(Consumer<Car_Owner> { car_ownerRepository.save(it) })

            val trackerCars = ArrayList<Tracker_Car>()
            val trackerCar1 = Tracker_Car(Date(Instant.now().toEpochMilli()), Date(Instant.now().toEpochMilli() + 100000))
            val trackerCar2 = Tracker_Car(Date(Instant.now().toEpochMilli()), Date(Instant.now().toEpochMilli() + 200000))
            val trackerCar3 = Tracker_Car(Date(Instant.now().toEpochMilli()), Date(Instant.now().toEpochMilli() + 300000))
            trackerCars.add(trackerCar1)
            trackerCars.add(trackerCar2)
            trackerCars.add(trackerCar3)
            trackerCars.forEach(Consumer<Tracker_Car> { tracker_carRepository.save(it) })

            car1.carOwner = object : LinkedList<Car_Owner>() {
                init {
                    add(carOwner1)
                }
            }
            car2.carOwner = object : LinkedList<Car_Owner>() {
                init {
                    add(carOwner2)
                }
            }
            car3.carOwner = object : LinkedList<Car_Owner>() {
                init {
                    add(carOwner3)
                }
            }
            owner1.carOwner = object : LinkedList<Car_Owner>() {
                init {
                    add(carOwner1)
                }
            }
            owner2.carOwner = object : LinkedList<Car_Owner>() {
                init {
                    add(carOwner2)
                }
            }
            owner3.carOwner = object : LinkedList<Car_Owner>() {
                init {
                    add(carOwner3)
                }
            }
            cars.forEach(Consumer<Car> { carRepository.save(it) })
            owners.forEach(Consumer<Owner> { ownerRepository.save(it) })
            carOwners.forEach(Consumer<Car_Owner> { car_ownerRepository.save(it) })

            car1.trackerCar = object : LinkedList<Tracker_Car>() {
                init {
                    add(trackerCar1)
                }
            }
            car2.trackerCar = object : LinkedList<Tracker_Car>() {
                init {
                    add(trackerCar2)
                }
            }
            car3.trackerCar = object : LinkedList<Tracker_Car>() {
                init {
                    add(trackerCar3)
                }
            }
            tracker1.trackerCar = object : LinkedList<Tracker_Car>() {
                init {
                    add(trackerCar1)
                }
            }
            tracker2.trackerCar = object : LinkedList<Tracker_Car>() {
                init {
                    add(trackerCar2)
                }
            }
            tracker3.trackerCar = object : LinkedList<Tracker_Car>() {
                init {
                    add(trackerCar3)
                }
            }
            cars.forEach(Consumer<Car> { carRepository.save(it) })
            trackers.forEach(Consumer<Tracker> { trackerRepository.save(it) })
            trackerCars.forEach(Consumer<Tracker_Car> { tracker_carRepository.save(it) })

        }
    }

    companion object {

        @Throws(Exception::class)
        @JvmStatic fun main(args: Array<String>) {
            SpringApplication.run(GMovementApplication::class.java, *args)
        }
    }


}
