package com.gamma.dal.entities

import com.gamma.repository.TrackerRepository
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
class TrackerTest{
    lateinit var trackerCar: LinkedList<Tracker_Car>

    @Autowired
    lateinit var trackerRepository: TrackerRepository

    @Before
    fun setupRegion() {
        trackerCar = LinkedList<Tracker_Car>()
    }

    @Test
    fun create(){
        val tracker = Tracker(1, 2, "hopsakee", trackerCar)

        trackerRepository.save(tracker)

        assertNotNull(tracker.uuid)
    }
}
