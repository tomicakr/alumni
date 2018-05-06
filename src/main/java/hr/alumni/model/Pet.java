package hr.alumni.model;

import java.util.List;
import javax.persistence.*;

import org.hibernate.annotations.Type;

import java.util.UUID;


@Entity
@Table(name = "pet")
public class Pet {

	@Id
	@Column
	@Type(type = "uuid-char")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID petKey;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private User owner;
	
	@Column
	private String name;
	
	@Column
	private int age;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "species_id", nullable = false)
	private Species species;
	
	@Column
	private char sex;
	
	@Column
	private String microchip;
	
	@Column
	private String remark;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pet", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Reservation> reservations;
	
	public Pet() {
	}

	public UUID getPetKey() {
		return petKey;
	}

	public void setPetKey(UUID petKey) {
		this.petKey = petKey;
	}

	
	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Species getSpecies() {
		return species;
	}

	public void setSpecies(Species species) {
		this.species = species;
	}

	public char getSex() {
		return sex;
	}

	public void setSex(char sex) {
		this.sex = sex;
	}

	public String getMicrochip() {
		return microchip;
	}

	public void setMicrochip(String microchip) {
		this.microchip = microchip;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

}
