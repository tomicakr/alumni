package hr.alumni.web;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hr.alumni.model.User;
import hr.alumni.model.details.CustomUserDetails;
import hr.alumni.model.details.LocationDetails;
import hr.alumni.model.details.UserDetailsMore;
import hr.alumni.model.form.EditUserForm;
import hr.alumni.model.form.RegistrationForm;
import hr.alumni.repository.UserRepository;
import hr.alumni.service.CommonServices;
import hr.alumni.service.FormFactory;
import hr.alumni.service.UserService;

@Controller
@RequestMapping("/register")
public class RegistrationController {

	private final UserRepository userRepository;
	private final FormFactory formFactory;
	private final CommonServices services;
	private final UserService userService;
	private final AuthenticationManager authenticationManager;

	@Autowired
	public RegistrationController(UserRepository userRepository, FormFactory formFactory, CommonServices services,
			UserService userService, AuthenticationManager authenticationManager) {
		this.userRepository = userRepository;
		this.formFactory = formFactory;
		this.services = services;
		this.userService = userService;
		this.authenticationManager = authenticationManager;
	}

	@GetMapping
	public String showRegistrationForm(Model model) {

		//List<LocationDetails> locationDetails = services.getAllLocationDetails();

		//model.addAttribute("locations", locationDetails);
		model.addAttribute("registrationForm", new RegistrationForm());

		return "register";
	}

	@PostMapping
	public String createUser(Model model, @Valid RegistrationForm registrationForm, BindingResult result,
			HttpSession session, HttpServletRequest request) {

		if (result.hasErrors()) {
			model.addAttribute("registrationForm", registrationForm);
			result.getAllErrors().forEach(e -> System.out.println(e.getDefaultMessage()));
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

	/*
	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@PreAuthorize("hasRole('ZAPOSLENIK') || hasRole('ADMINISTRATOR')")
	public ResponseEntity<?> updateUser(@RequestBody List<PatchForm> patchForms, @PathVariable UUID id) {
		Boolean valid = true;

		for (PatchForm patchForm : patchForms) {
			valid = userService.validatePatchForm(patchForm, valid, id);
		}

		System.out.println(valid);
		if (!valid)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		patchForms.forEach(patch -> userService.updateUser(id, patch));
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
*/
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