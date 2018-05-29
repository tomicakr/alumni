package hr.alumni.model.form;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.stereotype.Component;

import hr.alumni.model.form.validation.PasswordMatches;
import hr.alumni.model.form.validation.ValidEmail;

@Component
@PasswordMatches
public class RegistrationForm {

	@NotBlank(message = "{user.firstName.blank}")
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String firstName;

	@NotBlank(message = "{user.lastName.blank}")
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String lastName;

	@NotBlank(message = "{user.phone.blank}")
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String phone;

	@NotBlank(message = "{user.email.blank}")
	@ValidEmail(message = "{user.email.invalid}")
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String email;

	@NotBlank(message = "{user.address.blank}")
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String address;

	@Size(min = 6, max = 30, message = "{user.password.notfit}")
	@NotBlank(message = "{user.password.blank}")
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String password;

	@Size(min = 6, max = 30, message = "{user.passwordConfirm.notfit}") 
	@NotBlank(message = "{user.passwordConfirm.blank}")
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String passwordConfirm;

	@NotBlank(message = "{user.graduation.blank}")
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String graduation;

	@NotBlank(message = "{user.birthday.blank}")
	@SafeHtml(whitelistType = WhiteListType.NONE)
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
