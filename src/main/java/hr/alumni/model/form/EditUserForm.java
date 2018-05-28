package hr.alumni.model.form;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

import hr.alumni.model.User;
import hr.alumni.model.form.validation.PasswordMatches;
import hr.alumni.model.form.validation.ValidEmail;

@Component
@PasswordMatches
public class EditUserForm {

	@NotBlank(message = "{euform.firstName.blank}")
	private String firstName;

	@NotBlank(message = "{rform.lastName.blank}")
	private String lastName;

	@NotBlank(message = "{euform.phone.blank}")
	private String phone;

	@NotBlank(message = "{euform.address.blank}")
	@ValidEmail(message = "{euform.email.invalid}")
	private String email;

	@NotBlank(message = "{euform.address.blank}")
	private String address;

	@NotBlank(message = "{euform.address.empty}")
	private String password;

	@NotBlank(message = "{euform.address.empty}")
	private String passwordConfirm;

	public String getfirstName() {
		return firstName;
	}

	public void setfirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getlastName() {
		return lastName;
	}

	public void setlastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getpasswordConfirm() {
		return passwordConfirm;
	}

	public void setpasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public boolean hasChanges(User user) {
		return !firstName.equals(user.getFirstName())
				|| !lastName.equals(user.getLastName())
				|| !phone.equals(user.getPhone())
				|| !email.equals(user.getEmail())
				|| !address.equals(user.getAddress())
				|| !password.equals(user.getPassword());
	}
}
