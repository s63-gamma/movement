package com.gamma.service;

import com.gamma.dal.entities.Trip;
import com.gamma.repository.TripRepository;
import java.io.FileOutputStream;
import java.util.Date;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.GeneratedValue;
import javax.transaction.Transactional;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by frank on 21/03/2017.
 */
@Service
@Transactional
public class InvoiceService {
	@Autowired
	TripRepository tripRepository;

}
