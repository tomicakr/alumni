package hr.alumni.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.alumni.model.Location;
import hr.alumni.model.details.LocationDetails;
import hr.alumni.repository.LocationRepository;

@Service
public class LocationService {

	@Autowired
	private LocationRepository locationRepository;

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
}
