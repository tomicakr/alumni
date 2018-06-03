package hr.alumni.service.email;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import hr.alumni.model.Post;

@Component
public class EmailServiceImpl {

	@Autowired
	JavaMailSender mailSender;

	public void sendSimpleMessage(String to, String subject, Post post) {

		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", 587);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.mime.charset", "utf-8");

		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("no.reply.alumni@gmail.com", "alumni123");
			}
		};

		Session session = Session.getInstance(properties, auth);

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
