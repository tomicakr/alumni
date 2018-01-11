package hr.petsonly.model.form;

import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

	@NotNull
	@NotEmpty(message = "{euform.oldpassword.empty}")
	private String oldPassword;

	@NotNull
	@NotEmpty(message = "{euform.password.empty}")
	@Size(min = 6, max = 30, message = "{euform.password.invalid}")
	private String password;

	@NotNull
	@NotEmpty(message = "{euform.password2.empty}")
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
