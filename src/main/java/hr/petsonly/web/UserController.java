package hr.petsonly.web;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hr.petsonly.model.User;
import hr.petsonly.model.details.UserDetailsBasic;
import hr.petsonly.model.details.UserDetailsMore;
import hr.petsonly.repository.UserRepository;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping
	public String showUserList(Model model) {

		List<User> allUsersFull = userRepository.findAll();
		List<UserDetailsBasic> allUsers = new ArrayList<>();

		allUsersFull.forEach((user) -> {
			allUsers.add(new UserDetailsBasic(user));
		});

		model.addAttribute("users", allUsers);
		return "users";
	}

	@GetMapping("/new")
	public String showRegistrationForm(Model model, User user) {

		model.addAttribute("user", user);

		return "register";
	}

	@PostMapping
	public String createUser(Model model, @Valid User user, BindingResult result) {

		if (result.hasErrors()) {
			System.out.println(result);
			System.out.println(user);
			model.addAttribute("user", user);
			model.addAttribute("errorMessage", "Registracija nije valjana");
			return "register";
		}
		
		userRepository.save(user);

		return "redirect:/users";
	}

	@GetMapping("/{id}")
	public String showUserProfile(Model model, @PathVariable UUID id, HttpSession session) {

		UserDetailsMore userInSession = (UserDetailsMore) session.getAttribute("userInSession");

		if (userInSession == null || !userInSession.getUserId().equals(id)) {
			model.addAttribute("errorMessage", "Nemaš ovlasti za to!");
			return "customError";
		}

		User user = userRepository.findOne(id);

		if (user == null) {
			model.addAttribute("errorMessage", "Taj korisnik ne postoji!");
			return "customError";
		}

		UserDetailsMore userDetails = new UserDetailsMore(user);
		model.addAttribute("user", userDetails);

		return "profile";
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String editUser(Model model, @PathVariable UUID id, HttpSession session) {

		UserDetailsMore userInSession = (UserDetailsMore) session.getAttribute("userInSession");

		if (userInSession == null || !userInSession.getUserId().equals(id)) {
			model.addAttribute("errorMessage", "Nemaš ovlasti za to!");
			return "customError";
		}

		User user = userRepository.getOne(id);

		model.addAttribute("userForEdit", user);

		return "editUser";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String updateUser(Model model, @PathVariable UUID id, HttpSession session) {

		UserDetailsMore userInSession = (UserDetailsMore) session.getAttribute("userInSession");

		if (userInSession == null || !userInSession.getUserId().equals(id)) {
			model.addAttribute("errorMessage", "Nemaš ovlasti za to!");
			return "customError";
		}
		
		// TODO: dohvati usera iz baze i updateaj ga
		
		return "redirect:/users/" + id.toString();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String deleteUser(Model model, @PathVariable UUID id, HttpSession session) {

		UserDetailsMore userInSession = (UserDetailsMore) session.getAttribute("userInSession");

		if(userInSession == null || !userInSession.getUserId().equals(id)) {
			model.addAttribute("errorMessage", "Nemaš ovlasti za to!");
			return "customError";
		}
		
		//TODO: delete user

		return "redirect:/index";
	}

}