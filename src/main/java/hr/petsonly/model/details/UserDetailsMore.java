package hr.petsonly.model.details;

import hr.petsonly.model.Location;
import hr.petsonly.model.User;
import hr.petsonly.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class UserDetailsMore extends UserDetailsBasic{

	
	private String city;
	private String address;
	private String userPid;
	private String mobilePhone;
	private String telephone;
	private Location location;
	private int notificationSetting;
	private LocalTime notAvailableFrom;
	private LocalTime notAvailableTo;
	@Autowired
	private LocationRepository locationRepository;

	public UserDetailsMore(){
		super();
	}

	public UserDetailsMore(User user) {
		super(user);

		Location location = user.getLocation();

		this.address = user.getAddress();
		this.userPid = user.getUserPid();
		this.mobilePhone = user.getMobilePhone();
		this.telephone = user.getPhone();
		this.city = location.getLocationName();
		this.location = user.getLocation();
		this.notificationSetting = user.getNotificationSetting();
		this.notAvailableFrom = user.getNotAvailableFrom();
		this.notAvailableTo = user.getNotAvailableTo();

	}

	public int getNotificationSetting() {
		return notificationSetting;
	}

	public void setNotificationSetting(int notificationSetting) {
		this.notificationSetting = notificationSetting;
	}

	public LocalTime getNotAvailableFrom() {
		return notAvailableFrom;
	}

	public void setNotAvailableFrom(LocalTime notAvailableFrom) {
		this.notAvailableFrom = notAvailableFrom;
	}

	public LocalTime getNotAvailableTo() {
		return notAvailableTo;
	}
	
	public void setNotAvailableTo(LocalTime notAvailableTo) {
		this.notAvailableTo = notAvailableTo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUserPid() {
		return userPid;
	}

	public void setUserPid(String userPid) {
		this.userPid = userPid;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public LocationRepository getLocationRepository() {
		return locationRepository;
	}

	public void setLocationRepository(LocationRepository locationRepository) {
		this.locationRepository = locationRepository;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

}