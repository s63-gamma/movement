package com.gamma.configuration

import com.gamma.dal.entities.*
import com.gamma.repository.*
import com.gamma.service.InvoiceService
import com.github.javafaker.Faker
import com.gmail.guushamm.EuropeanIntegration.Connector
import com.gmail.guushamm.EuropeanIntegration.Countries
import com.gmail.guushamm.EuropeanIntegration.StolenCar
import com.google.gson.Gson
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.time.Instant
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by guushamm on 21-3-17.
 */
@Component
open class IntegrationConfiguration {
    val gson: Gson by lazy { Gson() }
    val faker: Faker by lazy { Faker() }

    @Autowired
    lateinit var carRepository: CarRepository

    @Autowired
    lateinit var invoiceRepository: InvoiceRepository

    @Autowired
    lateinit var rateRepository: RateRepository

    @Autowired
    lateinit var trackerRepository: TrackerRepository

    @Autowired
    lateinit var gpsPointRepository: GpsPointRepository

    @Autowired
    lateinit var regionRepository: RegionRepository

    @Autowired
    lateinit var invoiceService: InvoiceService

    val connector: Connector = Connector()

    fun initialize() {
        System.out.println("YO BOY, INTEGRATION")
        connector.prepare()
        connector.subscribeToQueue(Countries.UNITED_KINGDOM, com.gmail.guushamm.EuropeanIntegration.Car::class.java, { message: String ->
            val recievedCar: com.gmail.guushamm.EuropeanIntegration.Car = gson.fromJson(message, com.gmail.guushamm.EuropeanIntegration.Car::class.java)

            val cars = carRepository.findByLicensePlate(recievedCar.licensePlate)

            print(cars)

            if (cars.size == 0) {
                val car: Car = Car(
                        buildingYear = 0,
                        licensePlate = recievedCar.licensePlate + "@" + countryToString(recievedCar.originCountry),
                        weight = 0.0,
                        milage = 0.0,
                        type = CarType.FOREIGN,
                        isStolen = false,
                        carOwner = emptyList<Car_Owner>(),
                        rate = rateRepository.findByType("Foreign"),
                        trackerCar = emptyList<Tracker_Car>()
                )
                carRepository.save(car)

                val tracker: Tracker = Tracker(
                        authorisationCode = 1,
                        serialNumber = faker.number().numberBetween(1, 10000000),
                        type = TrackerType.CAMERA

                )
                trackerRepository.save(tracker)

                val trackerCar: Tracker_Car = Tracker_Car(startDate = Date.from(Instant.now()))
                trackerCar.tracker = tracker
                car.trackerCar += trackerCar
                carRepository.save(car)

                // Add a random entry point
                val region = regionRepository.findAll()[0]

                val longitude = region.longitude + (faker.number().numberBetween(-1000, 1000) * 0.00001)
                val latitude = region.latitude + (faker.number().numberBetween(-1000, 1000) * 0.00001)

                val gpsPoint: GpsPoint = GpsPoint(
                        longitude = longitude,
                        latitude = latitude,
                        sequenceNumber = faker.number().randomDigitNotZero(),
                        region = region,
                        tracker = tracker,
                        date = Instant.now()
                )
                gpsPointRepository.save(gpsPoint)

            }
        })
        connector.subscribeToQueue(Countries.UNITED_KINGDOM, com.gmail.guushamm.EuropeanIntegration.Invoice::class.java, { message: String ->
            val recievedInvoice = gson.fromJson(message, com.gmail.guushamm.EuropeanIntegration.Invoice::class.java)

            val cars = carRepository.findByLicensePlate(recievedInvoice.licensePlate)

            if (cars.size == 0) {
                //TODO FATAL not found, not mine?
                return@subscribeToQueue
            }

            //get the current owner of the car
            val first = cars[0].carOwner.stream().filter({ car_Owner -> car_Owner.endDate == null }).findFirst()
            if (!first.isPresent) {
                //TODO FATAL car doesn't currently have an owner
            }
            val owner = first.get()
            val invoice = Invoice(//TODO should we send the money to the origin?
                    date = recievedInvoice.date,
                    distance = recievedInvoice.kilometers,
                    priceTotal = recievedInvoice.price,
                    status = 0,
                    paymentCode = "",
                    owner = owner.owner!!
            )
            invoiceRepository.save(invoice)
            invoiceService.mailInvoice(invoice.uuid)//TODO check if uuid is correct
        })

        connector.subscribeToQueue(Countries.UNITED_KINGDOM, StolenCar::class.java, { message: String ->
            val stolenCar = gson.fromJson(message, com.gmail.guushamm.EuropeanIntegration.StolenCar::class.java)

            if (stolenCar.countryOfOrigin == Countries.UNITED_KINGDOM) {
                val cars = carRepository.findByLicensePlate(stolenCar.licensePlate)

                if (cars.size == 0) {
                    //TODO FATAL this car doesn't have a record of being registered in the UK
                }
                val car = cars.first()
                car.isStolen = stolenCar.stolenCar
                carRepository.save(car)
                return@subscribeToQueue
            } else {
                val cars = carRepository.findByLicensePlate(stolenCar.licensePlate + "@" + countryToString(stolenCar.countryOfOrigin))
                if (cars.size == 0) {
                    val car: Car = Car(
                            buildingYear = 0,
                            licensePlate = stolenCar.licensePlate + "@" + countryToString(stolenCar.countryOfOrigin),
                            weight = 0.0,
                            milage = 0.0,
                            type = CarType.FOREIGN,
                            isStolen = stolenCar.stolenCar,
                            carOwner = emptyList<Car_Owner>(),
                            rate = rateRepository.findByType("Foreign"),
                            trackerCar = emptyList()
                    )
                    carRepository.save(car)

                    val tracker: Tracker = Tracker(
                            authorisationCode = 1,
                            serialNumber = faker.number().numberBetween(1, 10000000),
                            type = TrackerType.CAMERA

                    )
                    trackerRepository.save(tracker)

                    val trackerCar: Tracker_Car = Tracker_Car(startDate = faker.date().past(200000000, TimeUnit.MILLISECONDS))
                    trackerCar.tracker = tracker
                    car.trackerCar += trackerCar
                    carRepository.save(car)
                    return@subscribeToQueue
                } else {
                    val car = cars.first()
                    car.isStolen = stolenCar.stolenCar
                    carRepository.save(car)
                }
            }
        })
    }

