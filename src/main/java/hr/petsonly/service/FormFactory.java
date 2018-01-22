package hr.petsonly.service;

import hr.petsonly.model.*;
import hr.petsonly.model.form.AddReservationForm;
import hr.petsonly.model.form.EditUserForm;
import hr.petsonly.model.form.PetForm;
import hr.petsonly.model.form.RegistrationForm;
import hr.petsonly.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class FormFactory {
	
	private final LocationRepository lr;
	private final UserRepository ur;
	private final ServiceRepository sr;
	private final PetRepository pr;
	private final RoleRepository rr;
	private final PasswordEncoder pe;
	private final SpeciesRepository specr;

	@Value("${default.reservation.duration}")
	private String DEFAULT_DURATION;

	@Autowired
	public FormFactory(LocationRepository lr, UserRepository ur, ServiceRepository sr, PetRepository pr, RoleRepository rr, PasswordEncoder pe, SpeciesRepository specr) {
		this.lr = lr;
		this.ur = ur;
		this.sr = sr;
		this.pr = pr;
		this.rr = rr;
		this.pe = pe;
		this.specr = specr;
	}

	public User createUserFromForm(RegistrationForm rf){
		User u = new User();
		u.setName(rf.getName());
		u.setSurname(rf.getSurname());
		u.setUserPid(rf.getUserPid());
		u.setMobilePhone(rf.getMobilePhone());
		u.setPhone(rf.getPhone());
		u.setEmail(rf.getEmail());
		u.setAddress(rf.getAddress());
		u.setPassword(pe.encode(rf.getPassword()));
		
		u.setRoles(Arrays.asList(rr.findByName("ROLE_KORISNIK")));
		
		Location l = lr.findOne(rf.getLocation());
		u.setLocation(l);
		
		String pattern = u.getName() + u.getSurname();
		Long num = ur.countByUserMnemonic(pattern+"[0-9]*");
		u.setUserMnemonic(pattern + num);
		
		List<Role> roles = new ArrayList<>();
		Role r = rr.findByNameIgnoreCase("ROLE_KORISNIK");
		roles.add(r);
		u.setRoles(roles);
		return u;
	}

	public Reservation createReservationFromForm(AddReservationForm arf){
		System.out.println(arf.toString());
		Reservation r = new Reservation();
		r.setReservationKey(UUID.randomUUID());
		r.setReservationStatus(ReservationStatus.PENDING); //KAKO SU NUMERIRANI STATUSI?
		r.setReservationTime(LocalDateTime.now());
		r.setExecutionTime(LocalDateTime.parse(arf.getExecutionTime(), DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm")));
		r.setSendReminder(arf.getSendReminder() != null);

		String[] parts;

		if(arf.getDuration().trim().isEmpty()) {
			parts = DEFAULT_DURATION.split(":");
		} else {
			parts = arf.getDuration().split(":");
		}
		
		Integer hours = Integer.parseInt(parts[0]);
		Integer minutes = Integer.parseInt(parts[1]);
		r.setDuration(Duration.ofMinutes(hours * 60 + minutes));
		
		r.setUser(ur.findOne(arf.getOwner()));
		r.setService(sr.findOne(UUID.fromString(arf.getService())));
		r.setPet(pr.findOne(arf.getPet()));
		if(arf.getPreferedEmployee() != null){
			r.setPreferedEmployee(ur.findOne(arf.getPreferedEmployee()));
		}
		return r;
	}
	
	public Pet createPetFromForm(PetForm pf){
		Pet p = new Pet();
		p.setPetKey(UUID.randomUUID());
		p.setName(pf.getName());
		p.setAge(pf.getAge());
		p.setMicrochip(pf.getMicrochip());
		p.setRemark(pf.getRemark());
		p.setSex(pf.getSex());
		p.setSpecies(specr.getOne(pf.getSpecies()));
		p.setOwner(ur.findOne(pf.getOwner()));
		return p;
	}
	
	public boolean editUserFromForm(User user, EditUserForm ef) {
		if(!ef.hasChanges(user)) {
			return false;
		}

		user.setName(ef.getName());
		user.setSurname(ef.getSurname());
		user.setMobilePhone(ef.getMobilePhone());
		user.setPhone(ef.getPhone());
		user.setEmail(ef.getEmail());
		user.setLocation(lr.findOne(ef.getLocation()));
		user.setAddress(ef.getAddress());

		if(ef.getPassword() != null && !ef.getPassword().equals("")){
			user.setPassword(ef.getPassword());
		}
		
		return true;
	}

	public boolean editReservationFromForm(Reservation res, AddReservationForm rf) {
		if(rf.hasChanges(res)) {
			return false;
		}
		
		// TODO: implementacija
		return true;
	}

}
