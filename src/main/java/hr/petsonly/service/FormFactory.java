package hr.petsonly.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.petsonly.model.Location;
import hr.petsonly.model.User;
import hr.petsonly.model.form.RegistrationForm;
import hr.petsonly.repository.LocationRepository;
import hr.petsonly.repository.UserRepository;

@Service
public class FormFactory {
	
	@Autowired
	LocationRepository lr;
	@Autowired
	UserRepository ur;
	
	public User createUserFromForm(RegistrationForm rf){
		User u = new User();
		u.setUserId(UUID.randomUUID());
		u.setName(rf.getName());
		u.setSurname(rf.getSurname());
		u.setUserPid(rf.getUserPid());
		u.setMobilePhone(rf.getMobilePhone());
		u.setPhone(rf.getPhone());
		u.setEmail(rf.getEmail());
		u.setAddress(rf.getAddress());
		u.setPassword(rf.getPassword());
		
		if(rf.getLocation() != null){
			Location l = lr.findByZipCode(rf.getLocation());
			u.setLocation(l);
		}
		
		String pattern = u.getName() + u.getSurname();
		Long num = ur.countByUserMnemonic(pattern+"[0-9]*");
		u.setUserMnemonic(pattern + num);
		return u;
	}

}
