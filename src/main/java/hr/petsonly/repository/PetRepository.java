package hr.petsonly.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import hr.petsonly.model.Pet;
import hr.petsonly.model.User;

@Service
public interface PetRepository extends JpaRepository<Pet, UUID>{
	
	//OWNER
	List<Pet> findByOwner(User owner);
	
	@Query(value = "SELECT * FROM pet p WHERE p.user_id = :user_id", nativeQuery = true)
	List<Pet> findByOwnerId(@Param("user_id") String userId);
	
	@Query(value = "SELECT * "
			+ "FROM pet p INNER JOIN users u ON p.user_id = u.user_id "
			+ "WHERE u.user_mnemonic = :user_mnemonic", nativeQuery = true)
	List<Pet> findByOwnerMnemonic(@Param("user_mnemonic") String userMnemonic);
	
	//NAME
	List<Pet> findByName(String name);
	
	List<Pet> findByNameIgnoreCase(String name);
	
	List<Pet> findByOwnerAndName(User owner, String name);
	
	@Query(value = "SELECT * "
			+ "FROM pet p INNER JOIN users u ON p.user_id = u.user_id "
			+ "WHERE u.user_mnemonic = :user_mnemonic AND p.name = :name", nativeQuery = true)
	List<Pet> findByOwnerMnemonicAndName(@Param("user_mnemonic") String userMnemonic, @Param("name") String name);
	
	List<Pet> findByOwnerAndNameIgnoreCase(User owner, String name);
	
	//AGE
	List<Pet> findByAge(int age);
	
	List<Pet> findByOwnerAndAge(User owner, int age);
	
	//SPECIES
	/*
	List<Pet> findBySpecies(String species);
	
	List<Pet> findBySpeciesIgnoreCase(String species);
	
	List<Pet> findByOwnerAndSpecies(User owner, String species);
	
	List<Pet> findByOwnerAndSpeciesIgnoreCase(User owner, String species);
	*/
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
