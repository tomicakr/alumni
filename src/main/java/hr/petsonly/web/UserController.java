package hr.petsonly.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hr.petsonly.model.User;
import hr.petsonly.repository.UserRepository;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showUserList(Model model) {

		List<User> allUsers = userRepository.findAll();

		model.addAttribute("users", allUsers);

		return "users";
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String showRegistrationForm() {

		return "register";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String logoutUser(Model model, HttpSession httpSession) {

		// TODO: validacija, spremanje u bazu, prijava
		// validacija -> 1.kreiranje usera u ovoj metodi i validacija pomocu Spring MVC
		// Forms?
		// 2.validacija ispravnosti podataka u frontendu?
		// 3.pomocu validationForm?
		User user = new User();

		return "redirect:/users/" + user.getUserPid(); // mozda drukcije?
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String showUserProfile(Model model, @PathVariable Long id) {

		User user = userRepository.findOne(id);
		
		if(user == null) {
			model.addAttribute("errorMessage", "Taj korisnik ne postoji!");
			return "customError";
		}
		
		model.addAttribute("thisUser", user);

		return "profile";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String updateUser(Model model, @PathVariable Long id) {

		// TODO: Validacija upisanih podataka

		return "redirect:/users/" + String.valueOf(id); // mozda drukcije?
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String deleteUser(Model model, @PathVariable Long id, HttpSession httpSession) {

		User userInSession = (User) httpSession.getAttribute("user");
		User userWithThatIdInDatabase = userRepository.getOne(id);

		if (!(userInSession.equals(userWithThatIdInDatabase) || userInSession.getRole() == 1)) { // pretpostavljam da je
																									// 1 admin
			model.addAttribute("errorMessage", "Nemas ovlasti za ovo!"); // TODO: ovakve poruke stavljati u
																			// application.properties da nisu ovako
																			// hardkodirane
																			// takoder: ovo mozda ne treba raditi ovako
																			// , a i ovaj kod bi se mozda trebao
																			// odvojiti u zaseban @service
																			// ako ce se na ovaj nacin provjeravati ima
																			// li korisnik koji je u sesiji odgovarajuce
																			// ovlasti
			return "customError";
		}

		if (userInSession.getRole() == 1) {
			return "redirect:/users/";
		} else {
			return "redirect:/index";
		}
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String editUser(Model model, @PathVariable Long id, HttpSession httpSession) {

		User userInSession = (User) httpSession.getAttribute("user");
		User userWithThatIdInDatabase = userRepository.getOne(id);

		if (!(userInSession.equals(userWithThatIdInDatabase) || userInSession.getRole() == 1)) { // pretpostavljam da je
																									// 1 admin
			model.addAttribute("errorMessage", "Nemas ovlasti za ovo!"); // TODO: ovakve poruke stavljati u
																			// application.properties da nisu ovako
																			// hardkodirane
																			// takoder: ovo mozda ne treba raditi ovako
			return "customError";
		}

		model.addAttribute("userForEdit", userWithThatIdInDatabase);
		return "editUser";
	}

}