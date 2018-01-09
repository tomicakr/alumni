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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import hr.petsonly.model.Location;
import hr.petsonly.model.User;
import hr.petsonly.model.details.LocationDetails;
import hr.petsonly.model.details.UserDetailsBasic;
import hr.petsonly.model.details.UserDetailsMore;
import hr.petsonly.model.form.EditUserForm;
import hr.petsonly.model.form.RegistrationForm;
import hr.petsonly.repository.LocationRepository;
import hr.petsonly.repository.UserRepository;
import hr.petsonly.service.FormFactory;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private FormFactory formFactory;

	@Autowired
	private LocationRepository locationRepository;

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
	public String showRegistrationForm(Model model) {

		List<Location> locations = locationRepository.findAll();
		List<LocationDetails> locationDetails = new ArrayList<>();
		locations.forEach(location -> locationDetails.add(new LocationDetails(location)));

		model.addAttribute("locations", locationDetails);

		return "register";
	}

	@PostMapping
	public String createUser(Model model, @Valid RegistrationForm registrationForm, BindingResult result,
			HttpSession session) {

		System.out.println(result);
		System.out.println(registrationForm);

		if (result.hasErrors()) {
			model.addAttribute("registrationForm", registrationForm);
			model.addAttribute("errorMessage", "Registracija nije valjana");
			return "register";
		}

		User user = formFactory.createUserFromForm(registrationForm);
		user = userRepository.save(user);

		UserDetailsMore userDetails = new UserDetailsMore(user);
		session.setAttribute("userInSession", userDetails);

		return "redirect:/users/" + user.getUserId();
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

		if (user == null) {
			model.addAttribute("errorMessage", "Taj korisnik ne postoji!");
			return "customError";
		}

		List<Location> locations = locationRepository.findAll();
		List<LocationDetails> locationDetails = new ArrayList<>();
		locations.forEach(location -> locationDetails.add(new LocationDetails(location)));

		model.addAttribute("locations", locationDetails);
		model.addAttribute("user", user);

		return "editUser";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String updateUser(Model model, @PathVariable UUID id, HttpSession session, @Valid EditUserForm editUserForm,
			BindingResult result) {

		UserDetailsMore userInSession = (UserDetailsMore) session.getAttribute("userInSession");
		
		List<Location> locations = locationRepository.findAll();
		List<LocationDetails> locationDetails = new ArrayList<>();
		locations.forEach(location -> locationDetails.add(new LocationDetails(location)));
		
		if (userInSession == null || !userInSession.getUserId().equals(id)) {
			model.addAttribute("errorMessage", "Nemaš ovlasti za to!");
			return "customError";
		}

		if (result.hasErrors()) {
			model.addAttribute("errorMessage", "");
			return "editUser";
		}

		User user = userRepository.findOne(id);

		if (editUserForm.getMobilePhone().isEmpty()) {
			model.addAttribute("errorMessage", "Broj mobitela ne smije biti prazan!");
			model.addAttribute("user", user);
			model.addAttribute("locations", locationDetails);
			return "editUser";
		}

		if (!user.getPassword().equals(editUserForm.getOldPassword())) {
			model.addAttribute("errorMessage", "Lozinka nije ispravna!");
			model.addAttribute("user", user);
			model.addAttribute("locations", locationDetails);
			return "editUser";
		}

		if (formFactory.editUserFromForm(user, editUserForm)) {
			userRepository.save(user);
			UserDetailsMore userDetails = new UserDetailsMore(user);
			session.setAttribute("userInSession", userDetails);
		}
		;

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