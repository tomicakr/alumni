package hr.alumni.model.details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hr.alumni.model.Location;
import hr.alumni.model.User;
import hr.alumni.repository.LocationRepository;

@Component
public class UserDetailsMore extends UserDetailsBasic{


    private String city;
    private String address;
    private String userPid;
    private String mobilePhone;
    private String telephone;
    private Location location;
    private String workingTime;
    private String notificationSetting;

    @Autowired
    private LocationRepository locationRepository;

    public UserDetailsMore(){
        super();
    }

    public UserDetailsMore(User user) {
        super(user);

        Location location = user.getLocation();

        this.address = user.getAddress();
        
        this.telephone = user.getPhone();
        this.city = location.getLocationName();
        this.location = user.getLocation();

       

       


    }
    public String getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(String workingTime) {
        this.workingTime = workingTime;
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

    public String getNotificationSetting() {
        return notificationSetting;
    }

    public void setNotificationSetting(String notificationSetting) {
        this.notificationSetting = notificationSetting;
    }
}