package com.gamma.dal.util;

import com.gamma.dal.entities.Invoice;
import com.gamma.dal.entities.Owner;
import com.itextpdf.text.*;
import com.itextpdf.text.List;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by frank on 21/03/2017.
 */
public class InvoiceGenerator {

	Font chapterFont = FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLDITALIC);
	Font paragraphFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL);

	public void createPdf(String dest, Invoice invoice, Owner owner) throws IOException, DocumentException {
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, new FileOutputStream(dest));
		document.open();

		document.add(createParagraph("Bill driver invoice" + invoice.getUuid() + " " + invoice.getDate()));
		document.add(createParagraph(convertDate(invoice.getDate(), "MMM dd, yyyy")));

		document.add(createInvoiceInfo(invoice, owner));

		document.add(createParagraph(""));

		document.add(createPriceTable(invoice));
		document.add(createPaymentText(invoice));

		document.close();
	}

	private Paragraph createParagraph(String content) {
		Paragraph paragraph = new Paragraph(content, paragraphFont);
		paragraph.setAlignment(Element.ALIGN_LEFT);
		return paragraph;
	}

	private PdfPTable createInvoiceInfo(Invoice invoice, Owner owner) {
		PdfPTable phraseTable = new PdfPTable(2);
		phraseTable.setSpacingBefore(50);
		phraseTable.addCell(listToCell(new String[] {owner.getName(), owner.getSurname(), owner.getPhoneNumber(), owner.getResidence()}));
		phraseTable.addCell(listToCell(new String[] {invoice.getPaymentCode().toString(), invoice.getDate().toString()}));

		phraseTable.setWidthPercentage(100);
		return phraseTable;
	}

	PdfPCell listToCell(String[] content) {
		List list = new List();
		for (String c:
			 content) {
			list.add(new ListItem(c));
		}

		Phrase phrase = new Phrase();
		phrase.add(list);
		// We add this phrase to a cell
		PdfPCell phraseCell = new PdfPCell();
		phraseCell.addElement(phrase);

		return phraseCell;
	}

	private PdfPTable createPriceTable(Invoice invoice) {
		PdfPTable table = new PdfPTable(2);
		table.getDefaultCell().setFixedHeight(30);
		table.setWidthPercentage(100);
		table.setHorizontalAlignment(Element.ALIGN_CENTER);


		table.addCell(new Paragraph("Description",chapterFont));
		table.addCell(new Paragraph("Amount", chapterFont));

		for (int i = 0 ; i < 4 ; i++) {
			table.addCell("Random description");
			table.addCell("0.00");
		}

		table.addCell("total");
		table.addCell(Double.toString(invoice.getPriceTotal()));


		return table;
	}

	private Paragraph createPaymentText(Invoice invoice) {
		Paragraph paragraph = new Paragraph("Please make sure to pay with invoice code " + invoice.getPaymentCode());
		paragraph.setAlignment(Element.ALIGN_BOTTOM);
		return paragraph;
	}

	public String convertDate(Date d, String newFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(newFormat);
		return sdf.format(d);
	}
}