    fun reportCarStolen(stolen: Boolean = true, licensePlate: String) {
        val sc = StolenCar(
                licensePlate = licensePlate,
                countryOfOrigin = Countries.UNITED_KINGDOM,
                stolenCar = stolen
        )
        connector.publishStolenCar(sc)
    }

    fun publishCar(car: Car, countryCode: String): Boolean {
        val destCode: Countries = stringToCountry(countryCode) ?: return false

        val conCar = com.gmail.guushamm.EuropeanIntegration.Car(
                licensePlate = car.licensePlate,
                originCountry = Countries.UNITED_KINGDOM,
                destinationCountry = destCode,
                stolen = car.isStolen
        )
        connector.publishCar(conCar)
        return true
    }

    fun publishInvoice(invoice: Invoice, destination: String, licencePlate: String): Boolean {
        val destCode: Countries = stringToCountry(destination) ?: return false

        val conInv = com.gmail.guushamm.EuropeanIntegration.Invoice(
                kilometers = invoice.distance,
                price = invoice.priceTotal,
                licensePlate = licencePlate,
                destinationCountry = destCode,
                originCountry = Countries.UNITED_KINGDOM,
                date = invoice.date
        )
        connector.publishInvoice(conInv)
        return true
    }

    fun foundStolen(car: Car) {
        //TODO NYI
    }

    fun stringToCountry(country: String): Countries? {
        when (country) {
            "BE", "BELGIUM" -> return Countries.BELGIUM
            "NL", "NETHERLANDS" -> return Countries.NETHERLANDS
            "DN", "DENMARK" -> return Countries.DENMARK
            "SW", "SWEDEN" -> return Countries.SWEDEN
            "UK", "UNITED_KINGDOM" -> return Countries.UNITED_KINGDOM
            else -> return null
        }
    }

    fun countryToString(country: Countries): String? {
        when (country) {
            Countries.NETHERLANDS -> return "NL"
            Countries.BELGIUM -> return "BE"
            Countries.UNITED_KINGDOM -> return "UK"
            Countries.SWEDEN -> return "SW"
            Countries.DENMARK -> return "DN"
            else -> return null
        }
    }
}
