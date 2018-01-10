package hr.petsonly.model.form;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import hr.petsonly.model.Reservation;
import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class AddReservationForm {

	@NotNull
	private UUID owner;

	@NotNull
	private String service;

	@NotNull
	private UUID pet;

	@NotNull
	private UUID employee;

	@NotNull
	private String executionTime;

	@NotNull
	private String duration;

	private String sendReminder;


	public boolean hasChanges(Reservation res) {
		// TODO Auto-generated method stub
		return false;
	}

}
