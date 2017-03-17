package com.gamma.dal.entities

import com.gamma.repository.TrackerRepository
import org.junit.Assert.*
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
class TrackerTest{
    @Autowired
    lateinit var trackerRepository: TrackerRepository

    @Test
    fun create(){
        val tracker = Tracker(1, 2, "hopsakee")

        trackerRepository.save(tracker)

        assertNotNull(tracker.uuid)
    }
}
