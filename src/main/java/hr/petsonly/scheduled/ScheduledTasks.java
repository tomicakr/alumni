package hr.petsonly.scheduled;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import hr.petsonly.model.Reservation;
import hr.petsonly.model.User;
import hr.petsonly.repository.ReservationRepository;
import hr.petsonly.service.email.EmailServiceImpl;

@Component
public class ScheduledTasks {
	
	final Integer CONFIRMED_RESERVATION_STATUS = 3;
	final Integer CONFIRMED_RESERVATION_REMINDER_TIME = 5;
	final String DEFAULT_REMINDER_EMAIL_SUBJECT = "Podsjetnik na rezervaciju";
	
	@Autowired
	EmailServiceImpl emailServiceImpl;
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Transactional
	@Scheduled(fixedRate = 3600000)
	public void sendReservationReminder() {
		
		List<Reservation> reservationList = reservationRepository.findAllByStatusAndWithinNHours(CONFIRMED_RESERVATION_STATUS,
															CONFIRMED_RESERVATION_REMINDER_TIME);
		for(Reservation reservation : reservationList) {
			StringBuilder stringBuilder = new StringBuilder();
			String userName = reservation.getUser().getName();
			LocalDateTime executionTime = reservation.getExecutionTime();
			
	
			
			String petName = reservation.getPet().getName();
			String serviceName = reservation.getService().getName();
			
			String dateString = getDateStringForLocalDateTime(executionTime);
			
			stringBuilder.append("Poštovani " + userName + ",\n")
			.append("Podsjećamo Vas da Vam je " + dateString + "zakazana usluga " + serviceName)
			.append(" za ljubimca " + petName + ".\n")
			.append("Vaš PetsOnlyZg");

			String emailBody = stringBuilder.toString();
			
			
			String userEmail = reservation.getUser().getEmail();
			emailServiceImpl.sendSimpleMessage(userEmail, DEFAULT_REMINDER_EMAIL_SUBJECT, emailBody);
		}
		
	}
	
	private String getDateStringForLocalDateTime (LocalDateTime executionTime) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(executionTime.getDayOfMonth()+"."+executionTime.getMonthValue()+"."+executionTime.getYear() + ". ")
		.append("godine u " + executionTime.getHour() + ":");
		String minuteString = Integer.toString(executionTime.getMinute());
		minuteString = minuteString.length()==1? "0" + minuteString : minuteString;
		stringBuilder.append(minuteString + "h ");
		
		String dateString = stringBuilder.toString();
		
		return dateString;
	}
	
}
