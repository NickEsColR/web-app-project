package co.edu.icesi.colmenares.businessdelegate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.icesi.colmenares.restController.PurchaseOrderDetailRestController;
import co.edu.icesi.colmenares.restController.PurchaseOrderHeaderRestController;
import co.edu.icesi.colmenares.restController.ShipMethodRestController;
import co.edu.icesi.colmenares.restController.UserRestController;
import co.edu.icesi.colmenares.restController.VendorRestController;


@Component
public class BusinessDelegateController {

	//User Rest Controller
	private UserRestController userRest;

	//Operator Rest Controller
	private PurchaseOrderHeaderRestController pohRest;
	private PurchaseOrderDetailRestController podRest;

	//Administrator Rest Controller
	private VendorRestController vendorRest;
	private ShipMethodRestController shipMethodRest;
	
	@Autowired
	public BusinessDelegateController(VendorRestController vendorRest,ShipMethodRestController shipMethodRest,PurchaseOrderDetailRestController podRest,PurchaseOrderHeaderRestController pohRest,UserRestController userRest) {
		this.userRest = userRest;
		this.podRest = podRest;
		this.pohRest = pohRest;
		this.vendorRest = vendorRest;
		this.shipMethodRest = shipMethodRest;
	}
	
	

}
