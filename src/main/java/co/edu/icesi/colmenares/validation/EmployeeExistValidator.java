package co.edu.icesi.colmenares.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import co.edu.icesi.colmenares.service.IEmployeeService;

public class EmployeeExistValidator implements ConstraintValidator<EmployeeExist, Integer> {

	@Autowired
	private IEmployeeService employeeService;
	
	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		return employeeService.findById(value).isPresent();
	}

}
