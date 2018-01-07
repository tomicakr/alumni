package hr.petsonly.model.form;

import java.util.UUID;

import org.springframework.stereotype.Component;

import hr.petsonly.model.User;

@Component
public class EditUserForm {
	
	private String mobilePhone;
	private UUID location;
	
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
	
}
