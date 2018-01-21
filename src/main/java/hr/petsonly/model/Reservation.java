package hr.petsonly.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Transactional
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
	@JoinColumn(name = "ownerId")
	private User user;

	@Column
	private ReservationStatus reservationStatus;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employeeId")
	private User employee;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "preferedEmployeeId")
	private User preferedEmployee;


	@Column
	private LocalDateTime reservationTime;
	@Column
	private LocalDateTime executionTime;
	@Column
	private Duration duration;
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

	public ReservationStatus getReservationStatus() {
		return reservationStatus;
	}

	public void setReservationStatus(ReservationStatus reservationStatus) {
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

	public Duration getDuration() {
		return duration;
	}

	public void setDuration(Duration duration) {
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

	public User getPreferedEmployee() {
		return preferedEmployee;
	}

	public void setPreferedEmployee(User preferedEmployee) {
		this.preferedEmployee = preferedEmployee;
	}
	
}
