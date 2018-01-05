package hr.petsonly.web;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String showUserList(Model model) {

		List<User> allUsers = userRepository.findAll();

		model.addAttribute("users", allUsers);

		return "users";
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String showRegistrationForm(Model model, User user) {
		
		model.addAttribute("user", user);
		
		return "register";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String showUserProfile(Model model, @PathVariable UUID id) {

		User user = userRepository.findOne(id);

		if (user == null) {
			model.addAttribute("errorMessage", "Taj korisnik ne postoji!");
			return "customError";
		}

		model.addAttribute("thisUser", user);

		return "profile";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String createUser(Model model, @Valid User user, BindingResult result) {

		if(result.hasErrors()) {
			System.out.println(user);
			System.out.println(result);
			model.addAttribute("user", user);
			model.addAttribute("errorMessage", "Registracija nije valjana"); // ovo odvoji u zasebni
			return "register";
		}
		
		System.out.println("AAAAAAAAAAAAAAAAAAAa");
		
		userRepository.save(user);
		
		return "redirect:/users"; // mozda drukcije?
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String updateUser(Model model, @PathVariable UUID id) {

		// TODO: Validacija upisanih podataka

		// User user = new User();
		//
		// Role role = new Role();
		// user.getRoles().add(role);
		// userRepository.save(user);

		return "redirect:/users" + id.toString(); // mozda drukcije?
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String deleteUser(Model model, @PathVariable UUID id, HttpSession httpSession) {

		User userInSession = (User) httpSession.getAttribute("user");
		User userWithThatIdInDatabase = userRepository.findOne(id);

		if (!(userInSession.equals(userWithThatIdInDatabase))) { // pretpostavljam da je
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

		return "redirect:/index";
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String editUser(Model model, @PathVariable UUID id, HttpSession httpSession) {

		User userInSession = (User) httpSession.getAttribute("user");
		User userWithThatIdInDatabase = userRepository.getOne(id);

		if (!(userInSession.equals(userWithThatIdInDatabase))) { // pretpostavljam da je
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