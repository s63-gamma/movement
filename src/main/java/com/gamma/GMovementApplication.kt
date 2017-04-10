package com.gamma

import com.gamma.dal.entities.*
import com.gamma.repository.*
import com.github.javafaker.Faker
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.support.SpringBootServletInitializer
import org.springframework.context.annotation.Bean
import java.util.*
import java.util.concurrent.TimeUnit

@SpringBootApplication
open class GMovementApplication : SpringBootServletInitializer() {

    override fun configure(application: SpringApplicationBuilder): SpringApplicationBuilder {
        return application.sources(GMovementApplication::class.java)
    }

    @Bean
    open fun init(invoiceRepository: InvoiceRepository, carRepository: CarRepository, ownerRepository: OwnerRepository, rateRepository: RateRepository, regionRepository: RegionRepository, trackerRepository: TrackerRepository, gpsPointRepository: GpsPointRepository, tripRepository: TripRepository, car_ownerRepository: Car_OwnerRepository, tracker_carRepository: Tracker_CarRepository) = CommandLineRunner {
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
        owners.forEach { ownerRepository.save(it) }

        val rates = ArrayList<Rate>()

        rates.add(Rate(
                type = "Passenger",
                cost = 10,
                name = "Passenger tax"))
        rates.add(Rate(
                type = "Truck",
                cost = 20,
                name = "Truck tax"))
        rates.add(Rate(
                type = "Innercity",
                cost = 30,
                name = "Innercity tax"))
        rates.add(Rate(
                type = "Outercity",
                cost = 10,
                name = "Outercity tax"))


        rates.forEach { rateRepository.save(it) }

        val regions = ArrayList<Region>()
        regions.add(Region(
                name = "Oxfordshire",
                longitude = -1.153994,
                latitude = 51.900753,
                radius = 20000,
                costMultiplier = 1.8))
//        regions.add(Region(
//                name = "Bicester",
//                longitude = -1.153994,
//                latitude = 51.900753,
//                radius = 5000,
//                costMultiplier = 1.8))
//        regions.add(Region(
//                name = "Bicester City Center",
//                longitude = -1.153994,
//                latitude = 51.900753,
//                radius = 500,
//                costMultiplier = 2.2))

        regions.forEach { regionRepository.save(it) }

        val trackers = ArrayList<Tracker>()
        for (i in 1..20) {
            trackers.add(Tracker(
                    authorisationCode = 1,
                    serialNumber = faker.number().numberBetween(1, 10000000),
                    type = TrackerType.values()[faker.random().nextInt(TrackerType.values().size)]
            ))
        }
        trackers.forEach { trackerRepository.save(it) }

//        val gpsPoints = ArrayList<GpsPoint>()
//        for (i in 1..300) {
//            val randomRegion = regions[faker.random().nextInt(regions.size)]
//            val randomTracker = trackers[faker.random().nextInt(trackers.size)]
//
//            val longitude = randomRegion.longitude + (faker.number().numberBetween(-1000,1000) * 0.00001)
//            val latitude = randomRegion.latitude + (faker.number().numberBetween(-1000,1000) * 0.00001)
//
//            gpsPoints.add(GpsPoint(
//                    longitude = longitude,
//                    latitude = latitude,
//                    sequenceNumber = i,
//                    region = randomRegion,
//                    tracker = randomTracker,
//                    date= faker.date().past(200000000, TimeUnit.MILLISECONDS).toInstant())
//            )
//        }
//        gpsPoints.forEach { gpsPointRepository.save(it) }

        val cars = ArrayList<Car>()
        for (i in 1..20) {
            cars.add(Car(
                    buildingYear = faker.number().numberBetween(1996, 2017),
                    licensePlate = faker.bothify("??##-???", true),
                    weight = faker.number().numberBetween(500, 40000).toDouble(),
                    milage = faker.number().randomDouble(1, 0, 350000),
                    type = CarType.values()[CarType.values().size - 1],
                    rate = rates[faker.random().nextInt(rates.size)]
            ))
        }
        cars.forEach { carRepository.save(it) }

//        val trips = ArrayList<Trip>()
//        var index = 0
//        for (i in 1..gpsPoints.size / 2) {
//            trips.add(Trip(
//                    startPoint = gpsPoints[index],
//                    endPoint = gpsPoints[index + 1],
//                    totalDistance = 2.0,
//                    car = cars[faker.random().nextInt(cars.size)]))
//            index += 2
//        }
//        trips.forEach { tripRepository.save(it) }

        val invoices = ArrayList<Invoice>()
        for (i in 1..20) {
            invoices.add(Invoice(
                    date = Date(),
                    distance = faker.number().numberBetween(0, 2000).toDouble(),
                    priceTotal = faker.number().numberBetween(0, 10000).toDouble(),
                    status = faker.number().numberBetween(0, 1),
                    paymentCode = faker.bothify("##???#?#?"),
                    owner = owners[faker.random().nextInt(owners.size)]
            ))
        }
        invoices.forEach { invoiceRepository.save(it) }

        cars.forEachIndexed { index, car ->
            run {
                val trackerCar: Tracker_Car = Tracker_Car(startDate = faker.date().past(200000000, TimeUnit.MILLISECONDS))
                trackerCar.tracker = trackers[index]
                car.trackerCar += trackerCar

//                val carOwner: Car_Owner = Car_Owner(startDate = faker.date().past(200000000, TimeUnit.MILLISECONDS), endDate = faker.date().future(500000000, TimeUnit.MILLISECONDS))
//                carOwner.owner = owners[faker.random().nextInt(owners.size)]
//                car.carOwner += carOwner
            }

        }
        cars.forEach { carRepository.save(it) }
    }

}
