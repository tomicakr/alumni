package hr.petsonly.web;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import hr.petsonly.model.Reservation;
import hr.petsonly.model.Role;
import hr.petsonly.model.User;
import hr.petsonly.model.details.CustomUserDetails;
import hr.petsonly.model.details.LocationDetails;
import hr.petsonly.model.details.ReservationDetails;
import hr.petsonly.model.details.UserDetailsMore;
import hr.petsonly.model.form.EditReservationForm;
import hr.petsonly.repository.ReservationRepository;
import hr.petsonly.repository.UserRepository;
import hr.petsonly.service.FormFactory;
import hr.petsonly.service.email.EmailServiceImpl;

@Controller
@RequestMapping(value = "/users/{id}/jobs")
public class JobController {
	
	private final ReservationRepository reservationRepository;
	private final UserRepository userRepository;
	private final EmailServiceImpl mailService;
	private FormFactory formFactory;

	@Autowired
	public JobController(UserRepository userRepository, EmailServiceImpl mailService, ReservationRepository reservationRepository) {
		this.userRepository = userRepository;
		this.mailService = mailService;
		this.reservationRepository = reservationRepository;
	}

	@GetMapping
	public String showAllReservations(Model model, @PathVariable UUID id) {
		
		User user = userRepository.findOne(id);
		
		List<Reservation> allReservations = reservationRepository.findAll();
		List<ReservationDetails> open = new ArrayList<>();
		List<ReservationDetails> accepted = new ArrayList<>();
		List<ReservationDetails> confirmed = new ArrayList<>();

		for (Reservation res : allReservations) {
			switch (res.getReservationStatus()) {
			case 1:
				for (Role rl : user.getRoles()) {
					if (rl.getName().equals("ROLE_ADMINISTRATOR")) {
						open.add(new ReservationDetails(res));
						break;
					}
				}
				
				if(user.compareUserAndReservationTime(res)){
					open.add(new ReservationDetails(res));
				}
				break;
			case 2:
				accepted.add(new ReservationDetails(res));
				break;
			case 3:
				confirmed.add(new ReservationDetails(res));
				break;
			}
		}
			
		model.addAttribute("open", open);
		model.addAttribute("accepted", accepted);
		model.addAttribute("confirmed", confirmed);
		
		return "jobs";
	}
	
	@GetMapping("/{reservationId}")
	public String showReservationDetails(Model model, @PathVariable UUID reservationId) {
		Reservation reservation = reservationRepository.findOne(reservationId);
		ReservationDetails reservationDetails = new ReservationDetails(reservation);
		model.addAttribute("reservation", reservationDetails);
		
		return "reservation";
		
	}
	
	@PostMapping("/{reservationId}/accept")
	public String acceptReservation(@PathVariable UUID reservationId) {
		
		Reservation reservation = reservationRepository.findOne(reservationId);
		reservation.setReservationStatus(2); //accepted
		reservationRepository.save(reservation);
		
		return "redirect:/users/{id}/jobs";
	}
	
	@PostMapping("/{reservationId}/confirm")
	public String confirmReservation(@PathVariable UUID reservationId) {
		
		Reservation reservation = reservationRepository.findOne(reservationId);
		reservation.setReservationStatus(3);
		reservationRepository.save(reservation);		
		mailService.sendReservationOffer(reservation);
		
		return "redirect:/users/{id}/jobs";
	}
	
	@PostMapping("/{reservationId}/archive")
	public String archiveReservation(@PathVariable UUID reservationId) {
		
		Reservation reservation = reservationRepository.findOne(reservationId);
		reservation.setReservationStatus(4);
		reservationRepository.save(reservation);

		return "redirect:/users/{id}/jobs";
	}
	
	@PostMapping("/{reservationId}/edit")
	public String updateReservation(Model model, @PathVariable UUID reservationId, EditReservationForm editReservationForm, BindingResult result) {

		Reservation reservation = reservationRepository.findOne(reservationId);

		/*if (formFactory.editReservationFromForm(reservation, editReservationForm)) {
			reservationRepository.save(reservation);
		}*/

		return "redirect:/users/{id}/jobs";




	}
}
