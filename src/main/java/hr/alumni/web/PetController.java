package hr.alumni.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import hr.alumni.model.Pet;
import hr.alumni.model.details.PetDetails;
import hr.alumni.model.form.PetForm;
import hr.alumni.repository.PetRepository;
import hr.alumni.service.FormFactory;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Controller
@PreAuthorize("@webSecurityConfig.checkUserId(authentication, #id)")
@RequestMapping("/users/{id}/pets")
public class PetController {

	private final FormFactory formFactory;
	private final PetRepository petRepository;

	@Autowired
	public PetController(FormFactory formFactory, PetRepository petRepository) {
		this.formFactory = formFactory;
		this.petRepository = petRepository;
	}

	@ResponseBody
	@GetMapping
	public List<PetDetails> showPetList(@PathVariable UUID id) {

		List<Pet> petList = petRepository.findByOwnerId(id.toString());
		System.out.println(Arrays.toString(petList.toArray()));

		List<PetDetails> petDetails = new ArrayList<>();

		petList.forEach(pet -> {
			petDetails.add(new PetDetails(pet));
		});

		return petDetails;
	}

	@GetMapping("/new")
	public String showNewPetForm() {
		return "addPet";
	}

	@ResponseBody
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PetDetails> addNewPet(@RequestBody @Valid PetForm petForm,
												BindingResult result,
												@PathVariable UUID id) {
		if (result.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		petForm.setOwner(id);
		Pet pet = formFactory.createPetFromForm(petForm);

		pet = petRepository.save(pet);
		PetDetails petDetails = new PetDetails(pet);

		return new ResponseEntity<>(petDetails, HttpStatus.OK);
	}

	@ResponseBody
	@DeleteMapping("/{petId}")
	public ResponseEntity<?> deletePet(@PathVariable UUID petId, @PathVariable UUID id) {

		Pet pet = petRepository.findOne(petId);
		if (pet == null) {
			System.out.println("Ljubimac nije pronađen!");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		petRepository.delete(petId);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
