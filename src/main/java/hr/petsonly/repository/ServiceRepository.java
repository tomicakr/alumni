package hr.petsonly.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.petsonly.model.Service;

public interface ServiceRepository extends JpaRepository<Service, UUID>{
	
	//NAME
	List<Service> findAllByName(String name);
	
	List<Service> findAllByNameIgnoreCase(String name);
	
	List<Service> findAllByNameLike(String name);
	
	//PRICE
	List<Service> findAllByPrice(double price);
	
	//DESCRIPTION
	List<Service> findAllByDescription(String description);
	
	List<Service> findAllByDescriptionIgnoreCase(String description);
	
	List<Service> findAllByDescriptionLike(String description);
	
}
