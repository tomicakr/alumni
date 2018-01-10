package hr.petsonly.model.form;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import hr.petsonly.model.form.validation.ValidEmail;

@Component
public class RegistrationForm {
	
	@NotNull
	private String name;
	
	@NotNull
	private String surname;
	
	@NotNull
	private String userPid;

	private String mobilePhone;
	
	@NotNull
	private String phone;
	
	@NotNull
	@ValidEmail
	private String email;
	
	private UUID location;
	
	private String address;
	
	@NotNull
	private String password;
	
	@NotNull
	private String password2; // Možda passwordReType	
	
	public RegistrationForm() {
	}

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

	public String getUserPid() {
		return userPid;
	}

	public void setUserPid(String userPid) {
		this.userPid = userPid;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
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

	public UUID getLocation() {
		return location;
	}

	public void setLocation(UUID location) {
		this.location = location;
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

	@Override
	public String toString() {
		return "RegistrationForm [name=" + name + ", surname=" + surname + ", userPid=" + userPid + ", mobilePhone="
				+ mobilePhone + ", phone=" + phone + ", email=" + email + ", location=" + location + ", address="
				+ address + ", password=" + password + ", password2=" + password2 + "]";
	}
	
}
