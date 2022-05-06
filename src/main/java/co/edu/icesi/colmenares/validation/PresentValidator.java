package co.edu.icesi.colmenares.validation;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PresentValidator implements ConstraintValidator<PresentConstraint, LocalDate> {

	@Override
	public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		LocalDate now = LocalDate.now();
		return now.equals(value);
	}

}
