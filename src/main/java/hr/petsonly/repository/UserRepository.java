package hr.petsonly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import hr.petsonly.model.User;

@Service
public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findByEmail(String email);
}
