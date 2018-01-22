package hr.petsonly.service;

import hr.petsonly.model.Role;
import hr.petsonly.model.User;
import hr.petsonly.model.form.PatchForm;
import hr.petsonly.model.form.RegistrationForm;
import hr.petsonly.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
	@Autowired
	private UserRepository repository;

	@Autowired
	private FormFactory formFactory;

	@Transactional
	public User registerNewUserAccount(RegistrationForm rf) {

		if (emailExist(rf.getEmail())) {
			return null;
		}

		User user = formFactory.createUserFromForm(rf);

		return repository.save(user);
	}

	@Transactional
	public List<Role> findUsersRoles(User user) {
		return user.getRoles();
	}

	public boolean hireUser(User user) {
		if (user == null) {
			return false;
		}

		repository.hireUser(user.getUserId().toString());

		return true;
	}

	public boolean fireUser(User user) {

		if (user == null || !user.getTasks().isEmpty()) {
			return false;
		}

		repository.fireUser(user.getUserId().toString());

		return true;
	}

	private boolean emailExist(String email) {
		User user = repository.findByEmail(email);
		if (user != null) {
			return true;
		}

		return false;
	}

	public boolean updateUser(UUID userId, PatchForm patchForm) {

		User user = repository.findOne(userId);
		
		String operation = patchForm.getOp();
		String path = patchForm.getPath();
		String value = patchForm.getValue();

		switch (operation) {
		case "replace":

			switch (path) {
			case "/status":

				if (value.equals("employee")) {
					hireUser(user);
					return true;
				} else if (value.equals("client")) {
					fireUser(user);
					return true;
				}

				return false;
			case "notAvailableFrom":
				String[] parts = value.split(":");
				if (parts.length != 2)
					return false;

				Integer i1;
				Integer i2;
				
				try {
					i1 = Integer.parseInt(parts[0]);
					i2 = Integer.parseInt(parts[1]);
					user.setNotAvailableFrom(LocalTime.of(i1, i2));
					repository.save(user);
				} catch (NumberFormatException|DateTimeException ex) {
					return false;
				}

				return true;
			
			case "notAvailableTo":
				String[] parts2 = value.split(":");
				if (parts2.length != 2) {
					return false;
				}

				Integer i3;
				Integer i4;
				
				try {
					i3 = Integer.parseInt(parts2[0]);
					i4 = Integer.parseInt(parts2[1]);
					user.setNotAvailableTo(LocalTime.of(i3, i4));
					repository.save(user);
				} catch (NumberFormatException|DateTimeException ex) {
					return false;
				}

				return true;
			case "emailSetting":
				user.setNotificationSetting(Integer.parseInt(value));
				repository.save(user);
				return true;
			}
		}

		return false;
	}

	public boolean validatePatchForm(PatchForm patchForm, Boolean valid, UUID userId) {

		String operation = patchForm.getOp();
		String path = patchForm.getPath();
		String value = patchForm.getValue();
		
		User user = repository.findOne(userId);
		
		switch (operation) {
		case "replace":

			switch (path) {
			case "/status":

				if (value.equals("employee")) {
					return true;
				} else if (value.equals("client")) {
					if(user == null || !user.getTasks().isEmpty()) {
						System.out.println(Arrays.toString(user.getTasks().toArray()));
						return false;
					}
					return true;
				}

				return false;
			case "notAvailableFrom":
				String[] parts = value.split(":");
				if (parts.length != 2) {
					return false;
				}

				Integer i1;
				Integer i2;
				
				try {
					i1 = Integer.parseInt(parts[0]);
					i2 = Integer.parseInt(parts[1]);
					LocalTime.of(i1, i2);
				} catch (NumberFormatException|DateTimeException ex) {
					return false;
				}

				return true;
			case "notAvailableTo":
				String[] parts2 = value.split(":");
				if (parts2.length != 2) {
					return false;
				}

				Integer i3;
				Integer i4;
				
				try {
					i3 = Integer.parseInt(parts2[0]);
					i4 = Integer.parseInt(parts2[1]);
					LocalTime.of(i3, i4);
				} catch (NumberFormatException|DateTimeException ex) {
					return false;
				}

				return true;
			case "emailSetting":
				
				if(!(value.equals("0") || value.equals("1") || value.equals("2"))) {
					return false;
				}
				
				return true;
			}
		}

		return false;
	}
	
	public boolean isAdmin(User u){
		for(Role r : u.getRoles()){
			if(r.getName().equals("ROLE_ADMINISTRATOR")){
				return true;
			}
		}
		return false;
	}
}
