package hr.alumni.model;

import java.util.UUID;
import java.util.List;
import javax.persistence.*;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "service")
public class Service {

	@Id
	@Type(type="uuid-char")
	@Column
	private UUID serviceKey;
	@Column
	private String name;
	@Column
	private double price;
	@Column
	private String description;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "service", cascade=CascadeType.ALL, orphanRemoval = true)
	private List<Reservation> reservations;

	public Service() {
	}

	public UUID getServiceKey() {
		return serviceKey;
	}

	public void setServiceKey(UUID serviceKey) {
		this.serviceKey = serviceKey;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

}
