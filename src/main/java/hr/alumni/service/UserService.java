package hr.alumni.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hr.alumni.model.Role;
import hr.alumni.model.User;
import hr.alumni.model.details.UserDetailsBasic;
import hr.alumni.model.form.RegistrationForm;
import hr.alumni.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository repository;

	@Autowired
	private FormFactory formFactory;

	@Autowired
	private AuthenticationManager authenticationManager;

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

	private boolean emailExist(String email) {
		User user = repository.findByEmail(email);
		if (user != null) {
			return true;
		}

		return false;
	}

	public boolean isAdmin(User u) {
		for (Role r : u.getRoles()) {
			if (r.getName().equals("ROLE_ADMINISTRATOR")) {
				return true;
			}
		}
		return false;
	}

	public List<UserDetailsBasic> getAllUsersBasicDetails() {
		List<User> allUsersFull = repository.findAll();
		List<UserDetailsBasic> allUsers = new ArrayList<>();

		allUsersFull.forEach((user) -> allUsers.add(new UserDetailsBasic(user)));

		return allUsers;
	}

	public void authenticateUserAndSetSession(RegistrationForm rform, HttpServletRequest request) {
		String username = rform.getEmail();
		String password = rform.getPassword();
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

		// generate session if one doesn't exist
		request.getSession();

		token.setDetails(new WebAuthenticationDetails(request));
		Authentication authenticatedUser = authenticationManager.authenticate(token);
		SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
	}
}
