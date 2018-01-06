package hr.petsonly.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.petsonly.model.Location;
import hr.petsonly.model.User;
import hr.petsonly.repository.LocationRepository;
import hr.petsonly.repository.UserRepository;

@Service
public class FormFactory {
	
	@Autowired
	LocationRepository lr;
	@Autowired
	UserRepository ur;
	
	public User createUserFromForm(/*RegistrationForm rf*/){
		User u = new User();
		u.setUserId(UUID.randomUUID());
		u.setName(null/*rf.getName()*/);
		u.setSurname(null/*rf.getSurname()*/);
		u.setUserPid(null/*rf.getUserPid()*/);
		u.setMobilePhone(null/*rf.getMobilePhone()*/);
		u.setPhone(null/*ref.getPhone()*/);
		u.setEmail(null/*rf.getEmail()*/);
		u.setAddress(null/*rf.getAddress*/);
		u.setPassword(null/*rf.setPassword()*/);
		
		if(true/*rf.getLocation() != null*/){
			Location l = lr.findByZipCode(0/*rf.getLocation()*/);
			u.setLocation(l);
		}
		
		String pattern = u.getName() + u.getSurname();
		Long num = ur.countByUserMnemonic(pattern+"[0-9]*");
		u.setUserMnemonic(pattern + num);
		return u;
	}

}
