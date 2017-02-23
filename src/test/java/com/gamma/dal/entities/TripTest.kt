package com.gamma.dal.entities

import com.gamma.dal.util.DatabaseUtil
import com.gamma.dal.util.DatabaseUtilTest
import org.junit.Assert.*
import org.junit.Test

/**
 * Created by requinard on 2/23/17.
 */
class TripTest{
    @Test
    fun create(){
        val session = DatabaseUtil().getSession()
        session.beginTransaction()

        val trip = Trip(0.1, 1.1, 1.0)
        session.save(trip)

        session.transaction.commit()
        session.close()

        assertNotNull(trip.uuid)
    }
}
