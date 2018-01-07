package hr.petsonly.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.petsonly.model.Location;
import hr.petsonly.model.Reservation;
import hr.petsonly.model.User;
import hr.petsonly.model.form.AddReservationForm;
import hr.petsonly.model.form.RegistrationForm;
import hr.petsonly.repository.LocationRepository;
import hr.petsonly.repository.PetRepository;
import hr.petsonly.repository.ServiceRepository;
import hr.petsonly.repository.UserRepository;

@Service
public class FormFactory {
	
	@Autowired
	LocationRepository lr;
	@Autowired
	UserRepository ur;
	@Autowired
	ServiceRepository sr;
	@Autowired
	PetRepository pr;
	
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

	public Reservation createReservationFromForm(AddReservationForm arf){
		Reservation r = new Reservation();
		r.setReservationKey(UUID.randomUUID());
		r.setReservationStatus(1); //KAKO SU NUMERIRANI STATUSI?
		r.setReservationTime(LocalDateTime.now());
		r.setExecutionTime(LocalDateTime.parse(arf.getExecutionTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
		r.setDuration(arf.getDuration());
		r.setSendReminder(arf.getSendReservation().toLowerCase().equals("yes") || arf.getSendReservation().toLowerCase().equals("y"));
		
		r.setService(sr.findOne(UUID.fromString(arf.getService())));
		r.setPet(pr.findOne(UUID.fromString(arf.getPet())));
		r.setUser(ur.findOne(UUID.fromString(arf.getUser())));
		if(arf.getEmployee() != null){
			r.setEmployee(ur.findOne(UUID.fromString(arf.getEmployee())));	
		}
		return r;
	}
}
