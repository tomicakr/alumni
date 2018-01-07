package hr.petsonly.model.form;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Component
public class PetForm {
	
	@NotNull
	private String name;
	
	@NotNull
	private Integer age;
	
	@NotNull 
	private Character sex;
	
	@NotNull
	private String species;
	
	@NotNull
	private String breed;
	
	@NotNull
	private String microchip;
	
	@NotNull
	private String remark;
	
	
	
	@NotNull
	private String owner;
	
	public PetForm() {
		super();
	}

	public PetForm(String name, Integer age, String species, String breed, String microchip, String remark, Character sex, String owner) {
		super();
		this.name = name;
		this.age = age;
		this.species = species;
		this.breed = breed;
		this.microchip = microchip;
		this.remark = remark;
		this.sex = sex;
		this.owner = owner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
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
	
	public Character getSex(){
		return sex;
	}
	
	public void setSex(Character sex){
		this.sex = sex;
	}
	
	public String getOwner(){
		return owner;
	}
	
	public void setOwner(String owner){
		this.owner = owner;
	}
	

}
