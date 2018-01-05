package hr.petsonly.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import hr.petsonly.model.Pet;
import hr.petsonly.model.User;

@Service
public interface PetRepository extends JpaRepository<Pet, UUID>{
	
	
	List<Pet> findByOwner(User owner);
	
	//NAME
	List<Pet> findByName(String name);
	
	List<Pet> findByNameIgnoreCase(String name);
	
	List<Pet> findByOwnerAndName(User owner, String name);
	
	List<Pet> findByOwnerAndNameIgnoreCase(User owner, String name);
	
	//AGE
	List<Pet> findByAge(int age);
	
	List<Pet> findByOwnerAndAge(User owner, int age);
	
	//SPECIES
	List<Pet> findBySpecies(String species);
	
	List<Pet> findBySpeciesIgnoreCase(String species);
	
	List<Pet> findByOwnerAndSpecies(User owner, String species);
	
	List<Pet> findByOwnerAndSpeciesIgnoreCase(User owner, String species);
	
	//BREED
	List<Pet> findByBreed(String breed);
	
	List<Pet> findByBreedIgnoreCase(String breed);
	
	List<Pet> findByOwnerAndBreed(User owner, String breed);
	
	List<Pet> findByOwnerAndBreedIgnoreCase(User owner, String breed);
	
	//SEX
	List<Pet> findBySex(char sex);
	
	List<Pet> findByOwnerAndSex(User owner, char sex);
	
	//MICROCHIP
	List<Pet> findByMicrochip(String microchip);
	
	List<Pet> findByMicrochipIgnoreCase(String microchip);
	
	List<Pet> findByOwnerAndMicrochip(User owner, String microchip);
	
	List<Pet> findByOwnerAndMicrochipIgnoreCase(User owner, String microchip);
	
	//REMARK
	
	List<Pet> findByRemark(String remark);
	
	List<Pet> findByRemarkIgnoreCase(String remark);
	
	List<Pet> findByOwnerAndRemarkLike(User owner, String remark);
	
}
