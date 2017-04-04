package com.gamma.controller;

import com.gamma.service.InvoiceService;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

/**
 * Created by frank on 04/04/2017.
 */
@Controller
public class InvoiceController {
	@Autowired
	InvoiceService invoiceService;

	@RequestMapping("sendInvoices")
	public void sendInvoices() throws IOException, DocumentException {
		invoiceService.sendInvoices();
	}
}
