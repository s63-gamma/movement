package com.gamma.dal.entities

import com.gamma.repository.RateRepository
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
class RateTest{

    @Autowired
    lateinit var rateRepository: RateRepository

    @Test
    fun create(){

        val rate = Rate("Car", 2, "Alpen-Express")
        rateRepository.save(rate)

        assertNotNull(rate.uuid)
    }
}
