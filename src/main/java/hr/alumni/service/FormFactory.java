package hr.alumni.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import hr.alumni.model.Comment;
import hr.alumni.model.Role;
import hr.alumni.model.User;
import hr.alumni.model.details.CustomUserDetails;
import hr.alumni.model.form.CommentForm;
import hr.alumni.model.form.EditUserForm;
import hr.alumni.model.form.RegistrationForm;
import hr.alumni.repository.LocationRepository;
import hr.alumni.repository.PostRepository;
import hr.alumni.repository.RoleRepository;
import hr.alumni.repository.UserRepository;
@Service
public class FormFactory {
	
	private final LocationRepository lr;
	private final RoleRepository rr;
	private final PasswordEncoder pe;
	private final UserRepository ur;


	@Autowired
	public FormFactory(LocationRepository lr, RoleRepository rr, PasswordEncoder pe, UserRepository ur) {
		this.lr = lr;
		this.rr = rr;
		this.pe = pe;
		this.ur = ur;
	}

	public User createUserFromForm(RegistrationForm rf){
		User u = new User();

		u.setName(rf.getName());
		u.setSurname(rf.getSurname());
		u.setPhone(rf.getPhone());
		u.setEmail(rf.getEmail());
		u.setAddress(rf.getAddress());
		u.setPassword(pe.encode(rf.getPassword()));
		u.setRoles(Arrays.asList(rr.findByName("ROLE_KORISNIK")));
		u.setBirthday(Date.valueOf(rf.getBirthday()));
		u.setGraduation(Date.valueOf(rf.getGraduation()));

		List<Role> roles = new ArrayList<>();
		Role r = rr.findByNameIgnoreCase("ROLE_KORISNIK");
		roles.add(r);
		u.setRoles(roles);
		return u;
	}

	public boolean editUserFromForm(User user, EditUserForm ef) {
		if(!ef.hasChanges(user)) {
			return false;
		}

		user.setName(ef.getName());
		user.setSurname(ef.getSurname());
		user.setPhone(ef.getPhone());
		user.setEmail(ef.getEmail());
		user.setLocation(lr.findOne(ef.getLocation()));
		user.setAddress(ef.getAddress());

		if(ef.getPassword() != null && !ef.getPassword().equals("")){
			user.setPassword(ef.getPassword());
		}
		
		return true;
	}

	public Comment createCommentFromForm(CommentForm cForm, CustomUserDetails user) {
		Comment comment = new Comment();

		comment.setMessage(cForm.getMessage());
		comment.setUser(ur.findOne(user.getUserId()));

		return comment;
	}

}
