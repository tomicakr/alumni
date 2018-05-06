package hr.alumni.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.alumni.model.Location;

public interface LocationRepository extends JpaRepository<Location, UUID>{

	Location findByZipCode(int zipCode);
	
	List<Location> findAllByLocationName(String locationName);
	
	List<Location> findAllByLocationNameIgnoreCase(String locationName);
}