package hr.alumni.repository;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import hr.alumni.model.User;
@Service
public interface UserRepository extends JpaRepository<User, UUID> {


	User findByEmail(String email);

}
