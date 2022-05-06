package co.edu.icesi.colmenares.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import co.edu.icesi.colmenares.service.IPersonService;

public class PersonExistValidation implements ConstraintValidator<PersonExist, Integer> {

	@Autowired
	private IPersonService personService;
	
	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		return personService.findById(value).isPresent();
	}

}
