package com.gamma.service;

import com.gamma.dal.entities.Invoice;
import com.gamma.dal.entities.Owner;
import com.gamma.invoice.InvoiceGenerator;
import com.gamma.invoice.SendMailTLS;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
		Date date = new Date();
		Invoice invoice = new Invoice(date, 678.00, 678.99, 0, "ASDSTASDG");

		ArrayList<Owner> owners = new ArrayList<>();

		//owners.add(new Owner("Martijn", "Jintarmlubu@gmail.com", "Martijn", "van Buul", "0612345678", "England"));
		//owners.add(new Owner("Frank", "frankhartman96@gmail.com", "Frank", "Hartman", "0612345678", "England"));
		//owners.add(new Owner("Guus", "guushamm@gmail.com", "Guus", "Hamm", "0612345678", "England"));
		//owners.add(new Owner("Rick", "r.rongen@student.fontys.nl ", "Rick", "Rongen", "0612345678", "England"));
		//owners.add(new Owner("Jeffrey", "jeffrey.cornelissen@fontys.nl", "Jeffrey", "Cornelissen", "0612345678","Fontys Hogeschool Eindhoven"));

		Map<Owner, Invoice> map = new HashMap<>();
		for(Owner owner : owners) map.put(owner, invoice);

		SendMailTLS.sendInvoices(map, "Invoice test S63-c", "This invoice was automaticly generated", "Invoice " + invoice.getDate());
	}

}
