package hr.petsonly.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "reservation")
public class Reservation {

	@Id
	@Column
	@Type(type="uuid-char")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID reservationKey;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "serviceKey")
	private Service service;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "petKey")
	private Pet pet;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private User user;

	@Column
	private int reservationStatus;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", insertable = false, updatable = false)
	private User employee;

	@Column
	private LocalDateTime reservationTime;
	@Column
	private LocalDateTime executionTime;
	@Column
	private double duration;
	@Column
	private double price;
	@Column
	private boolean sendReminder;
	@Column
	private String documentPath;

	public Reservation() {
	}

	public UUID getReservationKey() {
		return reservationKey;
	}

	public void setReservationKey(UUID reservationKey) {
		this.reservationKey = reservationKey;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getReservationStatus() {
		return reservationStatus;
	}

	public void setReservationStatus(int reservationStatus) {
		this.reservationStatus = reservationStatus;
	}

	public User getEmployee() {
		return employee;
	}

	public void setEmployee(User employee) {
		this.employee = employee;
	}

	public LocalDateTime getReservationTime() {
		return reservationTime;
	}

	public void setReservationTime(LocalDateTime reservationTime) {
		this.reservationTime = reservationTime;
	}

	public LocalDateTime getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(LocalDateTime executionTime) {
		this.executionTime = executionTime;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isSendReminder() {
		return sendReminder;
	}

	public void setSendReminder(boolean sendReminder) {
		this.sendReminder = sendReminder;
	}

	public String getDocumentPath() {
		return documentPath;
	}

	public void setDocumentPath(String documentPath) {
		this.documentPath = documentPath;
	}

}
