package hr.alumni.service.email;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import hr.alumni.model.Post;
import hr.alumni.model.User;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import java.awt.Font;
import java.io.OutputStream;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Properties;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Component
public class EmailServiceImpl {

	@Autowired
	JavaMailSender mailSender;

	public void sendSimpleMessage(String to, String subject, Post post) {

		// sets SMTP server properties
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", 587);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.mime.charset", "utf-8");

		// creates a new session with an authenticator
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("no.reply.alumni@gmail.com", "alumni123");
			}
		};

		Session session = Session.getInstance(properties, auth);

		// creates a new e-mail message
		MimeMessage msg = new MimeMessage(session);

		try {
			msg.setFrom(new InternetAddress("no.reply.alumni@gmail.com"));
			InternetAddress[] toAddresses = { new InternetAddress(to) };
			msg.setRecipients(Message.RecipientType.TO, toAddresses);
			msg.setSubject(subject, "UTF-8");
			msg.setSentDate(new Date());
			msg.setContent(generirajMailText(post), "text/html;charset=\"utf-8\"");

			// sends the e-mail
			Transport.send(msg);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private String generirajMailText(Post post) {
		String text = "";

		text += "Adresa: " + post.getAddress() + "<br><hr>";
		text += "Kratki opis: " + post.getShortDescription() + "<br><hr>";
		text += "Kategorije: ";
		for (int i = 0; i < post.getPostCategories().size(); i++) {
			text += post.getPostCategories().get(i).getName() + " ";
		}
		text+= "<br><hr>";
		text += "Dugi opis: " + post.getLongDescription() + "<br>";

		return text;
	}
}
