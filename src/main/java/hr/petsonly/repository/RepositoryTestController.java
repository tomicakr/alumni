package hr.petsonly.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hr.petsonly.model.Location;
import hr.petsonly.model.Pet;
import hr.petsonly.model.User;

@RestController
public class RepositoryTestController {
	
	@Autowired
	LocationRepository lr;
	
	@RequestMapping("/repotest/findAllLocations")
	public String findAll(){
		String result = "";
		List<Location> locs = lr.findAll();
		for(Location l : locs){
			result += l.toString() + "<br>";
		}
		
		return result;
	}
	
	@RequestMapping("/repotest/findByZipCode")
	public String findByZip(){
		String result ="";
		List<Location> locs = lr.findAllByZipCode(10000);
		for(Location l :locs){
			result += l.getLocationName() + "<br>";
		}
		
		return result;
	}

	@RequestMapping("/repotest/addLocation")
	public void addLocation(){
		Location l = new Location();
		l.setLocationName("ST");
		l.setZipCode(20000);
		lr.save(l);
		
	}
	
	///////////////////////////////////////////////////////////////////
	
	@Autowired
	PetRepository pr;
	
	@RequestMapping("/repotest/findByOwnerPet")
	public String findByOwner(){
		String result = "";
		User sodic = ur.findOne(new UUID(0, 0));
		List<Pet> pets = pr.findByOwner(sodic);
		for(Pet p: pets){
			result += p.getName() + "<br>";
		}
		return result;
	}
	
	@RequestMapping("/repotest/findByNamePet")
	public String findByNamePet(){
		String result = "";
		List<Pet> pets = pr.findByName("snjofo");
		for(Pet p: pets){
			result += p.getName() + "<br>";
		}
		return result;
	}
	
	@RequestMapping("/repotest/findByNameIgnorePet")
	public String findByNameIgnorePet(){
		String result = "";
		List<Pet> pets = pr.findByNameIgnoreCase("SNJOFO");
		for(Pet p: pets){
			result += p.getName() + "<br>";
		}
		return result;
	}
	
	@RequestMapping("/repotest/findByOwnerAndNamePet")
	public String findByOwnerAndName(){
		String result = "";
		User sodic = ur.findOne(new UUID(0, 0));
		List<Pet> pets = pr.findByOwnerAndName(sodic, "snjofo");
		for(Pet p: pets){
			result += p.getName() + "<br>";
		}
		return result;
	}
	
	@RequestMapping("/repotest/findByOwnerAndRemarkLikePet")
	public String findByOwnerAndLikeRemark(){
		String result = "";
		User sodic = ur.findOne(new UUID(0, 0));
		List<Pet> pets = pr.findByOwnerAndRemarkLike(sodic, "ne%");
		for(Pet p: pets){
			result += p.getName() + "<br>";
		}
		return result;
	}
	
	
	@Autowired 
	UserRepository ur;
}
