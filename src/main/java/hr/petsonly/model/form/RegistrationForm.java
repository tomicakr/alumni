package hr.petsonly.model.form;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import hr.petsonly.model.form.validation.PasswordMatches;
import hr.petsonly.model.form.validation.ValidEmail;
import lombok.Data;

@Component
@PasswordMatches
@Data
public class RegistrationForm {

	@NotNull
	@NotEmpty
	private String name;

	@NotNull
	@NotEmpty
	private String surname;

	@NotNull
	@NotEmpty
	private String userPid;

	@NotNull
	@NotEmpty
	private String mobilePhone;

	@NotNull
	@NotEmpty
	private String phone;

	@NotNull
	@ValidEmail
	private String email;

	@NotNull
	private UUID location;

	@NotNull
	@NotEmpty
	private String address;

	@NotNull
	@NotEmpty
	private String password;

	@NotNull
	@NotEmpty
	private String password2;

}
