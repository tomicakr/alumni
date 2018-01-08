package hr.petsonly.model.form;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Component
public class AddReservationForm {

	@NotNull
	private UUID owner;

	@NotNull
	private String service;

	@NotNull
	private UUID pet;

	private UUID employee;

	@NotNull
	private String executionTime;

	@NotNull
	private String duration;

	private String sendReminder;

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

	public UUID getOwner() {
		return owner;
	}

	public void setOwner(UUID owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "AddReservationForm [service=" + service + ", pet=" + pet + ", owner=" + owner + ", employee=" + employee + ", executionTime="
				+ executionTime + ", duration=" + duration + ", sendReminder=" + sendReminder + "]";
	}

}
