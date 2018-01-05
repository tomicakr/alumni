package hr.petsonly.web;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hr.petsonly.model.Pet;
import hr.petsonly.repository.PetRepository;

@Controller
@RequestMapping("/users/{id}/pets")
public class PetController {
	
	@Autowired
	private PetRepository petRepository;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Pet> showPetList(Model model, @PathVariable UUID id) {
		List<Pet> petList = petRepository.findByAge(2);
		return petList;

	}
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String showNewPetForm() {
		return "newPetForm";
	}
	
	/*@RequestMapping(value = "/", method = RequestMethod.POST)
	public String addNewPet(Model model) {

	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String showUserList(Model model) {

	}*/
	
	
}
