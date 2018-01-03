package hr.petsonly.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.petsonly.model.Location;

public interface LocationRepository extends JpaRepository<Location, UUID>{

}
