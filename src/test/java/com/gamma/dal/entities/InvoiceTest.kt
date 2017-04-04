package com.gamma.dal.entities

import com.gamma.repository.InvoiceRepository
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.util.*

/**
 * Created by requinard on 2/23/17.
 */
@RunWith(SpringRunner::class)
@SpringBootTest
class InvoiceTest {
    lateinit var owner: Owner

    @Autowired
    lateinit var invoiceRepository: InvoiceRepository

    @Before
    fun setupRegion() {
        owner = Owner()
    }

    @Test
    fun createInvoice() {
        val invoice = Invoice(Date(), 1.0, 1.0, 1, "paid", owner)
        invoiceRepository.save(invoice)

        assertNotNull(invoice.uuid)
    }
}
