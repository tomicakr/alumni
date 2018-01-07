package hr.petsonly.model.details;

import java.util.UUID;

import org.springframework.stereotype.Component;

import hr.petsonly.model.Reservation;

@Component
public class ReservationDetails {

	private UUID reservationId;
	private String pet;
	private String service;
	private String employee;
	private String status;
	private String time;

	public ReservationDetails() {
	}

	public ReservationDetails(Reservation reservation) {

		this.setReservationId(reservation.getReservationKey());
		this.pet = reservation.getPet().getName();
		this.service = reservation.getService().getName(); 
		this.employee = reservation.getEmployee() == null ? null :reservation.getEmployee().getName() + " " + reservation.getEmployee().getSurname();
		this.time = reservation.getReservationTime().toString();

		switch (reservation.getReservationStatus()) {
		case 1:
			this.status = "Otvorena";
			break;
		case 2:
			this.status = "Prihvaćena";
			break;
		case 3:
			this.status = "Potvrđena";
			break;
		default:
			this.status = "<unknown>";
			break;
		}
	}

	public String getPet() {
		return pet;
	}

	public void setPet(String pet) {
		this.pet = pet;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getEmployee() {
		return employee;
	}

	public void setEmployee(String employee) {
		this.employee = employee;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public UUID getReservationId() {
		return reservationId;
	}

	public void setReservationId(UUID reservationId) {
		this.reservationId = reservationId;
	}

}
