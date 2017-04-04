package com.gamma.service;
import com.gamma.dal.entities.Invoice;
import com.gamma.dal.entities.Owner;
import com.gamma.invoice.SendMailTLS;
import com.gamma.repository.InvoiceRepository;
import com.gamma.repository.OwnerRepository;


import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by frank on 21/03/2017.
 */
@Service
@Transactional
public class InvoiceService {
	@Autowired
	OwnerRepository ownerRepository;
	@Autowired
	InvoiceRepository invoiceRepository;

	public void sendInvoices() throws IOException, DocumentException {
		// Get an example invoice
		Invoice invoice = invoiceRepository.findAll().stream().findFirst().value;

		// Get all of the current owners
		List<Owner> owners = ownerRepository.findAll();
		Map<Owner, Invoice> map = new HashMap<>();
		for(Owner owner : owners) map.put(owner, invoice);

		SendMailTLS.sendInvoices(map);
	}

}
