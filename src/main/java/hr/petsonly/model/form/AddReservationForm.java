package hr.petsonly.model.form;

import hr.petsonly.model.Reservation;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Component
public class AddReservationForm {

	private UUID owner;

	@NotNull
	private UUID service;

	@NotNull
	private UUID pet;

	private UUID preferedEmployee;

	@NotNull
	@NotEmpty(message = "{arform.executiontime.empty}")
	private String executionTime;

	@NotNull
	private String duration;

	@NotNull
	@NotEmpty(message = "{arform.sendreminder.empty}")
	private String sendReminder;

	public UUID getOwner() {
		return owner;
	}

	public void setOwner(UUID owner) {
		this.owner = owner;
	}

	public UUID getService() {
		return service;
	}

	public void setService(UUID service) {
		this.service = service;
	}

	public UUID getPet() {
		return pet;
	}

	public void setPet(UUID pet) {
		this.pet = pet;
	}

	public UUID getPreferedEmployee() {
		return preferedEmployee;
	}

	public void setPreferecEmployee(UUID employee) {
		this.preferedEmployee = employee;
	}

	public String getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(String executionTime) {
		this.executionTime = executionTime;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getSendReminder() {
		return sendReminder;
	}

	public void setSendReminder(String sendReminder) {
		this.sendReminder = sendReminder;
	}

	public boolean hasChanges(Reservation res) {
		return false;
	}

}
