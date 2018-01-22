package hr.petsonly.model.form;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class EditReservationForm {

	@NotNull
	@NotEmpty(message = "{arform.service.empty}")
	private String service;
	
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

	public EditReservationForm() {
		super();
	}

	public EditReservationForm(String service, UUID employee, String executionTime, String duration,
			String sendReminder) {
		super();
		this.service = service;
		this.employee = employee;
		this.executionTime = executionTime;
		this.duration = duration;
		this.sendReminder = sendReminder;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
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
	
	
	
	
}
