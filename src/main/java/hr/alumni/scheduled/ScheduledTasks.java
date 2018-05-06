package hr.alumni.scheduled;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import hr.alumni.model.Reservation;
import hr.alumni.repository.ReservationRepository;
import hr.alumni.service.email.EmailServiceImpl;

@Component
public class ScheduledTasks {
	
	final long CONFIRMED_RESERVATION_REMINDER_TIME = 5;
	final long CONFIRMED_RESERVATION_INTERVAL_MINUTES = 15;
	final long MILISECONDS_IN_A_MINUTE = 60000;
	final String DEFAULT_REMINDER_EMAIL_SUBJECT = "Podsjetnik na rezervaciju";
	
	@Autowired
	EmailServiceImpl emailServiceImpl;
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Transactional
	@Scheduled(fixedRate = CONFIRMED_RESERVATION_INTERVAL_MINUTES * MILISECONDS_IN_A_MINUTE)
	public void sendReservationReminder() {
		
		List<Reservation> reservationList = reservationRepository.findAllConfirmedWithinNHours(CONFIRMED_RESERVATION_REMINDER_TIME,
															CONFIRMED_RESERVATION_INTERVAL_MINUTES);
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
