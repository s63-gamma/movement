package com.gamma.dal.util

import org.junit.Test

import org.junit.Assert.*

/**
 * Created by requinard on 2/21/17.
 */
class DatabaseUtilTest {
    @Test
    fun getSession() {
        val dbUtil = DatabaseUtil()
        val session = dbUtil.getSession()

        assertNotNull(session);
    }

}