package co.edu.icesi.colmenares.businessdelegate;

import java.math.BigDecimal;
import java.util.HashSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.colmenares.model.hr.Employee;
import co.edu.icesi.colmenares.model.person.Businessentity;
import co.edu.icesi.colmenares.model.person.Person;
import co.edu.icesi.colmenares.model.prchasing.Purchaseorderdetail;
import co.edu.icesi.colmenares.model.prchasing.Purchaseorderheader;
import co.edu.icesi.colmenares.model.prchasing.Shipmethod;
import co.edu.icesi.colmenares.model.prchasing.Vendor;
import co.edu.icesi.colmenares.model.security.UserApp;
import co.edu.icesi.colmenares.model.security.UserType;
import lombok.Getter;
import lombok.Setter;



@Component
public class BusinessDelegateRestTemplate {

	public final static String URI = "http://localhost:8081/api/";
	public final static String URI_USER = URI + "users/";
	public final static String URI_ADMIN = URI + "admin/";
	public final static String URI_OPERATOR = URI + "operator/";
	public final static String URI_VENDOR = URI + "vendors/";
	public final static String URI_SHIPMETHOD = URI + "shipmethods/";
	public final static String URI_POH = URI + "purchaseorderheaders/";
	public final static String URI_POD = URI + "purchaseorderdetails/";
	public final static String URI_BUS = URI + "businessentities/";
	public final static String URI_PER = URI + "people/";
	public final static String URI_EMP = URI + "employees/";

	@Getter
	@Setter
	private RestTemplate restTemplate;



	public BusinessDelegateRestTemplate() {
		this.restTemplate = new RestTemplate();
		System.out.println(" HYYY");
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
        messageConverters.add(converter);
        this.restTemplate.setMessageConverters(messageConverters);
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
		System.out.println(" MY FFFF");
		
		return restTemplate.getForObject(URI_VENDOR, HashSet.class);
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

	//Businessentity
	
	public Businessentity  businessentitySave(Businessentity businessentity) {
		return restTemplate.postForObject(URI_BUS, new HttpEntity<Businessentity>(businessentity), Businessentity.class);
	}
	
	public Iterable<Businessentity> businessentityFindAll() {
		return restTemplate.getForObject(URI_BUS, Iterable.class);
	}
	
	//Person
	
	public Iterable<Person> personFindAll() {
		return restTemplate.getForObject(URI_PER, Iterable.class);
	}
	
	//Employee
	
	public Iterable<Employee> employeeFindAll() {
		return restTemplate.getForObject(URI_EMP, Iterable.class);
	}




}
