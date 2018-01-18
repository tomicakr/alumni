package hr.petsonly.web.api;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import hr.petsonly.repository.UserRepository;
import hr.petsonly.service.UserService;

@RestController
public class UserAPI {

	private Map<String, Function<String, Boolean>> actions;
	
	@Autowired
	private UserService userService;
	
	public UserAPI() {
		actions.put("replace", new Function<String, Boolean>() {

			@Override
			public Boolean apply(String item) {
				
				return true;
			}
		});
	}
}
