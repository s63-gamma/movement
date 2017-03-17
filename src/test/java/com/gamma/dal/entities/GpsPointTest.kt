package com.gamma.dal.entities

import com.gamma.repository.GpsPointRepository
import com.gamma.repository.RegionRepository
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

/**
 *
 */
@RunWith(SpringRunner::class)
@SpringBootTest
class GpsPointTest {
    lateinit var region: Region

    @Autowired
    lateinit var gpsPointRepository: GpsPointRepository

    @Autowired
    lateinit var regionRepository: RegionRepository

    @Before
    fun setupRegion() {
        region = Region("So far in the closet he's found narnia", 1, 1, 1, 1)
        regionRepository.saveAndFlush(region)
    }

    @Test
    fun createGpsPoint() {
        val gpsPoint = GpsPoint(1.0, 1.0, 1)
        gpsPoint.region = region
        gpsPointRepository.save(gpsPoint)

        assertNotNull(gpsPoint.uuid)
    }

}
