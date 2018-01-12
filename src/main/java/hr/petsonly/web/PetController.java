package hr.petsonly.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hr.petsonly.model.Pet;
import hr.petsonly.model.details.PetDetails;
import hr.petsonly.model.form.PetForm;
import hr.petsonly.repository.PetRepository;
import hr.petsonly.service.FormFactory;

@Controller
@RequestMapping("/users/{id}/pets")
public class PetController {
	
	@Autowired
	private FormFactory formFactory;
	
	@Autowired
	private PetRepository petRepository;
	
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
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PetDetails> addNewPet(@RequestBody @Valid PetForm petForm, BindingResult result, Model model, @PathVariable UUID id) {
	
		if(result.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		petForm.setOwner(id.toString());
		Pet pet = formFactory.createPetFromForm(petForm);
		
		pet = petRepository.save(pet);
		PetDetails petDetails = new PetDetails(pet);
		
		return new ResponseEntity<>(petDetails, HttpStatus.OK);
	} 
	
	@ResponseBody
	@RequestMapping(value = "/{petId}", method = RequestMethod.DELETE)
	public ResponseEntity<PetDetails> deletePet(Model model, @PathVariable UUID petId) {
		
		Pet pet = petRepository.findOne(petId);
		if(pet==null) {
			System.out.println("Ljubimac nije pronađen!");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		PetDetails petDetails = new PetDetails(pet);
		petRepository.delete(petId);
		
		return new ResponseEntity<>(petDetails, HttpStatus.OK);
	}
	
}
