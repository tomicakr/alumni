package hr.alumni.model.details;

import java.util.UUID;

import org.springframework.stereotype.Component;

import hr.alumni.model.Location;

@Component
public class LocationDetails {

	private UUID id;
	private String name;
	
	public LocationDetails() {
	}
	
	public LocationDetails(Location location) {
		
		this.setId(location.getLocationId());
		this.setName(location.getLocationName());
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
