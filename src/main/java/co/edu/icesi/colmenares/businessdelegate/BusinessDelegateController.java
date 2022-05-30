package co.edu.icesi.colmenares.businessdelegate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.icesi.colmenares.model.prchasing.Purchaseorderdetail;
import co.edu.icesi.colmenares.model.prchasing.Purchaseorderheader;
import co.edu.icesi.colmenares.model.prchasing.Shipmethod;
import co.edu.icesi.colmenares.model.prchasing.Vendor;
import co.edu.icesi.colmenares.model.security.UserApp;
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
	private ShipMethodRestController shipmethodRest;

	@Autowired
	public BusinessDelegateController(VendorRestController vendorRest,ShipMethodRestController shipmethodRest,PurchaseOrderDetailRestController podRest,PurchaseOrderHeaderRestController pohRest,UserRestController userRest) {
		this.userRest = userRest;
		this.podRest = podRest;
		this.pohRest = pohRest;
		this.vendorRest = vendorRest;
		this.shipmethodRest = shipmethodRest;
	}

	//User functionalities
	public Iterable<UserApp> userFindAll() {
		return userRest.getAll();
	}

	public UserApp userFindById(long id) {
		return userRest.getById(id);
	}

	public void userSave(UserApp user) {
		userRest.add(user);
	}

	//Vendor functionalities 

	public void vendorSave(Vendor vendor) {
		vendorRest.add(vendor);
	}

	public Vendor vendorFindById(int id) {
		return vendorRest.getById(id);
	}

	public Iterable<Vendor> vendorFindAll(){
		return vendorRest.getAll();
	}

	public void vendorUpdate(Vendor vendor) {
		vendorRest.update(vendor);
	}

	//Ship method functionalities 

	public void shipmethodSave(Shipmethod shipmethod) {
		shipmethodRest.add(shipmethod);
	}

	public Shipmethod shipmethodFindById(int id) {
		return shipmethodRest.getById(id);
	}

	public Iterable<Shipmethod> shipmethodFindAll(){
		return shipmethodRest.getAll();
	}

	public void vendorUpdate(Shipmethod shipmethod) {
		shipmethodRest.update(shipmethod);
	}

	//Purchase order header functionalities 

	public void pohSave(Purchaseorderheader poh) {
		pohRest.add(poh);
	}

	public Purchaseorderheader pohFindById(int id) {
		return pohRest.getById(id);
	}

	public Iterable<Purchaseorderheader> pohFindAll(){
		return pohRest.getAll();
	}

	public void pohUpdate(Purchaseorderheader poh) {
		pohRest.update(poh);
	}

	//Purchase order header functionalities 

	public void podSave(Purchaseorderdetail pod) {
		podRest.add(pod);
	}

	public Purchaseorderdetail podFindById(int id) {
		return podRest.getById(id);
	}

	public Iterable<Purchaseorderdetail> podFindAll(){
		return podRest.getAll();
	}

	public void podUpdate(Purchaseorderdetail pod) {
		podRest.update(pod);
	}

}
