package hr.petsonly.model;

import javax.persistence.*;
import java.util.UUID;
import java.util.List;

@Entity
@Table(name = "location")
public class Location {

	@Id
	@Column
	@GeneratedValue
	private UUID locationId;
	
	@Column
	private int zipCode;
	
	@Column
	private String locationName;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "location")
	private List<User> users;
	public Location() {
	}

	public UUID getLocationId() {
		return locationId;
	}

	public void setLocationId(UUID locationId) {
		this.locationId = locationId;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

}
