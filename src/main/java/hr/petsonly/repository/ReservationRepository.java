package hr.petsonly.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.petsonly.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, UUID> {
	
}
