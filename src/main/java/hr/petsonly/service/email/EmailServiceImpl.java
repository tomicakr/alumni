package hr.petsonly.service.email;

import java.io.OutputStream;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import hr.petsonly.model.Reservation;

@Component
public class EmailServiceImpl {

	@Autowired
	JavaMailSender mailSender;

	public void sendSimpleMessage(String to, String subject, String text) {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(to);
			message.setSubject(subject);
			message.setText(text);

			mailSender.send(message);
		} catch (MailException exception) {
			exception.printStackTrace();
		}
	}

	public void sendReservationOffer(Reservation reservation) {
		try {
			MimeMessagePreparator prep = this.prepareReservationOfferEmail(reservation);
			mailSender.send(prep);
		} catch (MailException exception) {
			exception.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * private MimeMessagePreparator prepareReservationOfferEmail(final
	 * Reservation reservation) {
	 * 
	 * MimeMessagePreparator preparator = new MimeMessagePreparator() {
	 * 
	 * public void prepare(MimeMessage mimeMessage) throws Exception {
	 * MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
	 * 
	 * helper.setSubject("PetsOnlyZg rezervacija");
	 * helper.setFrom("fau53t7zss@gmail.com");
	 * helper.setTo("mate.paulinovic@fer.hr");//reservation.getUser().getEmail()
	 * ); String content = "Dear " + reservation.getUser().getName() +
	 * ",\nthank you for your reservation. Your reservation id is " +
	 * reservation.getReservationKey() + ".";
	 * 
	 * helper.setText(content); // Add a resource as an attachment
	 * 
	 * helper.addAttachment("Ponuda.pdf",
	 * Paths.get("./src/main/resources/templates/turtle.html").toFile());
	 * 
	 * } }; return preparator; }
	 */

	private void writePdf(OutputStream outputStream, Reservation reservation) throws Exception {
		// IText API
		PdfWriter pdfWriter = new PdfWriter(outputStream);
		PdfDocument pdf = new PdfDocument(pdfWriter);
		Document pdfDocument = new Document(pdf);
		// title
		Paragraph title = new Paragraph(reservation.getUser().getName());
		title.setFont(PdfFontFactory.createFont(FontConstants.HELVETICA));
		title.setFontSize(18f);
		title.setItalic();
		pdfDocument.add(title);
		// date
		Paragraph date = new Paragraph(
				reservation.getExecutionTime().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));
		date.setFontSize(16f);
		pdfDocument.add(date);
		// content
		Paragraph content = new Paragraph(reservation.getService().getName());
		pdfDocument.add(content);
		pdfDocument.close();
	}

	private MimeMessagePreparator prepareReservationOfferEmail(final Reservation reservation) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

				helper.setSubject("PetsOnlyZg rezervacija");
				helper.setFrom("fau53t7zss@gmail.com");
				helper.setTo("mate.paulinovic@fer.hr"); //reservation.getUser().getEmail());
				String content = "Dear " + reservation.getUser().getName()
						+ ",\nthank you for your reservation. Your reservation id is " + reservation.getReservationKey()
						+ ".";
				// helper.setText(content);
				// Add a resource as an attachment
				MimeBodyPart textBodyPart = new MimeBodyPart();
				textBodyPart.setText(content);
				ByteArrayOutputStream os = null;
				try {
					os = new ByteArrayOutputStream();
					writePdf(os, reservation);
					byte[] bytes = os.toByteArray();

					// construct the pdf body part
					DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
					MimeBodyPart pdfBodyPart = new MimeBodyPart();
					pdfBodyPart.setDataHandler(new DataHandler(dataSource));
					pdfBodyPart.setFileName("Ponuda.pdf");
					MimeMultipart mimeMultipart = new MimeMultipart();
					mimeMultipart.addBodyPart(textBodyPart);
					mimeMultipart.addBodyPart(pdfBodyPart);
					mimeMessage.setContent(mimeMultipart);

				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					// clean off
					if (null != os) {
						try {
							os.close();
							os = null;
						} catch (Exception ex) {
						}
					}
				}

				// helper.addAttachment("Ponuda.pdf",
				// Paths.get("./src/main/resources/turtle.html").toFile());

			}
		};

		return preparator;
	}
}
