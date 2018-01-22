package hr.petsonly.model.details;

import hr.petsonly.model.Reservation;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component
public class ReservationDetails {

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm");

	private UUID reservationId;
	private String pet;
	private String petSpecies;
	private String service;
	private String employee;
	private String status;
	private String time;
	private String owner;

	public ReservationDetails() {
	}

	public ReservationDetails(Reservation reservation) {

		this.setReservationId(reservation.getReservationKey());
		this.pet = reservation.getPet().getName();
		this.petSpecies = reservation.getPet().getSpecies().getName();
		this.service = reservation.getService().getName(); 
		this.employee = reservation.getEmployee() == null ? "" :reservation.getEmployee().getName() + " " + reservation.getEmployee().getSurname();
		this.time = reservation.getExecutionTime().format(formatter);
		this.owner = reservation.getUser().getName() + " " + reservation.getUser().getSurname();
		switch (reservation.getReservationStatus()) {
		case PENDING:
			this.status = "Otvorena";
			break;
		case ACCEPTED:
			this.status = "Čeka se uplata";
			break;
		case CONFIRMED:
			this.status = "Uplaćena";
			break;
		case ARCHIVED:
			this.status = "Gotova";
			break;
		default:
			this.status = "<unknown>";
			break;
		}
	}

	public String getPetSpecies() {
		return petSpecies;
	}

	public void setPetSpecies(String petSpecies) {
		this.petSpecies = petSpecies;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
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
