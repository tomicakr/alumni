package hr.petsonly.model.details;

import java.util.UUID;

import hr.petsonly.model.Service;

public class ServiceDetails {
	
	private UUID id;
	private String name;

	public ServiceDetails(Service service) {
		this.setId(service.getServiceKey());
		this.setName(service.getName());
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
