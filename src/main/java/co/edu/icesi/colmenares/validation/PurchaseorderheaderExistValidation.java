package co.edu.icesi.colmenares.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import co.edu.icesi.colmenares.model.prchasing.Purchaseorderheader;
import co.edu.icesi.colmenares.service.IPurchaseorderheaderService;

public class PurchaseorderheaderExistValidation implements ConstraintValidator<PurchaseorderheaderExist, Purchaseorderheader> {

	@Autowired
	private IPurchaseorderheaderService pohService;
	
	@Override
	public boolean isValid(Purchaseorderheader value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		return pohService.findById(value.getPurchaseorderid()).isPresent();
	}

}
