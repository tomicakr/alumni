package hr.petsonly.repository;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hr.petsonly.model.Pet;
import hr.petsonly.model.Reservation;
import hr.petsonly.model.Service;
import hr.petsonly.model.User;

public interface ReservationRepository extends JpaRepository<Reservation, UUID> {
	
	//SERVICE
	List<Reservation> findAllByService(Service service);
	
	//PET
	List<Reservation> findAllByPet(Pet pet);
	
	//USER
	List<Reservation> findAllByUser(User user);
	
	@Query(value = "SELECT * FROM reservation r WHERE r.owner_id = :owner_id", nativeQuery = true)
	List<Reservation> findAllByUserId(String userId);
	
	//RESERVATION_STATUS
	List<Reservation> findAllByReservationStatus(int reservationStatus);
	
	List<Reservation> findAllByUserAndReservationStatus(User user, int reservationStatus);
	
	List<Reservation> findAllByUserAndService(User user, Service service);
	
	//EMPLOYEE
	List<Reservation> findAllByEmployee(User employee);
	
	//RESERVATION_TIME
	List<Reservation> findAllByReservationTime(LocalTime reservationTime);
	
	//EXECUTION_TIME
	List<Reservation> findAllByExecutionTime(LocalTime executionTime);
	
	//DURATION
	List<Reservation> findAllByDuration(double duration);
	
	//PRICE
	List<Reservation> findAllByPrice(double price);
	
	//SEND_REMINDER
	List<Reservation> findAllBySendReminderTrue();
	
	//DOCUMENT_PATH
	List<Reservation> findAllByDocumentPath(String documentPath);
	
	List<Reservation> findAllByDocumentPathLike(String documentPath);
}
