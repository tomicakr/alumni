package hr.alumni.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String showLoginPage(Model model, @RequestParam(value = "error", required = false) String error) {
		if(error != null) {
			model.addAttribute("errorMessage", "Pogrešna kombinacija elektroničke pošte i lozinke.");
		}

		return "login";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String logoutUser(Model model, HttpSession httpSession) {
		httpSession.invalidate();
		return "redirect:/index";
	}

}