package hr.petsonly.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hr.petsonly.model.Pet;
import hr.petsonly.model.User;
import hr.petsonly.model.details.PetDetails;
import hr.petsonly.repository.PetRepository;
import hr.petsonly.repository.UserRepository;

@Controller
@RequestMapping("/users/{id}/pets")
public class PetController {
	
	@Autowired
	private PetRepository petRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<PetDetails> showPetList(Model model, @PathVariable UUID id) {
		
		List<Pet> petList = petRepository.findByOwnerId(id.toString());
		System.out.println(Arrays.toString(petList.toArray()));
		
		List<PetDetails> petDetails = new ArrayList<>();
		
		petList.forEach(pet -> {
			petDetails.add(new PetDetails(pet));
		});
		
		return petDetails;
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String showNewPetForm() {
		return "addPet";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String addNewPet(Model model, @PathVariable UUID id, @Valid Pet pet, BindingResult result) {
		
		User user = userRepository.getOne(id);
		
		if(result.hasErrors()) {
			model.addAttribute("errorMessage", "Neispravni podaci za živinu: " + result.toString());
			return "addPet";
		}
		
		user.getPets().add(pet);
		userRepository.save(user);
		
		return "redirect:/profile";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String showUserList(Model model, @PathVariable UUID id) {
		
		petRepository.delete(id);
		
		return "redirect:/profile";
	}
	
	
}
