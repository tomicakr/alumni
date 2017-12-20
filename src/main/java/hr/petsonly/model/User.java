package hr.petsonly.model;

import java.time.LocalTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

	private Long userPid;
	private String name;
	private String surname;
	private String mobilePhone;
	private String phone;
	private String email;
	private int role;
	private String pass;
	private String remark;
	private String userMnemonic;
	private String address;
	private Location location;
	private int notificationSetting;
	private LocalTime notAvailableFrom;
	private LocalTime notAvailableTo;
	private List<Pet> pets;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getUserPid() {
		return userPid;
	}

	public void setUserPid(Long userPid) {
		this.userPid = userPid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUserMnemonic() {
		return userMnemonic;
	}

	public void setUserMnemonic(String userMnemonic) {
		this.userMnemonic = userMnemonic;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "location")
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
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
	
	@OneToMany(mappedBy = "owner")
	public List<Pet> getPets() {
		return pets;
	}
	
	public void setPets(List<Pet> pets) {
		this.pets = pets;
	}
	
	public void addPet(Pet pet) {
		this.pets.add(pet);
		if(pet.getOwner() != this) {
			pet.setOwner(this);
		}
	}

}
