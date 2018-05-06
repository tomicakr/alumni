package hr.alumni.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.alumni.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	
	//NAME
	Role findByName(String name);
	
	Role findByNameIgnoreCase(String name);
	
	List<Role> findAllByNameLike(String name);
	
}
