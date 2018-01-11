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
	@NotEmpty(message = "{rform.firstname.empty}")
	private String name;

	@NotNull
	@NotEmpty(message = "{rform.lastname.empty}")
	private String surname;

	@NotNull
	@NotEmpty(message = "{rform.oib.empty}")
	private String userPid;

	@NotNull
	@NotEmpty(message = "{rform.mobilephone.empty}")
	private String mobilePhone;

	@NotNull
	@NotEmpty(message = "{rform.phone.empty}")
	private String phone;

	@NotNull
	@ValidEmail(message = "{rform.email.invalid}")
	private String email;

	@NotNull
	private UUID location;

	@NotNull
	@NotEmpty(message = "{rform.address.empty}")
	private String address;

	@NotNull
	@NotEmpty(message = "{rform.password.empty}")
	private String password;

	@NotNull
	@NotEmpty(message = "{rform.password2.empty}")
	private String password2;

}
