package hr.petsonly.model.form;

import javax.validation.constraints.NotNull;

public class PetForm {
	
	@NotNull
	private String name;
	
	@NotNull
	private String age;
	
	@NotNull
	private String species;
	
	@NotNull
	private String breed;
	
	@NotNull
	private String microchip;
	
	@NotNull
	private String remark;

	public PetForm() {
		super();
	}

	public PetForm(String name, String age, String species, String breed, String microchip, String remark) {
		super();
		this.name = name;
		this.age = age;
		this.species = species;
		this.breed = breed;
		this.microchip = microchip;
		this.remark = remark;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
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
	

}
