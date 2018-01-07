package hr.petsonly.model.form;

import javax.validation.constraints.NotNull;

public class AddReservationForm {
	
	@NotNull
	private String service;
	
	@NotNull
	private String pet;
	
	@NotNull
	private String user;
	
	private String employee;
	
	@NotNull
	private String executionTime;
	
	@NotNull
	private double duration;
		
	@NotNull
	private String sendReservation;

	public AddReservationForm(String service, String pet, String user, String employee, String executionTime,
			double duration, String sendReservation) {
		super();
		this.service = service;
		this.pet = pet;
		this.user = user;
		this.employee = employee;
		this.executionTime = executionTime;
		this.duration = duration;
		this.sendReservation = sendReservation;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getPet() {
		return pet;
	}

	public void setPet(String pet) {
		this.pet = pet;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getEmployee() {
		return employee;
	}

	public void setEmployee(String employee) {
		this.employee = employee;
	}

	public String getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(String executionTime) {
		this.executionTime = executionTime;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public String getSendReservation() {
		return sendReservation;
	}

	public void setSendReservation(String sendReservation) {
		this.sendReservation = sendReservation;
	}
	
	
}
