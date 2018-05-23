package hr.alumni.model.details;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import hr.alumni.model.User;

@Component
public class UserDetailsBasic {

	private UUID userId;
	private String firstName;
	private String lastName;
	private String email;
	private List<String> roles;

	public UserDetailsBasic() {
	}

	public UserDetailsBasic(User user) {

		this.userId = user.getUserId();
		this.firstName = user.getName();
		this.lastName = user.getSurname();
		this.email = user.getEmail();
		this.roles = new ArrayList<>();

		roles = new ArrayList<>();
		if (user.getRoles().size()>0 && user.getRoles().get(0) != null) {
			user.getRoles().forEach((role) -> {
				roles.add(role.getName());
			});
		}
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

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

}
