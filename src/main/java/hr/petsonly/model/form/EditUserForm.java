package hr.petsonly.model.form;

import java.util.UUID;

import org.springframework.stereotype.Component;

import hr.petsonly.model.User;

@Component
public class EditUserForm {
	
	private String mobilePhone;
	private UUID location;
	private String oldPassword;
	private String password;
	private String password2;
	
	public EditUserForm(String mobilePhone, UUID location, String oldPassword, String password, String password2) {
		super();
		this.mobilePhone = mobilePhone;
		this.location = location;
		this.oldPassword = oldPassword;
		this.password = password;
		this.password2 = password2;
	}

	public EditUserForm() {
	}

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
	
	public boolean hasChanges(User user) {
		if(user.getLocation().getLocationId().equals(location) && user.getMobilePhone().equals(mobilePhone)) {
			return false;
		}
		
		return true;
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
	
	
}
