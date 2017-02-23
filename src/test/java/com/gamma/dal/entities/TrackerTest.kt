package com.gamma.dal.entities

import com.gamma.dal.util.DatabaseUtil
import com.gamma.dal.util.DatabaseUtilTest
import org.junit.Assert.*
import org.junit.Test

/**
 * Created by requinard on 2/23/17.
 */
class TrackerTest{
    @Test
    fun create(){
        val session = DatabaseUtil().getSession()
        session.beginTransaction()

        val tracker = Tracker(1, 2, "hopsakee")
        session.save(tracker)

        session.transaction.commit()
        session.close()

        assertNotNull(tracker.uuid)
    }
}
