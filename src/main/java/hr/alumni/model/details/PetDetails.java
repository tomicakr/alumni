package hr.alumni.model.details;

import org.springframework.stereotype.Component;

import hr.alumni.model.Pet;

import java.util.UUID;

@Component
public class PetDetails {

	private UUID petId;
	private String name;
	private int age;
	private String species;
	private char sex;
	private String microchip;
	private String remark;

	public PetDetails() {
	}

	public PetDetails(Pet pet) {
		this.petId = pet.getPetKey();
		this.name = pet.getName();
		this.age = pet.getAge();
		this.species = pet.getSpecies().getName();
		this.sex = pet.getSex();
		this.microchip = pet.getMicrochip();
		this.remark = pet.getRemark();

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

	public UUID getPetId() {
		return petId;
	}

	public void setPetId(UUID petId) {
		this.petId = petId;
	}
}
