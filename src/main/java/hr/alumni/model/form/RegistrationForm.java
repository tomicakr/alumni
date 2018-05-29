package hr.alumni.model.form;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

import hr.alumni.model.form.validation.PasswordMatches;
import hr.alumni.model.form.validation.ValidEmail;

@Component
@PasswordMatches
public class RegistrationForm {

	@NotBlank(message = "{rform.firstName.blank}")
	private String firstName;

	@NotBlank(message = "{rform.lastName.blank}")
	private String lastName;

	@NotBlank(message = "{rform.phone.blank}")
	private String phone;

	@NotBlank(message = "{rform.email.blank}")
	@ValidEmail(message = "{rform.email.invalid}")
	private String email;

	@NotBlank(message = "{rform.address.blank}")
	private String address;

	@Size(min = 8, max = 30, message = "{rform.password.notfit}")
	@NotBlank(message = "{rform.password.blank}")
	private String password;

	@Size(min = 8, max = 30, message = "{rform.passwordConfirm.notfit}") 
	@NotBlank(message = "{rform.passwordConfirm.blank}")
	private String passwordConfirm;

	@NotBlank(message = "{rform.graduation.blank}")
	private String graduation;

	@NotBlank(message = "{rform.birthday.blank}")
	private String birthday;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
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

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String getGraduation() {
		return graduation;
	}

	public void setGraduation(String graduation) {
		this.graduation = graduation;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
}
