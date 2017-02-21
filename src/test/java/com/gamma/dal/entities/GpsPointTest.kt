package com.gamma.dal.entities

import com.gamma.dal.util.DatabaseUtil
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

import org.junit.Assert.*

/**
 *
 */
class GpsPointTest {
    lateinit var region: Region

    @Before
    fun setupRegion() {
        val session = DatabaseUtil().getSession()
        session.beginTransaction()

        region = Region("So far in the closet he's found narnia", 1, 1, 1, 1)
        session.save(region)

        session.transaction.commit()
        session.close()
    }

    @Test
    fun createGpsPoint() {
        val session = DatabaseUtil().getSession()
        session.beginTransaction()

        val gpsPoint = GpsPoint(1.0, 1.0, 1)
        gpsPoint.region = region
        session.save(gpsPoint)

        session.transaction.commit()
        session.close()

        assertNotNull(gpsPoint.id)
    }

}
