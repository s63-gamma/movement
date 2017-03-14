package com.gamma.dal.entities

import com.gamma.repository.TripRepository
import org.junit.Assert.assertNotNull
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
    @Autowired
    lateinit var tripRepository: TripRepository

    @Test
    fun create(){
        val trip = Trip(0.1, 1.1, 1.0)
        tripRepository.save(trip)

        assertNotNull(trip.uuid)
    }
}
