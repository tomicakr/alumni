package hr.petsonly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ComplexQueryRepository{
	
	@Query("SELECT u.name, u.surname, p.name FROM User u INNER JOIN Pet p ON user.user_id")
	List<Object[]> findAllUsersAndPets();
	
	
}
