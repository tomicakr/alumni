package hr.petsonly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.petsonly.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	
	//NAME
	List<Role> findAllByName(String name);
	
	List<Role> findAllByNameIgnoreCase(String name);
	
	List<Role> findAllByNameLike(String name);
	
}
