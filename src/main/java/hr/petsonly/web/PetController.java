package hr.petsonly.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import hr.petsonly.repository.UserRepository;

@Controller
@RequestMapping("/users/{id}/pets")
public class PetController {
	
	@Autowired
	private UserRepository userRepository;
	
}
