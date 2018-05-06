package hr.alumni.model.details;

import java.util.UUID;

import hr.alumni.model.Service;

public class ServiceDetails {
	
	private UUID id;
	private String name;
	private Double price;

	public ServiceDetails(Service service) {
		this.setId(service.getServiceKey());
		this.setName(service.getName());
		this.setPrice(service.getPrice());
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
