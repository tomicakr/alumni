package hr.petsonly.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.petsonly.model.Species;

public interface SpeciesRepository extends JpaRepository<Species, UUID> {
	
	Species findByName(String name);
}
