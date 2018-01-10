package hr.petsonly.model.form.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import hr.petsonly.model.form.EditUserForm;
import hr.petsonly.model.form.RegistrationForm;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

	@Override
	public void initialize(PasswordMatches constraintAnnotation) {
	}

	@Override
	public boolean isValid(Object obj, ConstraintValidatorContext context) {
		if (obj instanceof RegistrationForm) {
			RegistrationForm rForm = (RegistrationForm) obj;

			return rForm.getPassword().equals(rForm.getPassword2());
		}

		if (obj instanceof EditUserForm) {
			EditUserForm rForm = (EditUserForm) obj;

			return rForm.getPassword().equals(rForm.getPassword2());
		}

		return false;
	}
}