package hr.petsonly.repository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import hr.petsonly.model.Pet;
import hr.petsonly.model.Reservation;
import hr.petsonly.model.ReservationStatus;
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
	List<Reservation> findAllByUserId(@Param("owner_id")String userId);
	
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

	@Transactional
	@Query("SELECT r FROM Reservation r WHERE r.sendReminder = TRUE AND r.reservationStatus = :status AND r.executionTime BETWEEN :start AND :end")
	List<Reservation> findAllConfirmedWithinNHoursHelper(@Param("status") ReservationStatus status, @Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
	
	default List<Reservation> findAllConfirmedWithinNHours(Long hours, Long minutes){
		LocalDateTime start = LocalDateTime.now().plusHours(hours);
		LocalDateTime end = start.plusHours(hours).plusMinutes(minutes);
		return findAllConfirmedWithinNHoursHelper(ReservationStatus.CONFIRMED, start, end);
	}
	
	@Transactional
	@Query("SELECT r FROM Reservation r WHERE r.reservationStatus = :status AND time(r.executionTime) >= :to AND time(r.executionTime) <= :from AND r.executionTime >= now()")
	List<Reservation> findAllOpenWithinAvailableHoursHelper(@Param("status") ReservationStatus status, @Param("from") LocalTime from, @Param("to") LocalTime to);
	
	default List<Reservation> findAllOpenWithinAvailableHours(User u){
		LocalTime from = u.getNotAvailableFrom();
		LocalTime to   = u.getNotAvailableTo();
		return findAllOpenWithinAvailableHoursHelper(ReservationStatus.PENDING, from, to);
		
	}
	
	@Transactional
	@Query("SELECT r FROM Reservation r WHERE r.employee = :employee AND r.reservationStatus = :status AND r.executionTime >= now()")
	List<Reservation> findAllByStatusFromEmployeeHelper(@Param("employee") User employee, @Param("status") ReservationStatus status);
	
	default List<Reservation> findAllAcceptedByEmployee(User u){
		return findAllByStatusFromEmployeeHelper(u, ReservationStatus.ACCEPTED);
	}
	
	default List<Reservation> findAllConfirmedByEmployee(User u){
		return findAllByStatusFromEmployeeHelper(u, ReservationStatus.CONFIRMED);
	}
	
	@Transactional
	@Query(value = "SELECT * FROM reservation r WHERE r.reservation_status = :status AND r.execution_time >= now() AND r.execution_time <= date_add(now(), INTERVAL :hour HOUR)", nativeQuery = true)
	List<Reservation> findAllByStatusAndWithinNHours(@Param("status") Integer status, @Param("hour") Integer hour);
}
