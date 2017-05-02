package com.gamma.dal.util;

import javassist.bytecode.stackmap.TypeData;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

/**
 * Created by frank on 28/03/2017.
 */
public class SendMailTLS {

	private static final Logger LOGGER = Logger.getLogger( TypeData.ClassName.class.getName());

	final static String username = "ticketsysteem.mediamarkt@gmail.com";
	final static String password = "123Welkom";

	/**
	 * Send a mail with a give attachement
	 * @param subject The subject of the email
	 * @param content The content of the mail
	 * @param pdfName The name of the pdf attachement
	 * @param address The target email address
	 */
	public static void sendMail(ByteArrayOutputStream outputStream, String subject, String content, String pdfName, String address) {

		Properties props = getProperties();
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		Multipart attachement = createAttachement(outputStream, pdfName, content);
		transportMail(session, attachement, subject, address);
	}

	private static Properties getProperties() {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		return props;
	}

	private static Multipart createAttachement(ByteArrayOutputStream outputStream, String pdfName, String text) {
		// Create a multipar message
		Multipart multipart = new MimeMultipart();

		try {
			BodyPart messageBodyPart = new MimeBodyPart();
			// Now set the actual message
			messageBodyPart.setText(text);
			// Set text message part
			multipart.addBodyPart(messageBodyPart);
			// Part two is attachment
			messageBodyPart = new MimeBodyPart();

			byte[] bytes = outputStream.toByteArray();
			DataSource source = new ByteArrayDataSource(bytes, "application/pdf");

			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(pdfName);
			multipart.addBodyPart(messageBodyPart);
		}

		catch (MessagingException e) {
			LOGGER.log(Level.FINE, "Failed to create attachement");
		}

		return multipart;

	}

	private static void transportMail(Session session, Multipart attachement, String subject, String address) {
		try {
			Message message = new MimeMessage(session);
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(address));
			message.setSubject(subject);

			// Send the complete message parts
			message.setContent(attachement);

			Transport.send(message);

			LOGGER.log(Level.FINE, "Mail has been send to {0}", address);

		} catch (MessagingException e) {
			LOGGER.log(Level.FINE, "Failed to send mail to {0}", address);
			throw new RuntimeException(e);
		}
	}
}
