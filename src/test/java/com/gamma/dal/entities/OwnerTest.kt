package com.gamma.dal.entities

import com.gamma.repository.OwnerRepository
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
class OwnerTest {
    @Autowired
    lateinit var ownerRepository: OwnerRepository

    @Test
    fun create() {
        val owner = Owner(
                "robbyford",
                "r.ford@westwold.com",
                "Robert",
                "Ford",
                "+12413321321",
                "Westworld"
        )

        ownerRepository.save(owner)

        assertNotNull(owner.uuid)
    }
}
