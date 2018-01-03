package hr.petsonly.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.petsonly.model.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long>{

}
