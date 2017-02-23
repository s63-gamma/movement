package com.gamma.dal.entities

import ch.qos.logback.core.db.dialect.DBUtil
import com.gamma.dal.util.DatabaseUtil
import org.junit.Assert.*
import org.junit.Test
import java.util.*

/**
 * Created by requinard on 2/23/17.
 */
class InvoiceTest {
    val dbUtil by lazy { DatabaseUtil() }
    @Test
    fun createInvoice() {
        val session = dbUtil.getSession()
        session.beginTransaction()

        val invoice = Invoice(Date(), 1.0, 1.0, 1, "paid")
        session.save(invoice)

        session.transaction.commit()
        session.close()

        assertNotNull(invoice.uuid)
    }
}
