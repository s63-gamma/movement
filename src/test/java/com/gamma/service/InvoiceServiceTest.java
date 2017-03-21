package com.gamma.service;

import com.gamma.dal.entities.Invoice;
import com.gamma.dal.entities.Owner;
import com.gamma.dal.entities.Trip;
import com.gamma.dal.util.InvoiceGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import java.util.Date;

/**
 * Created by frank on 21/03/2017.
 */
public class InvoiceServiceTest {
	InvoiceService invoiceService;

	public static final String DEST = "C:\\Users\\frank\\Documents\\Intellij\\S63-Gamma";

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
		generator.createPdf("C:\\Users\\frank\\Documents\\appel.pdf", invoice, new Owner("adfadr", "adfadr", "adfadr", "adfadr", "adfadr", "adfadr"));

	}

}
