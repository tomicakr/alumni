package hr.alumni.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.alumni.model.Species;

public interface SpeciesRepository extends JpaRepository<Species, UUID> {
	
	Species findByName(String name);
}
