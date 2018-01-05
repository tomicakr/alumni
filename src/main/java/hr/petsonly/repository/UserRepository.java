package hr.petsonly.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import hr.petsonly.model.User;

@Service
public interface UserRepository extends JpaRepository<User, UUID> {
	
	public User findByEmail(String email);
}
