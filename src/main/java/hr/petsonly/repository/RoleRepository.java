package hr.petsonly.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.petsonly.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

}
