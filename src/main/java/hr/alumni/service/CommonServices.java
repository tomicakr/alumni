package hr.alumni.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.alumni.model.User;
import hr.alumni.model.details.UserDetailsBasic;
import hr.alumni.repository.UserRepository;

@Service
public class CommonServices {

	@Autowired
	private UserRepository userRepository;

	public List<UserDetailsBasic> getAllUsersBasicDetails() {
		List<User> allUsersFull = userRepository.findAll();
		List<UserDetailsBasic> allUsers = new ArrayList<>();

		allUsersFull.forEach((user) -> allUsers.add(new UserDetailsBasic(user)));

		return allUsers;
	}
}

