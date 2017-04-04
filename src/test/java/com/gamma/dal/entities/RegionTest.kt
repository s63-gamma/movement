package com.gamma.dal.entities

import com.gamma.repository.RegionRepository
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

/**
 * Created by requinard on 2/21/17.
 */
@RunWith(SpringRunner::class)
@SpringBootTest
class RegionTest {
    @Autowired
    lateinit var regionRepository: RegionRepository

    @Test
    @Throws(Exception::class)
    fun createRegion() {
        val region = Region("MANC UNITED", 1.0, 1.0, 1.0, 1.0)
        regionRepository.save(region)

        assertNotNull(region.uuid)
    }

}
