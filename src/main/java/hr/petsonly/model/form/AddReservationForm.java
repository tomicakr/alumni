package hr.petsonly.model.form;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import hr.petsonly.model.Reservation;

@Component
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

	public UUID getOwner() {
		return owner;
	}

	public void setOwner(UUID owner) {
		this.owner = owner;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public UUID getPet() {
		return pet;
	}

	public void setPet(UUID pet) {
		this.pet = pet;
	}

	public UUID getEmployee() {
		return employee;
	}

	public void setEmployee(UUID employee) {
		this.employee = employee;
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
