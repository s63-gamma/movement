package com.gamma.dal.entities

import com.gamma.dal.util.DatabaseUtil
import com.gamma.dal.util.DatabaseUtilTest
import org.junit.Assert.*
import org.junit.Test

/**
 * Created by requinard on 2/23/17.
 */
class CarTest{
    @Test
    fun create(){
        val session = DatabaseUtil().getSession()
        session.beginTransaction()

        val car = Car(
                1994,
                "NE-UK-69",
                20000.0,
                800.85,
                "Sedan"
        )
        session.save(car)

        session.transaction.commit()
        session.close()

        assertNotNull(car.uuid)
    }
}
