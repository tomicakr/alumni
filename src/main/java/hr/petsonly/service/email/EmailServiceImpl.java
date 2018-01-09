package hr.petsonly.service.email;

import java.nio.file.Paths;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import hr.petsonly.model.Reservation;
import hr.petsonly.model.form.AddReservationForm;

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
			MimeMessagePreparator prep = this.getContentWtihAttachementMessagePreparator(reservation);
			mailSender.send(prep);
		} catch (MailException exception) {
			exception.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private MimeMessagePreparator getContentWtihAttachementMessagePreparator(final Reservation reservation) {

		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		
				helper.setSubject("PetsOnlyZg rezervacija");
				helper.setFrom("fau53t7zss@gmail.com");
				helper.setTo("filip.sodic@fer.hr"); //reservation.getUser().getEmail();
				String content = "Dear " + reservation.getUser().getName() 
						+ ",\nthank you for your reservation. Your reservation id is " + reservation.getReservationKey() + ".";

				helper.setText(content);
				// Add a resource as an attachment	
			
				helper.addAttachment("Ponuda.pdf", Paths.get("./src/main/resources/templates/Informacije_o_projektnom_zadatku.pdf").toFile());
				
			}
		};
		return preparator;
	}
}
