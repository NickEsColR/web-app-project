package co.edu.icesi.colmenares.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.icesi.colmenares.service.IBusinessentityService;

@Component
public class BusinessentityExistValidation implements ConstraintValidator<BusinessentityExist, Integer> {

	@Autowired
	private IBusinessentityService businessentityService;
	
	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		return businessentityService.findById(value).isPresent();
	}

}
