package com.gamma.service;

import com.gamma.dal.entities.Invoice;
import com.gamma.dal.entities.Owner;
import com.gamma.dal.entities.Trip;
import com.gamma.dal.util.InvoiceGenerator;
import com.gamma.dal.util.SendMailTLS;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.Date;

/**
 * Created by frank on 21/03/2017.
 */
public class InvoiceServiceTest {
	InvoiceService invoiceService;

	@Before
	public void setUp() throws Exception {
		invoiceService = Mockito.mock(InvoiceService.class);
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void generateInvoice() throws Exception {
		InvoiceGenerator generator = new InvoiceGenerator();
		Date date = new Date(100);
		Invoice invoice = new Invoice(date, 678.00, 678.99, 0, "ASDSTASDG");
		Owner owner = new Owner("frenkie", "frankhartman96@gmail.com", "Frank", "Hartman", "0612345678", "Eindhoven");

		ByteArrayOutputStream outputStream = generator.createInvoice(invoice, owner);
		SendMailTLS.sendMail(outputStream, "Invoice " + invoice.getDate().toString(), "Please pay this invoice", invoice.getDate().toString(), owner.getEmailadres());

	}

}
