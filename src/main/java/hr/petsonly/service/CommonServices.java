package hr.petsonly.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.petsonly.model.Location;
import hr.petsonly.model.User;
import hr.petsonly.model.details.LocationDetails;
import hr.petsonly.model.details.UserDetailsBasic;
import hr.petsonly.repository.LocationRepository;
import hr.petsonly.repository.UserRepository;

@Service
public class CommonServices {

	@Autowired
	private LocationRepository locationRepository;

	@Autowired
	private UserRepository userRepository;

	public List<LocationDetails> getAllLocationDetails() {
		List<Location> locations = locationRepository.findAll();
		List<LocationDetails> locationDetails = new ArrayList<>();
		
		locations.forEach(location -> locationDetails.add(new LocationDetails(location)));

		return locationDetails;
	}
	
	public List<LocationDetails> getAllLocationDetails(Location except) {
		List<Location> locations = locationRepository.findAll();
		locations.remove(except);
		List<LocationDetails> locationDetails = new ArrayList<>();
		
		locations.forEach(location -> locationDetails.add(new LocationDetails(location)));

		return locationDetails;
	}

	public List<UserDetailsBasic> getAllUsersBasicDetails() {
		List<User> allUsersFull = userRepository.findAll();
		List<UserDetailsBasic> allUsers = new ArrayList<>();

		allUsersFull.forEach((user) -> allUsers.add(new UserDetailsBasic(user)));

		return allUsers;
	}
}
