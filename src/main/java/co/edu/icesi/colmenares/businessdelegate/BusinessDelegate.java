package co.edu.icesi.colmenares.businessdelegate;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.colmenares.model.security.UserApp;
import co.edu.icesi.colmenares.model.security.UserType;



@Component
public class BusinessDelegate {
	
	public final static String URI = "http://localhost:8080/api/";
	public final static String URI_USER = URI + "users/";
	public final static String URI_ADMIN = URI + "admin/";
	public final static String URI_OPERATOR = URI + "operator/";
	public final static String URI_VENDOR = URI + "vendors/";
	public final static String URI_SHIPMETHOD = URI + "shipmethods/";
	public final static String URI_POH = URI + "purchaseorderheaders/";
	public final static String URI_POD = URI + "purchaseorderdetails/";
	
	private RestTemplate restTemplate;
	
	
	public BusinessDelegate() {
		restTemplate = new RestTemplate();
	}
	
	//User
		public Iterable<UserApp> userFindAll() {
			return restTemplate.getForObject(URI_USER, Iterable.class);
		}
		
		public UserApp userFindById(long id) {
			return restTemplate.getForObject(URI_USER+id, UserApp.class);
		}
		
		public Iterable<UserType> userFindAllTypes() {
			return restTemplate.getForObject(URI_USER+"types", Iterable.class);
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
	
	

}
