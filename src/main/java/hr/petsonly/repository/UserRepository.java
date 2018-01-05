package hr.petsonly.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import hr.petsonly.model.Location;
import hr.petsonly.model.User;

@Service
public interface UserRepository extends JpaRepository<User, UUID> {
	
	//USER_PID
	User findByUserPid(String userPid);
		
	//NAME
	List<User> findAllByName(String name);
	
	List<User> findAllByNameIgnoreCase(String name);
	
	List<User> findAllByNameLike(String name);
	
	//SURNAME
	List<User> findAllBySurname(String surname);
	
	List<User> findAllBySurnameIgnoreCase(String surname);
	
	List<User> findAllBySurnameLike(String surname);
	
	//MOBILE_PHONE
	User findByMobilePhone(String mobilePhone);
	
	User findByMobilePhoneLike(String mobilePhone);
	
	//PHONE
	List<User> findAllByPhone(String phone);
	
	List<User> findAllByPhoneLike(String phone);
	
	//EMAIL
	User findByEmail(String email);
	
	List<User> findAllByEmailLike(String email);
	
	
	//REMARK
	List<User> findAllByRemark(String remark);
	
	List<User> findAllByRemarkIgnoreCase(String remark);
	
	List<User> findAllByRemarkLike(String remark);
	
	//USER_MNEMONIC
	List<User> findAllByUserMnemonic(String userMnemonic);
	
	List<User> findAllByUserMnemonicLike(String userMnemonic);	
	
	//ADDRESS
	List<User> findAllByAddress(String address);
	
	List<User> findAllByAddressIgnoreCase(String address);
	
	List<User> findAllByAddressLike(String address);
	
	//LOCATION
	List<User> findAllByLocation(Location location);
	
	//NOTIFICATION_SETTING
	List<User> findAllByNotificationSetting(int notificationSetting);
	

}
