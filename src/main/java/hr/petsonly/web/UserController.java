package hr.petsonly.web;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hr.petsonly.model.User;
import hr.petsonly.model.details.CustomUserDetails;
import hr.petsonly.model.details.LocationDetails;
import hr.petsonly.model.details.UserDetailsBasic;
import hr.petsonly.model.details.UserDetailsMore;
import hr.petsonly.model.form.EditUserForm;
import hr.petsonly.model.form.RegistrationForm;
import hr.petsonly.repository.UserRepository;
import hr.petsonly.service.CommonServices;
import hr.petsonly.service.FormFactory;
import hr.petsonly.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private FormFactory formFactory;

	@Autowired
	private CommonServices services;
	
	@Autowired
	private UserService userService;

	@GetMapping
	public String showUserList(Model model) {

		List<UserDetailsBasic> allUsers = services.getAllUsersBasicDetails();

		model.addAttribute("users", allUsers);

		return "users";
	}

	@GetMapping("/new")
	public String showRegistrationForm(Model model) {

		List<LocationDetails> locationDetails = services.getAllLocationDetails();

		model.addAttribute("locations", locationDetails);
		model.addAttribute("registrationForm", new RegistrationForm());

		return "register";
	}

	@PostMapping
	public String createUser(Model model, @Valid RegistrationForm registrationForm, BindingResult result,
			HttpSession session) {

		if (result.hasErrors()) {
			model.addAttribute("registrationForm", registrationForm);
			model.addAttribute("locations", services.getAllLocationDetails());

			return "register";
		}

		User user = userService.registerNewUserAccount(registrationForm);

		UserDetailsMore userDetails = new UserDetailsMore(user);
		session.setAttribute("userInSession", userDetails);

		return "redirect:/users/" + user.getUserId();
	}

	@GetMapping("/{id}")
	public String showUserProfile(Model model, @PathVariable UUID id, HttpSession session) {

		CustomUserDetails userInSession = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (userInSession == null || !userInSession.getUserId().equals(id.toString())) {
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

		CustomUserDetails userInSession = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (userInSession == null || !userInSession.getUserId().equals(id.toString())) {
			model.addAttribute("errorMessage", "Nemaš ovlasti za to!");
			return "customError";
		}

		User user = userRepository.getOne(id);

		if (user == null) {
			model.addAttribute("errorMessage", "Taj korisnik ne postoji!");
			return "customError";
		}

		List<LocationDetails> locationDetails = services.getAllLocationDetails(user.getLocation());

		model.addAttribute("locations", locationDetails);
		model.addAttribute("user", new UserDetailsMore(user));

		return "editUser";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String updateUser(Model model, @PathVariable UUID id, HttpSession session, @Valid EditUserForm editUserForm,
			BindingResult result) {

		UserDetailsMore userInSession = (UserDetailsMore) session.getAttribute("userInSession");

		List<LocationDetails> locationDetails = services.getAllLocationDetails();

		if (userInSession == null || !userInSession.getUserId().equals(id)) {
			model.addAttribute("errorMessage", "Nemaš ovlasti za to!");
			return "customError";
		}

		if (result.hasErrors()) {
			model.addAttribute("locations", locationDetails);
			return "editUser";
		}

		User user = userRepository.findOne(id);

		if (formFactory.editUserFromForm(user, editUserForm)) {
			userRepository.save(user);
			session.setAttribute("userInSession", new UserDetailsMore(user));
		}

		return "redirect:/users/" + id.toString();
	}

	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String deleteUser(Model model, @PathVariable UUID id, HttpSession session) {

		UserDetailsMore userInSession = (UserDetailsMore) session.getAttribute("userInSession");

		if (userInSession == null || !userInSession.getUserId().equals(id)) {
			model.addAttribute("errorMessage", "Nemaš ovlasti za to!");
			return "customError";
		}

		userRepository.delete(userRepository.findOne(id));
		session.invalidate();

		return "nijeUspjelo";
	}

}