package com.gamma.service;

import com.gamma.dal.entities.Invoice;
import com.gamma.dal.entities.Owner;
import com.gamma.dal.entities.Trip;
import com.gamma.dal.util.InvoiceGenerator;
import com.gamma.repository.InvoiceRepository;
import com.gamma.repository.TripRepository;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.util.Date;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.persistence.GeneratedValue;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.FileOutputStream;
import java.util.UUID;

/**
 * Created by frank on 21/03/2017.
 */
@RestController
public class InvoiceService {
	@Autowired
	InvoiceRepository invoiceRepository;

	@RequestMapping(value="/generatePdf/{id}")
	public ResponseEntity<byte[]> getPDF(@PathVariable("id") UUID id) throws IOException, DocumentException {
		Invoice invoice = invoiceRepository.getOne(id);

		if (invoice == null)
			throw new NullPointerException();

		byte[] contents = InvoiceGenerator.createInvoice(invoice).toByteArray();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		String filename = "invoice.pdf";
		headers.setContentDispositionFormData(filename, filename);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(contents, headers, HttpStatus.OK);
		return response;
	}
	
	@RequestMapping(value = "/mailInvoice/{id}")
	public ResponseEntity mailInvoice(@PathVariable("id") UUID id) throws IOException, DocumentException {
			Invoice invoice = invoiceRepository.getOne(id);
			if (invoice == null)
				throw new NullPointerException();

			InvoiceGenerator generator = new InvoiceGenerator();
			ByteArrayOutputStream outputStream = generator.createInvoice(invoice);
			SendMailTLS.sendMail(outputStream, "Invoice " + invoice.getDate().toString(), "Please pay this invoice",
					invoice.getDate().toString(), invoice.getOwner().getEmailadres());

			return ResponseEntity.ok().build();
		}


}
