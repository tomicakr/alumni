package hr.alumni.model.form.validation;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import hr.alumni.model.Species;
import hr.alumni.repository.SpeciesRepository;

public class SpeciesValidator implements ConstraintValidator<ValidSpecies, String>{

	@Autowired
	SpeciesRepository sr;
	
	@Override
	public void initialize(ValidSpecies constraintAnnotation) {
	}

	@Override
	public boolean isValid(String species, ConstraintValidatorContext context) {
		return (validateSpecies(species));
	}
	
	private boolean validateSpecies(String species) {
		List<Species> allSpecies = sr.findAll();
		for(Species s : allSpecies){
			if(s.getId().toString().equals(species)){
				return true;
			}
		}
		return false;
	}
}
