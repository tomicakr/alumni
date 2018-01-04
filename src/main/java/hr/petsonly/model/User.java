package hr.petsonly.model;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID userId;

	@Column
	private String userPid;

	@Column
	private String name;

	@Column
	private String surname;

	@Column
	private String mobilePhone;

	@Column
	private String phone;

	@Column
	private String email;

	@Column
	private String password;

	@Column
	private String remark;

	@Column
	private String userMnemonic;

	@Column
	private String address;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "locationId")
	private Location location;

	@Column
	private int notificationSetting;

	@Column
	private LocalTime notAvailableFrom;

	@Column
	private LocalTime notAvailableTo;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "owner", cascade = CascadeType.ALL)
	private List<Pet> pets;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user" , cascade = CascadeType.ALL)
	private List<Reservation> reservations;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee" , cascade = CascadeType.ALL)
	private List<Reservation> tasks;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userId"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private List<Role> roles;

	public User() {
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public String getUserPid() {
		return userPid;
	}

	public void setUserPid(String userPid) {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Pet> getPets() {
		return pets;
	}

	public void setPets(List<Pet> pets) {
		this.pets = pets;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public List<Reservation> getTasks() {
		return tasks;
	}

	public void setTasks(List<Reservation> tasks) {
		this.tasks = tasks;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}
