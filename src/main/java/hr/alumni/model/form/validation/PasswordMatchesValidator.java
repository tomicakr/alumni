package hr.alumni.model.form.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import hr.alumni.model.form.EditUserForm;
import hr.alumni.model.form.RegistrationForm;

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

			return rForm.getPassword() == null || rForm.getPassword().equals(rForm.getPassword2());
		}

		return false;
	}
}