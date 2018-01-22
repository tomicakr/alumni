package hr.petsonly.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.petsonly.model.Reservation;
import hr.petsonly.model.User;
import hr.petsonly.model.details.ReservationDetails;
import hr.petsonly.repository.ReservationRepository;

@Service	
public class ReservationService {
	
	@Autowired
	ReservationRepository repository;

	
	@Autowired
	UserService userService;
	
	public Reservation findOne(UUID id){
		return repository.findOne(id);
	}
	
	public Reservation save(Reservation r){
		return repository.save(r);
	}
	
	public boolean isDeleted(UUID id){
		return !repository.exists(id);
	}
	
	public List<ReservationDetails> findAllPendingReservations(User u){
		List<Reservation> reservations;
		if(userService.isAdmin(u)){
			reservations = repository.findAllFuturePending();
		
		} else{
			reservations = repository.findAllPendingWithinAvailableHours(u);
		}
		
		List<ReservationDetails> details = new ArrayList<>();
		reservations.forEach(r  -> {
			details.add(new ReservationDetails(r));
		});
		
		return details;
	}
	
	public List<ReservationDetails> findAllAcceptedReservations(User u){
		List<Reservation> reservations;
		if(userService.isAdmin(u)){
			reservations = repository.findAllFutureAccepted();
		
		} else{
			reservations = repository.findAllAcceptedByEmployee(u);
		}
		
		List<ReservationDetails> details = new ArrayList<>();
		reservations.forEach(r  -> {
			details.add(new ReservationDetails(r));
		});
		
		return details;
	}
	
	public List<ReservationDetails> findAllConfirmedReservations(User u){
		List<Reservation> reservations;
		if(userService.isAdmin(u)){
			reservations = repository.findAllConfirmed();
		
		} else{
			reservations = repository.findAllConfirmedByEmployee(u);
		}
		
		List<ReservationDetails> details = new ArrayList<>();
		reservations.forEach(r  -> {
			details.add(new ReservationDetails(r));
		});
		
		return details;
	}
	
	
}
