package com.gamma.dal.entities

import com.gamma.dal.util.DatabaseUtil
import org.junit.Assert.*
import org.junit.Test

/**
 * Created by requinard on 2/23/17.
 */
class OwnerTest {
    @Test
    fun create() {
        val session = DatabaseUtil().getSession()
        session.beginTransaction()

        val owner = Owner(
                "robbyford",
                "r.ford@westwold.com",
                "Robert",
                "Ford",
                "+12413321321",
                "Westworld"
        )

        session.save(owner)

        session.transaction.commit()
        session.close()

        assertNotNull(owner.uuid)
    }
}
