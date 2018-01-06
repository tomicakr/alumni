package hr.petsonly.model.details;

import java.util.List;

import org.springframework.stereotype.Component;

import hr.petsonly.model.User;

@Component
public class UserDetailsBasic {

	private String firstName;
	private String lastName;
	private String email;
	private String mnemonicId;
	private List<String> roles;

	public UserDetailsBasic() {
	}

	public UserDetailsBasic(User user) {

		this.firstName = user.getName();
		this.lastName = user.getSurname();
		this.email = user.getEmail();
		this.mnemonicId = user.getUserMnemonic();
		
		user.getRoles().forEach((role) -> {
			roles.add(role.getName());
		});
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMnemonicId() {
		return mnemonicId;
	}

	public void setMnemonicId(String mnemonicId) {
		this.mnemonicId = mnemonicId;
	}
	
	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

}
