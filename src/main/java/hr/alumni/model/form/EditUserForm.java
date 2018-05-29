package hr.alumni.model.form;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.stereotype.Component;

import hr.alumni.model.form.validation.ValidEmail;

@Component
public class EditUserForm {

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
