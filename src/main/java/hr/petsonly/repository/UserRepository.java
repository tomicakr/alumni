package hr.petsonly.repository;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hr.petsonly.model.Location;
import hr.petsonly.model.User;
@Service
public interface UserRepository extends JpaRepository<User, UUID> {

	User findByUserPid(String userPid);

	List<User> findAllByName(String name);

	List<User> findAllByNameIgnoreCase(String name);

	List<User> findAllByNameLike(String name);

	List<User> findAllBySurname(String surname);

	List<User> findAllBySurnameIgnoreCase(String surname);

	List<User> findAllBySurnameLike(String surname);

	User findByMobilePhone(String mobilePhone);

	User findByMobilePhoneLike(String mobilePhone);

	List<User> findAllByPhone(String phone);

	List<User> findAllByPhoneLike(String phone);

	User findByEmail(String email);

	List<User> findAllByEmailLike(String email);

	List<User> findAllByRemark(String remark);

	List<User> findAllByRemarkIgnoreCase(String remark);

	List<User> findAllByRemarkLike(String remark);

	List<User> findAllByUserMnemonic(String userMnemonic);

	List<User> findAllByUserMnemonicLike(String userMnemonic);

	List<User> findAllByAddress(String address);

	List<User> findAllByAddressIgnoreCase(String address);

	List<User> findAllByAddressLike(String address);

	List<User> findAllByLocation(Location location);

	List<User> findAllByNotificationSetting(int notificationSetting);

	@Query(value = "SELECT COUNT(u.user_id) FROM users u WHERE u.user_mnemonic REGEXP :pattern", nativeQuery = true)
	Long countByUserMnemonic(@Param("pattern") String pattern);
	default User saveWithMnemonic(User entity) {
		String pattern = entity.getName() + entity.getSurname();
		Long num = countByUserMnemonic(pattern);
		entity.setUserMnemonic(pattern + num);
		return this.save(entity);
	}

	@Query(value = "SELECT * FROM users u INNER JOIN users_roles ur ON u.user_id = ur.user_id WHERE role_id = 2", nativeQuery = true)
	List<User> findAllEmployees();

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE users_roles ur SET ur.role_id = 2 WHERE ur.user_id = :user_id", nativeQuery = true)
	void hireUser(@Param("user_id") String userId);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE users_roles ur SET ur.role_id = 1 WHERE ur.user_id = :user_id", nativeQuery = true)
	void fireUser(@Param("user_id") String userId);
}
