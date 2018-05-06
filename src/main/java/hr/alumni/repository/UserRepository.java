package hr.alumni.repository;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import hr.alumni.model.Location;
import hr.alumni.model.User;
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
}
