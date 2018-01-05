package hr.petsonly.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.petsonly.model.Location;

public interface LocationRepository extends JpaRepository<Location, UUID>{

	List<Location> findAllByZipCode(int zipCode);
	
	List<Location> findAllByLocationName(String locationName);
	
	List<Location> findAllByLocationNameIgnoreCase(String locationName);
}