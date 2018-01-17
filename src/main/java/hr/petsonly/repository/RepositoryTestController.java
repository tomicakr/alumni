package hr.petsonly.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hr.petsonly.model.Location;
import hr.petsonly.model.Pet;
import hr.petsonly.model.Reservation;
import hr.petsonly.model.User;
import hr.petsonly.service.email.EmailServiceImpl;

@RestController
public class RepositoryTestController {

	@Autowired
	LocationRepository lr;

	@RequestMapping("/repotest/findAllLocations")
	public String findAll() {
		String result = "";
		List<Location> locs = lr.findAll();
		for (Location l : locs) {
			result += l.toString() + "<br>";
		}

		return result;
	}

	@RequestMapping("/repotest/findByZipCode")
	public String findByZip() {
		String result = "";
		Location l = lr.findByZipCode(10000);

		result += l.getLocationName() + "<br>";

		return result;
	}

	@RequestMapping("/repotest/addLocation")
	public void addLocation() {
		Location l = new Location();
		l.setLocationName("ST");
		l.setZipCode(20000);
		lr.save(l);

	}

	///////////////////////////////////////////////////////////////////

	@Autowired
	PetRepository pr;

	@RequestMapping("/repotest/findByOwnerPet")
	public String findByOwner() {
		String result = "";
		User sodic = ur.findOne(new UUID(0, 0));
		List<Pet> pets = pr.findByOwner(sodic);
		for (Pet p : pets) {
			result += p.getName() + "<br>";
		}
		return result;
	}

	@RequestMapping("/repotest/findByNamePet")
	public String findByNamePet() {
		String result = "";
		List<Pet> pets = pr.findByName("snjofo");
		for (Pet p : pets) {
			result += p.getName() + "<br>";
		}
		return result;
	}

	@RequestMapping("/repotest/findByNameIgnorePet")
	public String findByNameIgnorePet() {
		String result = "";
		List<Pet> pets = pr.findByNameIgnoreCase("SNJOFO");
		for (Pet p : pets) {
			result += p.getName() + "<br>";
		}
		return result;
	}

	
	@RequestMapping("/repotest/findByOwnerAndNamePet")
	public String findByOwnerAndName() {
		String result = "";
		User sodic = ur.findOne(new UUID(0, 0));
		List<Pet> pets = pr.findByOwnerAndName(sodic, "snjofo");
		for (Pet p : pets) {
			result += p.getName() + "<br>";
		}
		return result;
	}

	@RequestMapping("/repotest/findByOwnerAndRemarkLikePet")
	public String findByOwnerAndLikeRemark() {
		String result = "";
		User sodic = ur.findOne(new UUID(0, 0));
		List<Pet> pets = pr.findByOwnerAndRemarkLike(sodic, "ne%");
		for (Pet p : pets) {
			result += p.getName() + "<br>";
		}
		return result;
	}

	@RequestMapping("/repotest/mnemonic")
	public String findByMnemonic() {
		String result = "";

		List<Pet> pets = pr.findByOwnerMnemonic("mp");
		for (Pet p : pets) {
			result += p.getName() + " " + p.getOwner().getUserMnemonic() + " "
					+ p.getOwner().getUserId() + "<br>";
		}
		return result;
	}

	@RequestMapping("/repotest/umn")
	public String findByMnemonicAndName() {
		String result = "";

		List<Pet> pets = pr.findByOwnerMnemonicAndName("fs", "Šnaucer");
		for (Pet p : pets) {
			result += p.getName() + " " + p.getOwner().getUserMnemonic() + " "
					+ p.getOwner().getUserId() + "<br>";
		}
		return result;
	}

	@RequestMapping("/repotest/petuuidowner")
	public String findByOwnerUiid() {
		String result = "";

		List<Pet> pets = pr.findByOwnerId("fb8f4014-e0b0-4a5e-9079-320c1e1516e8");
		System.out.println(pets.size());
		for (Pet p : pets) {
			result += p.getName() + " "  + p.getOwner().getUserMnemonic() + " "
					+ p.getOwner().getUserId() + "<br>";
		}
		return result;
	}

	@Autowired
	UserRepository ur;

	@RequestMapping("/repotest/count")
	public String findByOwnerCount() {
		String result = "";

		Long count = ur.countByUserMnemonic("fs");
		result += count + "<br>";
		Long count2 = ur.countByUserMnemonic("kk");
		result += count2;

		return result;
	}

	@Autowired
	ComplexQueryRepository cqr;
	
	@RequestMapping("/repotest/custom")
	public String findOwnerAndAnimal(){
		String result = "";
		List<Object[]> list = cqr.findAllUsersAndPets();
		for(Object[] o : list){
			result += o[0] + " " + o[1] + " "  + o[2] + "<br>";
		}
		return result;
	}
	
	@RequestMapping("/repotest/addPet")
	public void addPet(){
		User owner = ur.getOne(UUID.fromString("48c2ad9c-c37a-4dbc-a823-11adbbe11769"));
		Pet p = new Pet();
		p.setPetKey(UUID.randomUUID());
		p.setAge(1);
		p.setMicrochip("ABXDESD");
		p.setName("snjofo");
		p.setOwner(owner);
		p.setRemark("");
		p.setSex('f');
		
		if(false){
			owner.getPets().add(p);
			ur.save(owner);
		} else {
			pr.save(p);
		}
	}
	
	@RequestMapping("/repotest/employee")
	public String findAllEmployees(){
		String result = "";
		List<User> list = ur.findAllEmployees();
		for(User o : list){
			result += o.getName() + "<br>";
		}
		return result;
	}
	
	@Autowired
	PrivilegeRepository prir;
	
	@RequestMapping("/repotest/deletep")
	public String deletep(){
		String result = ""; 
		//prir.delete((long) 1);
		pr.delete(UUID.fromString("4234a45d-9220-4576-8e94-0d3b0e2c6eaf"));
		return result;
	}
	
	@RequestMapping("/repotest/hire")
	public String hire(){
		String result = "";
		ur.hireUser("0e070ca1-4950-40ff-a1d7-8f65e6f559bf");
		return result;
	}
	@Autowired
	ReservationRepository rr;
	@Autowired
	EmailServiceImpl esi;
	@RequestMapping("/repotest/send")
	public String sendMail(){
		String result = "";
		//esi.sendSimpleMessage("mate.paulinovic@fer.hr", "PROBA", "Poslano iz aplikacije");
		esi.sendReservationOffer(rr.getOne(UUID.fromString("372110c8-7141-432c-bc2f-95c6af824b3d")));
		return result;
	}
	
	@RequestMapping("/repotest/reservations")
	public String findReservationsWithinTime(){
		String result = "";
		List<Reservation> rs = rr.findAllConfirmedWithinNHours((long) 5, (long) 15);
		for(Reservation r : rs){
			result += r.getPet().getName() + " " + r.getExecutionTime() + "<br>";
		}
		return result;
	}
	
}
