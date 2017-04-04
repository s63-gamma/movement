package com.gamma.dal.entities

import com.gamma.repository.CarRepository
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.util.*

/**
 * Created by requinard on 2/23/17.
 */
@RunWith(SpringRunner::class)
@SpringBootTest
class CarTest{
    lateinit var carOwner: LinkedList<Car_Owner>
    lateinit var rate: Rate
    lateinit var trackerCar: LinkedList<Tracker_Car>


    @Autowired
    lateinit var carRepository: CarRepository

    @Before
    fun setupRegion() {
        carOwner = LinkedList<Car_Owner>()
        rate = Rate()
        trackerCar = LinkedList<Tracker_Car>()
    }

    @Test
    fun create(){
        val car = Car(
                1994,
                "NE-UK-69",
                20000.0,
                800.85,
                CarType.SEDAN,
                false,
                carOwner,
                rate,
                trackerCar
        )
        carRepository.save(car)

        assertNotNull(car.uuid)
    }
}
