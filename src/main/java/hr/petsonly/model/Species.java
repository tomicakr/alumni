package hr.petsonly.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="species")
public class Species{
	
	@Id
	@Type(type="uuid-char")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private UUID id;
	
	@Column
	private String name;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "species", cascade = CascadeType.ALL)
	private List<Pet> pets;
	
	public Species(){
		
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
