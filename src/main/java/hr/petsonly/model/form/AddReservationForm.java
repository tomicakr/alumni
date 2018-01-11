package hr.petsonly.model.form;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import hr.petsonly.model.Reservation;
import lombok.Data;

@Component
@Data
public class AddReservationForm {

	@NotNull
	private UUID owner;

	@NotNull
	@NotEmpty(message = "{arform.service.empty}")
	private String service;

	@NotNull
	private UUID pet;

	@NotNull
	private UUID employee;

	@NotNull
	@NotEmpty(message = "{arform.executiontime.empty}")
	private String executionTime;
	
	@NotNull
	private String duration;

	@NotNull
	@NotEmpty(message = "{arform.sendreminder.empty}")
	private String sendReminder;

	public boolean hasChanges(Reservation res) {
		return false;
	}

}
