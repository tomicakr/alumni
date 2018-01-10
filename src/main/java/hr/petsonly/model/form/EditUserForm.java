package hr.petsonly.model.form;

import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import hr.petsonly.model.User;
import hr.petsonly.model.form.validation.PasswordMatches;
import lombok.Data;

@Component
@PasswordMatches
@Data
public class EditUserForm {

	@NotNull
	@NotEmpty
	private String mobilePhone;

	@NotNull
	private UUID location;

	@NotNull
	@NotEmpty
	private String oldPassword;

	@NotNull
	@NotEmpty
	@Size(min = 6, max = 30)
	private String password;

	@NotNull
	@NotEmpty
	private String password2;

	public boolean hasChanges(User user) {
		if (user.getLocation().getLocationId().equals(location) && user.getMobilePhone().equals(mobilePhone)
				&& user.getPassword().equals(password)) {
			return false;
		}

		return true;
	}
}
