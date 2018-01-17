package hr.petsonly.model.form;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import hr.petsonly.model.form.validation.ValidSpecies;

@Component
public class PetForm {

	@NotNull
	@NotEmpty(message = "{pform.name.empty}")
	private String name;

	@NotNull(message = "{pform.age.null}")
	private Integer age;

	@NotNull(message = "{pform.sex.null}")
	private Character sex;

	@NotNull
	@NotEmpty(message = "{pform.species.empty}")
	@ValidSpecies
	private String species;

	@NotNull
	private String microchip;

	private String remark;

	private String owner;

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

	public Character getSex() {
		return sex;
	}

	public void setSex(Character sex) {
		this.sex = sex;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
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

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

}
