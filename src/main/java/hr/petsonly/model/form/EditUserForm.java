package hr.petsonly.model.form;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import hr.petsonly.model.User;
import hr.petsonly.model.form.validation.PasswordMatches;

@Component
@PasswordMatches
public class EditUserForm {

	@NotNull
	@NotEmpty(message = "{euform.mobilephone.empty}")
	private String mobilePhone;

	@NotNull
	private UUID location;

	private String oldPassword;

	private String password;

	private String password2;

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public UUID getLocation() {
		return location;
	}

	public void setLocation(UUID location) {
		this.location = location;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public boolean hasChanges(User user) {
		if (user.getLocation().getLocationId().equals(location) && user.getMobilePhone().equals(mobilePhone)
				&& user.getPassword().equals(password)) {
			return false;
		}

		return true;
	}
}
