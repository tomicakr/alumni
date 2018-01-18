package hr.petsonly.web.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hr.petsonly.model.details.UserDetailsBasic;
import hr.petsonly.repository.UserRepository;

@RestController
public class UserAPI {

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/api/users")
	public ResponseEntity<List<UserDetailsBasic>> getUsers(@RequestParam("role") String role) {
		
		List<UserDetailsBasic> usersWithRole;
		//TODO ovo treba
		return null;
	}
}
