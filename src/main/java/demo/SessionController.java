package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sessions")
public class SessionController {

	@Autowired
	LoginService service;

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String showLoginPage(Model model) {
		return "login";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseBody
	public String loginUser(Model model, @RequestParam String name, @RequestParam String password) {
		
		if(service.validateUser(name, password)) {
			return String.format("Bok %s, tvoj pass je %s! heheheh", name, password);
		};
		
		return "krivo ti je nes";
	}

}