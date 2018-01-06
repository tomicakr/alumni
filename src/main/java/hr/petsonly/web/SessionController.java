package hr.petsonly.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import hr.petsonly.model.User;
import hr.petsonly.model.details.UserDetailsBasic;
import hr.petsonly.model.details.UserDetailsMore;
import hr.petsonly.repository.UserRepository;

@Controller
@RequestMapping("/sessions")
public class SessionController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String showLoginPage() {
		
		return "login";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String loginUser(Model model, HttpSession httpSession, @RequestParam String email,
			@RequestParam String password) {

		User user = userRepository.findByEmail(email);

		if (user == null) {
			model.addAttribute("errorMessage", "Korisnik sa e-mail adresom ' " + email + " ' ne postoji!");
			return "login";
		}

		if (!user.getPassword().equals(password)) {
			model.addAttribute("errorMessage", "Pogrešna lozinka!");
			return "login";
		}

		UserDetailsMore userDetails = new UserDetailsMore(user);
		
		httpSession.setAttribute("userInSession", userDetails);

		return "redirect:/profile";
	}

	@RequestMapping(value = "/", method = RequestMethod.DELETE)
	public String logoutUser(Model model, HttpSession httpSession) {
		
		httpSession.invalidate();

		return "redirect:/index";
	}

}