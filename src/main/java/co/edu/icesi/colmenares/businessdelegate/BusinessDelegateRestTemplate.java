package co.edu.icesi.colmenares.businessdelegate;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.colmenares.model.prchasing.Purchaseorderdetail;
import co.edu.icesi.colmenares.model.prchasing.Purchaseorderheader;
import co.edu.icesi.colmenares.model.prchasing.Shipmethod;
import co.edu.icesi.colmenares.model.prchasing.Vendor;
import co.edu.icesi.colmenares.model.security.UserApp;
import co.edu.icesi.colmenares.model.security.UserType;



@Component
public class BusinessDelegateRestTemplate {

	public final static String URI = "http://localhost:8080/api/";
	public final static String URI_USER = URI + "users/";
	public final static String URI_ADMIN = URI + "admin/";
	public final static String URI_OPERATOR = URI + "operator/";
	public final static String URI_VENDOR = URI + "vendors/";
	public final static String URI_SHIPMETHOD = URI + "shipmethods/";
	public final static String URI_POH = URI + "purchaseorderheaders/";
	public final static String URI_POD = URI + "purchaseorderdetails/";

	private RestTemplate restTemplate;



	public BusinessDelegateRestTemplate() {
		restTemplate = new RestTemplate();
	}

	//User
	public Iterable<UserApp> userFindAll() {
		return restTemplate.getForObject(URI_USER, Iterable.class);
	}

	public UserApp userFindById(long id) {
		return restTemplate.getForObject(URI_USER+id, UserApp.class);
	}

	public UserApp userSave(UserApp user) {
		return restTemplate.postForObject(URI_USER, new HttpEntity<UserApp>(user), UserApp.class);
	}

	public void userUpdate(UserApp user) {
		restTemplate.put(URI_USER, user, UserApp.class);
	}

	public void userDelete(long id) {
		restTemplate.delete(URI_USER+id);
	}

	//Vendor

	public Iterable<Vendor> vendorFindAll() {
		return restTemplate.getForObject(URI_VENDOR, Iterable.class);
	}

	public Vendor vendorFindById(long id) {
		return restTemplate.getForObject(URI_VENDOR+id, Vendor.class);
	}

	public Iterable<Vendor> vendorFindByCreditRating() {
		return restTemplate.getForObject(URI_VENDOR+"creditrating", Iterable.class);
	}

	public Vendor vendorSave(Vendor vendor) {
		return restTemplate.postForObject(URI_VENDOR, new HttpEntity<Vendor>(vendor), Vendor.class);
	}

	public void vendorUpdate(Vendor vendor) {
		restTemplate.put(URI_VENDOR, vendor, Vendor.class);
	}

	public void vendorDelete(long id) {
		restTemplate.delete(URI_VENDOR+id);
	}

	//ShipMethod

	public Iterable<Shipmethod> shipmethodFindAll() {
		return restTemplate.getForObject(URI_SHIPMETHOD, Iterable.class);
	}

	public Shipmethod shipmethodFindById(long id) {
		return restTemplate.getForObject(URI_SHIPMETHOD+id, Shipmethod.class);
	}


	public Shipmethod shipmethodSave(Shipmethod shipmethod) {
		return restTemplate.postForObject(URI_SHIPMETHOD, new HttpEntity<Shipmethod>(shipmethod), Shipmethod.class);
	}

	public void shipmethodUpdate(Shipmethod shipmethod) {
		restTemplate.put(URI_SHIPMETHOD, shipmethod, Shipmethod.class);
	}

	public void shipmethodDelete(long id) {
		restTemplate.delete(URI_SHIPMETHOD+id);
	}

	//Purchase order detail

	public Iterable<Purchaseorderdetail> purchaseorderdetailFindAll() {
		return restTemplate.getForObject(URI_POD, Iterable.class);
	}
	
	public Iterable<Purchaseorderdetail> purchaseorderdetailFindByUnitprice(BigDecimal price) {
		return restTemplate.getForObject(URI_POD+"get/"+price, Iterable.class);
	}
	
	public Iterable<Purchaseorderdetail> purchaseorderdetailFindByProduct(int product) {
		return restTemplate.getForObject(URI_POD+"get/"+product, Iterable.class);
	}

	public Purchaseorderdetail purchaseorderdetailFindById(long id) {
		return restTemplate.getForObject(URI_POD+id, Purchaseorderdetail.class);
	}


	public Purchaseorderdetail purchaseorderdetailSave(Purchaseorderdetail purchaseorderdetail) {
		return restTemplate.postForObject(URI_POD, new HttpEntity<Purchaseorderdetail>(purchaseorderdetail), Purchaseorderdetail.class);
	}

	public void purchaseorderdetailUpdate(Purchaseorderdetail purchaseorderdetail) {
		restTemplate.put(URI_POD, purchaseorderdetail, Purchaseorderdetail.class);
	}

	public void purchaseorderdetailDelete(long id) {
		restTemplate.delete(URI_POD+id);
	}

	//Purchase order header

	public Iterable<Purchaseorderheader> purchaseorderheaderFindAll() {
		return restTemplate.getForObject(URI_POH, Iterable.class);
	}

	public Purchaseorderheader  purchaseorderheaderFindById(long id) {
		return restTemplate.getForObject(URI_POH+id, Purchaseorderheader.class);
	}

	public Iterable<Purchaseorderheader>  purchaseorderheaderFindByVendor(Vendor vendor) {
		return restTemplate.getForObject(URI_POH+"get/"+vendor.getBusinessentityid(), Iterable.class);
	}

	public Iterable<Purchaseorderheader>  purchaseorderheaderFindByShipmethod(Shipmethod shipmethod) {
		return restTemplate.getForObject(URI_POH+"get/"+shipmethod.getShipmethodid(), Iterable.class);
	}

	public Iterable<Purchaseorderheader>  purchaseorderheaderFindWithTwoplusPurchaseorderdetails() {
		return restTemplate.getForObject(URI_POH+"get/POD", Iterable.class);
	}

	public Iterable<Purchaseorderheader>  purchaseorderheaderFindAllWithSumUnitprices(LocalDate start, LocalDate end) {
		return restTemplate.getForObject(URI_POH+"get/"+start+"/"+end, Iterable.class);
	}

	public Purchaseorderheader  purchaseorderheaderSave(Purchaseorderheader purchaseorderheader) {
		return restTemplate.postForObject(URI_POH, new HttpEntity<Purchaseorderheader>(purchaseorderheader), Purchaseorderheader.class);
	}

	public void purchaseorderheaderUpdate(Purchaseorderheader purchaseorderheader) {
		restTemplate.put(URI_POH, purchaseorderheader, Purchaseorderheader.class);
	}

	public void  purchaseorderheaderDelete(long id) {
		restTemplate.delete(URI_POH+id);
	}



	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}



}
