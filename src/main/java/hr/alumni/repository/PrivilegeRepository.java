package hr.alumni.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.alumni.model.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long>{

	List<Privilege> findAllByName(String name);
	
	List<Privilege> findAllByNameIgnoreCase(String name);
	
	List<Privilege> findAllByNameLike(String name);
}
