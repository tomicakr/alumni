package hr.alumni.model.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import hr.alumni.model.form.validation.PasswordMatches;
import hr.alumni.model.form.validation.ValidEmail;

@Component
@PasswordMatches
public class RegistrationForm {

	@NotNull
	@NotEmpty(message = "{rform.firstname.empty}")
	private String name;

	@NotNull
	@NotEmpty(message = "{rform.lastname.empty}")
	private String surname;

	@NotNull
	@NotEmpty(message = "{rform.phone.empty}")
	private String phone;

	@NotNull
	@ValidEmail(message = "{rform.email.invalid}")
	private String email;

	@NotNull
	@NotEmpty(message = "{rform.address.empty}")
	private String address;

	@Size(min = 8, max = 30)
	@NotNull
	@NotEmpty(message = "{rform.password.empty}")
	private String password;

	@Size(min = 8, max = 30)
	@NotNull
	@NotEmpty(message = "{rform.password2.empty}")
	private String password2;

	@NotNull
	@NotEmpty(message = "{rform.graduation.empty}")
	private String graduation;

	@NotNull
	@NotEmpty(message = "{rform.birthday.empty}")
	private String birthday;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
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

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
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
