package hr.petsonly.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hr.petsonly.model.User;

public interface ComplexQueryRepository extends JpaRepository<User, UUID>{
	
	@Query(value = "SELECT u.name AS uname, u.surname AS usurname, p.name AS pname FROM users u INNER JOIN pet p ON u.user_id = p.user_id", nativeQuery = true)
	List<Object[]> findAllUsersAndPets();
	
	
}
