package com.gamma.invoice;

import com.gamma.dal.entities.Invoice;
import com.gamma.dal.entities.Owner;
import com.itextpdf.text.*;
import com.itextpdf.text.List;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by frank on 21/03/2017.
 */
public class InvoiceGenerator {

	static Font chapterFont = FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLDITALIC);
	static Font paragraphFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL);

	/**
	 * Create a invoice in pdf format based on invoice and user data
	 * @param invoice The invoice data
	 * @param owner The owner of the invoice
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static ByteArrayOutputStream createInvoice(Invoice invoice, Owner owner) throws IOException, DocumentException {
		Document document = new Document(PageSize.A4);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PdfWriter.getInstance(document, outputStream);
		document.open();

		// Add a title to the the invoice
		document.add(createParagraph("Bill driver invoice" + invoice.getUuid() + " " + invoice.getDate()));
		document.add(createParagraph(convertDate(invoice.getDate(), "MMM dd, yyyy")));

		// Add info about the owner of the invoice
		document.add(createInvoiceInfo(invoice, owner));

		document.add(createParagraph(""));

		// Create the price table of the invoice
		document.add(createPriceTable(invoice));
		document.add(createPaymentText(invoice));

		document.close();

		return outputStream;
	}

	/**
	 * Create a new paragraph
	 * @param content
	 * @return
	 */
	private static Paragraph createParagraph(String content) {
		Paragraph paragraph = new Paragraph(content, paragraphFont);
		paragraph.setAlignment(Element.ALIGN_LEFT);
		return paragraph;
	}

	/**
	 * Create the section that show info about the invoice
	 * @param invoice
	 * @param owner
	 * @return
	 */
	private static PdfPTable createInvoiceInfo(Invoice invoice, Owner owner) {
		PdfPTable phraseTable = new PdfPTable(3);
		phraseTable.setSpacingBefore(50);
		phraseTable.addCell(listToCell(new String[] {owner.getName(), owner.getSurname(), owner.getPhoneNumber(), owner.getResidence()}));
		phraseTable.addCell(listToCell(new String[] {invoice.getPaymentCode().toString(), invoice.getDate().toString()}));
		phraseTable.addCell(listToCell(new String[] {"Guildhall, PO Box 270", "London EC2P 2EJ"}));

		phraseTable.setWidthPercentage(100);
		return phraseTable;
	}

	/**
	 * Convert a list to a cell
	 * @param content
	 * @return
	 */
	private  static PdfPCell listToCell(String[] content) {
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

	/**
	 * Create the section that shows the price of the invoice
	 * @param invoice
	 * @return
	 */
	private static PdfPTable createPriceTable(Invoice invoice) {
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

	private static Paragraph createPaymentText(Invoice invoice) {
		Paragraph paragraph = new Paragraph("Please make sure to pay with invoice code " + invoice.getPaymentCode());
		paragraph.setAlignment(Element.ALIGN_BOTTOM);
		return paragraph;
	}

	public static String convertDate(Date d, String newFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(newFormat);
		return sdf.format(d);
	}
}
