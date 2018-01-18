package hr.petsonly.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hr.petsonly.model.Role;
import hr.petsonly.model.User;
import hr.petsonly.model.form.RegistrationForm;
import hr.petsonly.repository.UserRepository;

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
	
	public boolean hireUser(UUID userId) {
		User user = repository.findOne(userId);
		
		if(user == null) {
			return false;
		}
		
		repository.hireUser(userId.toString());
		
		return true;
	}
	
	public boolean fireUser(UUID userId) {
		User user = repository.findOne(userId);
		
		if(user == null) {
			return false;
		}
		
		repository.fireUser(userId.toString());
		
		return true;
	}

	private boolean emailExist(String email) {
		User user = repository.findByEmail(email);
		if (user != null) {
			return true;
		}
		
		return false;
	}
}
