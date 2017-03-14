package com.gamma.dal.entities

import com.gamma.repository.CarRepository
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
class CarTest{
    @Autowired
    lateinit var carRepository: CarRepository

    @Test
    fun create(){
        val car = Car(
                1994,
                "NE-UK-69",
                20000.0,
                800.85,
                "Sedan"
        )
        carRepository.save(car)

        assertNotNull(car.uuid)
    }
}
