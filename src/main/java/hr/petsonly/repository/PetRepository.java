package hr.petsonly.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import hr.petsonly.model.Pet;

@Service
public interface PetRepository extends JpaRepository<Pet, UUID>{

}
