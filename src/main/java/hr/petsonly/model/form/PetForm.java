package hr.petsonly.model.form;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;


@Component
@Getter
@Setter
public class PetForm {

	@NotNull
	@NotEmpty
	private String name;

	@NotNull
	@NotEmpty
	private Integer age;

	@NotNull
	@NotEmpty
	private Character sex;

	@NotNull
	@NotEmpty
	private String species;

	@NotNull
	@NotEmpty
	private String breed;

	@NotNull
	@NotEmpty
	private String microchip;

	@NotNull
	@NotEmpty
	private String remark;

	@NotNull
	@NotEmpty
	private String owner;

}
