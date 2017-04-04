package com.gamma.dal.entities

import com.gamma.repository.TripRepository
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

/**
 * Created by requinard on 2/23/17.
 */
@RunWith(SpringRunner::class)
@SpringBootTest
class TripTest{
    lateinit var car: Car
    lateinit var startGpsPoint: GpsPoint
    lateinit var endGpsPoint: GpsPoint

    @Autowired
    lateinit var tripRepository: TripRepository

    @Before
    fun setupRegion() {
        car = Car()
        startGpsPoint = GpsPoint();
        endGpsPoint = GpsPoint();
    }

    @Test
    fun create(){
        val trip = Trip(startGpsPoint, endGpsPoint, 1.0, car)
        tripRepository.save(trip)

        assertNotNull(trip.uuid)
    }
}
