package hr.petsonly.web;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import hr.petsonly.model.Reservation;
import hr.petsonly.repository.ReservationRepository;

@Controller
@RequestMapping(value = "/jobs")
public class JobController {
	
	@Autowired
	private ReservationRepository reservationRepository;

	@GetMapping
	public String showAllReservations(Model model) {
		
		List<Reservation> allReservations = reservationRepository.findAll();
		model.addAllAttributes(allReservations);
		
		return "jobs";
	}
	
	@GetMapping("/{id}")
	public String showReservationDetalils(Model model, @PathVariable UUID reservationId) {
		
		Reservation reservation = reservationRepository.findOne(reservationId);
		model.addAttribute("reservation", reservation);
		
		return "reservation";
	}
	
	@PostMapping("/{id}/accept")
	public String acceptReservation(@PathVariable UUID reservationId) {
		
		Reservation reservation = reservationRepository.findOne(reservationId);
		reservation.setReservationStatus(1); 
		
		return "redirect:/jobs";
	}
	
	@PostMapping("/{id}/confirm")
	public String confirmReservation(@PathVariable UUID reservationId) {
		
		Reservation reservation = reservationRepository.findOne(reservationId);
		reservation.setReservationStatus(1); 
		
		return "redirect:/jobs";
	}
}
