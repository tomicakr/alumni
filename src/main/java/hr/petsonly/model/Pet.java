package hr.petsonly.model;

import java.util.List;
import javax.persistence.*;
import java.util.UUID;


@Entity
@Table(name = "pet")
public class Pet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private UUID petKey;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private User owner;
	
	@Column
	private String name;
	
	@Column
	private int age;
	
	@Column
	private String species;
	
	@Column
	private String breed;
	
	@Column
	private char sex;
	
	@Column
	private String microchip;
	
	@Column
	private String remark;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="pet")
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

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
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
