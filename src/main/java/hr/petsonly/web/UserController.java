package hr.petsonly.web;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import hr.petsonly.model.User;
import hr.petsonly.model.details.CustomUserDetails;
import hr.petsonly.model.details.LocationDetails;
import hr.petsonly.model.details.UserDetailsBasic;
import hr.petsonly.model.details.UserDetailsMore;
import hr.petsonly.model.form.EditUserForm;
import hr.petsonly.model.form.PatchForm;
import hr.petsonly.model.form.RegistrationForm;
import hr.petsonly.repository.UserRepository;
import hr.petsonly.service.CommonServices;
import hr.petsonly.service.FormFactory;
import hr.petsonly.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

	private final UserRepository userRepository;
	private final FormFactory formFactory;
	private final CommonServices services;
	private final UserService userService;
	private final AuthenticationManager authenticationManager;

	@Autowired
	public UserController(UserRepository userRepository, FormFactory formFactory, CommonServices services, UserService userService, AuthenticationManager authenticationManager) {
		this.userRepository = userRepository;
		this.formFactory = formFactory;
		this.services = services;
		this.userService = userService;
		this.authenticationManager = authenticationManager;
	}

	@GetMapping
	@PreAuthorize("hasRole('ADMINISTRATOR')")
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
			HttpSession session, HttpServletRequest request) {

		if (result.hasErrors()) {
			model.addAttribute("registrationForm", registrationForm);
			model.addAttribute("locations", services.getAllLocationDetails());

			return "register";
		}

		if (registrationForm.getAddress().isEmpty()) {

		}

		User user = userService.registerNewUserAccount(registrationForm);

		if (user == null) {
			model.addAttribute("registrationForm", registrationForm);
			model.addAttribute("locations", services.getAllLocationDetails());

			result.rejectValue("email", "email.already.exists");

			System.out.println(result);
			return "register";
		}

		authenticateUserAndSetSession(registrationForm, request);

		return "redirect:/users/" + user.getUserId();
	}

	@GetMapping("/{id}")
	@PreAuthorize("@webSecurityConfig.checkUserId(authentication, #id)")
	public String showUserProfile(Model model, @PathVariable UUID id, HttpSession session) {
		CustomUserDetails userInSession = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();

		if (userInSession == null
				|| !(userInSession.getUserId().equals(id) || userInSession.getRoles().contains("ROLE_ADMINISTRATOR"))) {
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
	@PreAuthorize("@webSecurityConfig.checkUserId(authentication, #id)")
	public String editUser(Model model, @PathVariable UUID id, HttpSession session) {

		CustomUserDetails userInSession = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();

		if (userInSession == null
				|| !(userInSession.getUserId().equals(id) || userInSession.getRoles().contains("ROLE_ADMINISTRATOR"))) {
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
	@PreAuthorize("@webSecurityConfig.checkUserId(authentication, #id)")
	public String updateUser(Model model, @PathVariable UUID id, HttpSession session, @Valid EditUserForm editUserForm,
			BindingResult result) {

		CustomUserDetails userInSession = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();

		List<LocationDetails> locationDetails = services.getAllLocationDetails();

		if (userInSession == null
				|| !(userInSession.getUserId().equals(id) || userInSession.getRoles().contains("ROLE_ADMINISTRATOR"))) {
			model.addAttribute("errorMessage", "Nemaš ovlasti za to!");
			return "customError";
		}

		User user = userRepository.getOne(id);
		if (result.hasErrors()) {
			model.addAttribute("user", new UserDetailsMore(user));
			model.addAttribute("editUserForm", editUserForm);
			model.addAttribute("locations", locationDetails);

			return "editUser";
		}
		
		if (formFactory.editUserFromForm(user, editUserForm)) {
			userRepository.save(user);
		}

		return "redirect:/users/" + id.toString();
	}

	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("@webSecurityConfig.checkUserId(authentication, #id)")
	public String deleteUser(Model model, @PathVariable UUID id, HttpSession session) {
		CustomUserDetails userInSession = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();

		if (userInSession == null
				|| !(userInSession.getUserId().equals(id) || userInSession.getRoles().contains("ROLE_ADMINISTRATOR"))) {
			model.addAttribute("errorMessage", "Nemaš ovlasti za to!");
			return "customError";
		}

		userRepository.delete(userRepository.findOne(id));

		return "nijeUspjelo";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void updateUser(@RequestBody List<PatchForm> patchForms,
			@PathVariable UUID id) {
		patchForms.forEach(patch -> userService.updateUser(id, patch.getOp(), patch.getPath(), patch.getValue()));
	}

	private void authenticateUserAndSetSession(RegistrationForm rform, HttpServletRequest request) {
		String username = rform.getEmail();
		String password = rform.getPassword();
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

		// generate session if one doesn't exist
		request.getSession();

		token.setDetails(new WebAuthenticationDetails(request));
		Authentication authenticatedUser = authenticationManager.authenticate(token);
		SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
	}

}