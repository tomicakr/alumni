package hr.petsonly.model.form;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import lombok.Data;


@Component
@Data
public class PetForm {

	@NotNull
	@NotEmpty(message = "{pform.name.empty}")
	private String name;

	@NotNull
	@NotEmpty(message = "{pform.age.empty}")
	private Integer age;

	@NotNull
	@NotEmpty(message = "{pform.sex.empty}")
	private Character sex;

	@NotNull
	@NotEmpty(message = "{pform.species.empty}")
	private String species;

	@NotNull
	@NotEmpty(message = "{pform.breed.empty}")
	private String breed;

	@NotNull
	@NotEmpty(message = "{pform.microchip.empty}")
	private String microchip;

	@NotNull
	@NotEmpty(message = "{pform.remark.empty}")
	private String remark;

	@NotNull
	@NotEmpty(message = "{pform.owner.empty}")
	private String owner;

}
