package com.gamma.service

import com.gamma.dal.entities.GpsPoint
import com.gamma.dal.entities.Region
import com.gamma.dal.entities.Tracker
import com.gamma.dal.entities.Trip
import com.gamma.repository.GpsPointRepository
import com.gamma.repository.RegionRepository
import com.gamma.repository.TrackerRepository
import com.gamma.repository.TripRepository
import com.github.javafaker.Faker
import org.gavaghan.geodesy.Ellipsoid
import org.gavaghan.geodesy.GeodeticCalculator
import org.gavaghan.geodesy.GlobalPosition
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.TimeUnit


/**
 * Created by guushamm on 26-6-17.
 */
@RestController
class GenerationController {

    val geoCalc: GeodeticCalculator by lazy { GeodeticCalculator() }
    val faker: Faker by lazy { Faker() }
    var reference: Ellipsoid = Ellipsoid.WGS84

    @Autowired
    lateinit var tripRepository: TripRepository

    @Autowired
    lateinit var trackerRepository: TrackerRepository

    @Autowired
    lateinit var regionRepository: RegionRepository

    @Autowired
    lateinit var gpsPointRepository: GpsPointRepository

    @RequestMapping(value = "/generator/trips")
    fun generateTrips() {
        val trackers: ArrayList<Tracker> = trackerRepository.findAll() as ArrayList<Tracker>

        trackers.forEach { tracker ->
            val gpsPoints: ArrayList<GpsPoint> = ArrayList()
            tracker.gpsPoints.toCollection(gpsPoints)
            for (index in 1..gpsPoints.size / 2) {
                val startPoint = gpsPoints[index]
                val endPoint = gpsPoints[index + 1]
                val globalStartPoint = GlobalPosition(startPoint.latitude, startPoint.longitude, 0.0)
                val globalEndPoint = GlobalPosition(endPoint.latitude, endPoint.longitude, 0.0)
                val distance = geoCalc.calculateGeodeticCurve(reference, globalEndPoint, globalStartPoint).ellipsoidalDistance
                val trip: Trip = Trip(
                        startPoint = startPoint,
                        endPoint = endPoint,
                        totalDistance = distance,
                        tracker = tracker)
                tripRepository.save(trip)
            }
        }
    }

    @RequestMapping(value = "/generator/gpspoints")
    fun generateGpsPoints(@RequestParam(value = "amount", required = false) amount: Int = 25) {
        val regions: ArrayList<Region> = regionRepository.findAll() as ArrayList<Region>
        val trackers: ArrayList<Tracker> = trackerRepository.findAll() as ArrayList<Tracker>

        trackers.forEach { tracker ->
            for (i in 1..amount) {
                val randomRegion = regions[faker.random().nextInt(regions.size)]

                val longitude = randomRegion.longitude + (faker.number().numberBetween(-500, 500) * 0.00001)
                val latitude = randomRegion.latitude + (faker.number().numberBetween(-500, 500) * 0.00001)

                val gpsPoint = GpsPoint(
                        longitude = longitude,
                        latitude = latitude,
                        sequenceNumber = i,
                        region = randomRegion,
                        tracker = tracker,
                        date = faker.date().past(200000000, TimeUnit.MILLISECONDS).toInstant())
                gpsPointRepository.save(gpsPoint)
            }
        }

    }
}
