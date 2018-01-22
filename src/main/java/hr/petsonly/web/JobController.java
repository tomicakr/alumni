package hr.petsonly.web;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import hr.petsonly.model.Reservation;
import hr.petsonly.model.ReservationStatus;
import hr.petsonly.model.User;
import hr.petsonly.model.details.ReservationDetails;
import hr.petsonly.model.form.EditReservationForm;
import hr.petsonly.repository.UserRepository;
import hr.petsonly.service.FormFactory;
import hr.petsonly.service.ReservationService;
import hr.petsonly.service.email.EmailServiceImpl;

@Controller
@RequestMapping(value = "/users/{id}/jobs")
public class JobController {
	
	private final ReservationService reservationService;

	private final EmailServiceImpl mailService;
	
	private final UserRepository userRepository;
	private FormFactory formFactory;

	@Autowired
	public JobController(EmailServiceImpl mailService, ReservationService reservationService, UserRepository userRepository) {
		this.mailService = mailService;
		this.reservationService = reservationService;
		this.userRepository = userRepository;
	}

	@GetMapping
	public String showAllReservations(Model model, @PathVariable UUID id) {
		
		User user = userRepository.findOne(id);
		
		List<ReservationDetails> open = reservationService.findAllPendingReservations(user);
		List<ReservationDetails> accepted = reservationService.findAllAcceptedReservations(user);
		List<ReservationDetails> confirmed = reservationService.findAllConfirmedReservations(user);

		
		model.addAttribute("open", open);
		model.addAttribute("accepted", accepted);
		model.addAttribute("confirmed", confirmed);
		
		return "jobs";
	}
	
	@GetMapping("/{reservationId}")
	public String showReservationDetalils(Model model, @PathVariable UUID reservationId) {
		Reservation reservation = reservationService.findOne(reservationId);
		ReservationDetails reservationDetails = new ReservationDetails(reservation);
		model.addAttribute("reservation", reservationDetails);
		
		return "reservation";
		
	}
	
	@PostMapping("/{reservationId}/accept")
	public String acceptReservation(@PathVariable UUID reservationId, @PathVariable UUID id) {
		User employee = userRepository.findOne(id);
		Reservation reservation = reservationService.findOne(reservationId);
		reservation.setReservationStatus(ReservationStatus.ACCEPTED); //accepted
		reservation.setEmployee(employee);
		reservationService.save(reservation);
		
		return "redirect:/users/{id}/jobs";
	}
	
	@PostMapping("/{reservationId}/confirm")
	public String confirmReservation(@PathVariable UUID reservationId) {
		
		Reservation reservation = reservationService.findOne(reservationId);
		reservation.setReservationStatus(ReservationStatus.CONFIRMED);
		reservationService.save(reservation);		
		mailService.sendReservationOffer(reservation);
		
		return "redirect:/users/{id}/jobs";
	}
	
	@PostMapping("/{reservationId}/archive")
	public String archiveReservation(@PathVariable UUID reservationId) {
		
		Reservation reservation = reservationService.findOne(reservationId);
		reservation.setReservationStatus(ReservationStatus.ARCHIVED);
		reservationService.save(reservation);

		return "redirect:/users/{id}/jobs";
	}
	
	@PostMapping("/{reservationId}/edit")
	public String updateReservation(Model model, @PathVariable UUID reservationId, EditReservationForm editReservationForm, BindingResult result) {

		Reservation reservation = reservationService.findOne(reservationId);

		/*if (formFactory.editReservationFromForm(reservation, editReservationForm)) {
			reservationRepository.save(reservation);
		}*/

		return "redirect:/users/{id}/jobs";




	}
}
