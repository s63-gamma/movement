package com.gamma.dal.entities

import com.gamma.dal.util.DatabaseUtil
import com.gamma.dal.util.DatabaseUtilTest
import org.junit.Assert.*
import org.junit.Test

/**
 * Created by requinard on 2/23/17.
 */
class RateTest{
    @Test
    fun create(){
        val session = DatabaseUtil().getSession()
        session.beginTransaction()

        val rate = Rate("Car", 2, "Alpen-Express")
        session.save(rate)

        session.transaction.commit()
        session.close()

        assertNotNull(rate.uuid)
    }
}
