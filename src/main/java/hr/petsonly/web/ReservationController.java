package hr.petsonly.web;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.hibernate.hql.internal.ast.tree.FromElementFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
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

import hr.petsonly.model.Pet;
import hr.petsonly.model.Reservation;
import hr.petsonly.model.User;
import hr.petsonly.model.details.PetDetails;
import hr.petsonly.model.details.ReservationDetails;
import hr.petsonly.model.details.UserDetailsBasic;
import hr.petsonly.model.form.AddReservationForm;
import hr.petsonly.repository.PetRepository;
import hr.petsonly.repository.ReservationRepository;
import hr.petsonly.repository.UserRepository;
import hr.petsonly.service.FormFactory;

@Controller
@RequestMapping(value = "users/{uid}/reservations")
public class ReservationController {

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PetRepository petRepository;

	@Autowired
	private FormFactory formFactory;
	
	@ResponseBody
	@GetMapping
	public List<ReservationDetails> showAllReservationsOfAUser(@PathVariable UUID uid) {

		User user = userRepository.findOne(uid);
		List<Reservation> userReservations = reservationRepository.findAllByUser(user);
		List<ReservationDetails> reservationDetails = new ArrayList<>();

		userReservations.forEach(reservation -> {
			reservationDetails.add(new ReservationDetails(reservation));
		});

		return reservationDetails;
	}

	@GetMapping(value = "/new")
	public String showReservationForm(Model model, @PathVariable UUID uid) {
		
		List<Pet> pets = petRepository.findByOwnerId(uid.toString());
		List<PetDetails> petDetails = new ArrayList<>();
		pets.forEach(pet -> petDetails.add(new PetDetails(pet)));
		
		List<User> employees = userRepository.findAllEmployees();
		List<UserDetailsBasic> employeeDetails = new ArrayList<>();
		employees.forEach(employee -> employeeDetails.add(new UserDetailsBasic(employee)));
		
		model.addAttribute("userId", uid);
		model.addAttribute("pets", petDetails);
		model.addAttribute("employees", employeeDetails);
		
		return "newReservation";
	}

	@PostMapping
	public String createReservation(Model model, @PathVariable UUID uid, @Valid AddReservationForm reservationForm, BindingResult result) {

		if (result.hasErrors()) {
			model.addAttribute("reservation", reservationForm);
			return "newReservation";
		}

		User user = userRepository.getOne(uid);
		user.getReservations().add(formFactory.createReservationFromForm(reservationForm));
		userRepository.save(user);
		
		return String.format("redirect:/users/%s/reservations", uid.toString());
	}

	@GetMapping(value = "/{id}")
	public String showSelectedReservation(Model model, @PathVariable UUID id) {

		Reservation reservation = reservationRepository.getOne(id);
		ReservationDetails reservationDetails = new ReservationDetails(reservation);

		model.addAttribute("reservation", reservationDetails);
		return "reservation";
	}

	@GetMapping(value = "/{id}/edit")
	public String showReservationEditForm(Model model, @PathVariable UUID id) {
		
		
		return "reservationEdit";
	}

	@PutMapping(value = "/{id}")
	public String saveReservation(@PathVariable UUID uid, @Valid Reservation reservatioin, BindingResult result) {

		if (result.hasErrors()) {
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
