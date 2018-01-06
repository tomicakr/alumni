package hr.petsonly.web;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hr.petsonly.model.Reservation;
import hr.petsonly.model.User;
import hr.petsonly.repository.ReservationRepository;
import hr.petsonly.repository.UserRepository;

@Controller
@RequestMapping(value = "users/{uid}/reservations")
public class ReservationController {

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private UserRepository userRepository;

	@ResponseBody
	@GetMapping
	public List<Reservation> showAllReservationsOfAUser(@PathVariable UUID id) {

		User user = userRepository.getOne(id);
		List<Reservation> userReservations = reservationRepository.findAllByUser(user);

		return userReservations;
	}

	@GetMapping(value = "/new")
	public String showReservationForm(Model model, Reservation reservation) {

		model.addAttribute("reservation", reservation);
		return "newReservation";
	}

	@PostMapping(value = "")
	public String createReservation(@PathVariable UUID uid, @Valid Reservation reservation, BindingResult result) {

		if (result.hasErrors()) {
			return "newReservation";
		}

		User user = userRepository.getOne(uid);
		user.getReservations().add(reservation);

		userRepository.save(user);
		return String.format("redirect:/users/%s/reservations", uid.toString());
	}
	
	@GetMapping(value = "/{id}")
	public String showSelectedReservation(Model model, @PathVariable UUID reservationId) {
		
		Reservation reservation = reservationRepository.getOne(reservationId);
		model.addAttribute("reservation", reservation);
		
		return "reservation";
	}
	
	@GetMapping(value = "/{id}/edit")
	public String showReservationEditForm(Model model, @PathVariable UUID reservationId) {
		
		Reservation reservation = reservationRepository.getOne(reservationId);
		model.addAttribute("reservation", reservation);
		
		return "reservationEdit";
	}

	@PutMapping(value = "/{id}")
	public String saveReservation(@PathVariable UUID uid, @Valid Reservation reservatioin, BindingResult result) {
		
		if(result.hasErrors()) {
			return "reservationEdit";
		}
		
		// ovo naravno treba drugacije --------
		
		User user = userRepository.getOne(uid);
		
		List<Reservation> usersReservations = user.getReservations();
		usersReservations.removeIf(res -> res.getReservationKey().equals(reservatioin.getReservationKey()));
		
		usersReservations.add(reservatioin);
		
		userRepository.save(user);
		
		
		// ------------------------------------
		return String.format("redirect:/users/%s/reservations", uid.toString());
	}
	
	@DeleteMapping(value = "/{id}")
	public String deleteReservation(@PathVariable UUID id, @PathVariable UUID uid) {
		
		reservationRepository.delete(id);
		
		return String.format("redirect:/users/%s/reservations", uid.toString());
	}
}
