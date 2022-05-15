package co.edu.icesi.colmenares.service;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import co.edu.icesi.colmenares.Taller2.Taller2Application;
import co.edu.icesi.colmenares.dao.IVendorDao;
import co.edu.icesi.colmenares.model.person.Businessentity;
import co.edu.icesi.colmenares.model.prchasing.Vendor;
import co.edu.icesi.colmenares.repository.IBusinessentityRepository;
import co.edu.icesi.colmenares.repository.IVendorRepository;

@SpringBootTest(classes = Taller2Application.class)
class VendorServiceTest {

	IBusinessentityRepository businessRepositorymock = Mockito.mock(IBusinessentityRepository.class);
	IVendorDao vendorRepositoryMock = Mockito.mock(IVendorDao.class);
	IVendorService vendorService = new VendorService(businessRepositorymock, vendorRepositoryMock);
	
	@BeforeEach
	void setup() {
		Vendor v2 = new Vendor();
		v2.setVendorid(2);
		v2.setCreditrating(1);
		v2.setPurchasingwebserviceurl("https");
		v2.setName("prueba");
		v2.setBusinessentityid(1);
		Optional<Businessentity> be = Optional.of(new Businessentity());
		be.get().setBusinessentityid(1);
		Optional<Vendor> v = Optional.of(new Vendor());
		v.get().setVendorid(1);;
		Mockito.when(businessRepositorymock.findById(1)).thenReturn(be);
		Mockito.when(vendorRepositoryMock.findById(1)).thenReturn(v.get());
		Mockito.when(vendorRepositoryMock.findById(2)).thenReturn(v2);
		Mockito.when(businessRepositorymock.findById(2)).thenReturn(Optional.ofNullable(null));
	}
	
	@Test
	void testValidCase1() {
		Vendor v2 = new Vendor();
		v2.setVendorid(2);
		v2.setCreditrating(1);
		v2.setPurchasingwebserviceurl("https");
		v2.setName("prueba");
		v2.setBusinessentityid(1);
		vendorService.saveVendor(v2);
		assertAll("Case 1", ()-> {
			assertEquals(1, vendorService.getCreditrating(2));
			assertEquals("https", vendorService.getPurchasingwebserviceurl(2));
			assertEquals("prueba", vendorService.getName(2));
			assertEquals(1, vendorService.getBusinessentityId(2));
		});
	}
	
	@Test
	void testInvalidCase1() {
		Vendor v2 = new Vendor();
		v2.setVendorid(2);
		v2.setCreditrating(0);
		v2.setPurchasingwebserviceurl("https");
		v2.setName("prueba");
		v2.setBusinessentityid(1);	
		assertThrows(IllegalArgumentException.class,() -> vendorService.saveVendor(v2));
	}
	
	@Test
	void testInvalidCase2() {
		Vendor v2 = new Vendor();
		v2.setVendorid(2);
		v2.setCreditrating(-1);
		v2.setPurchasingwebserviceurl("https");
		v2.setName("prueba");
		v2.setBusinessentityid(1);	
		assertThrows(IllegalArgumentException.class,() -> vendorService.saveVendor(v2));
	}
	
	@Test
	void testInvalidCase3() {
		Vendor v2 = new Vendor();
		v2.setVendorid(2);
		v2.setCreditrating(1);
		v2.setPurchasingwebserviceurl("httpx");
		v2.setName("prueba");
		v2.setBusinessentityid(1);	
		assertThrows(IllegalArgumentException.class,() -> vendorService.saveVendor(v2));
	}
	
	@Test
	void testInvalidCase4() {
		Vendor v2 = new Vendor();
		v2.setVendorid(2);
		v2.setCreditrating(1);
		v2.setPurchasingwebserviceurl("https");
		v2.setName(null);
		v2.setBusinessentityid(1);	
		assertThrows(IllegalArgumentException.class,() -> vendorService.saveVendor(v2));
	}
	
	@Test
	void testInvalidCase5() {
		Vendor v2 = new Vendor();
		v2.setVendorid(2);
		v2.setCreditrating(1);
		v2.setPurchasingwebserviceurl("https");
		v2.setName("prueba");
		v2.setBusinessentityid(2);	
		assertThrows(IllegalArgumentException.class,() -> vendorService.saveVendor(v2));
	}
	
	@Test
	void testAddBusinessEntity() {
		vendorService.setBusinessentityId(1,1);
		assertEquals(1, vendorService.getBusinessentityId(1));
	}
	
	@Test
	void testInvalidBusinessEntity() {
		assertThrows(IllegalArgumentException.class,() -> vendorService.setBusinessentityId(2,1), "business entity must exist");
	}
	
	
	@Test
	void testCreateVendor() {
		vendorService.setCreditrating(1,1);
		vendorService.setPurchasingwebserviceurl("https",1);
		vendorService.setName("prueba",1);
		assertAll("Case 1", ()-> {
			assertEquals(1, vendorService.getCreditrating(1));
			assertEquals("https", vendorService.getPurchasingwebserviceurl(1));
			assertEquals("prueba", vendorService.getName(1));
		});
	}
	
	@Test
	void testVendorCreditRating() {
		assertAll("rating invalid", () -> {
			assertThrows(IllegalArgumentException.class, () -> vendorService.setCreditrating(0,1));
			assertThrows(IllegalArgumentException.class, () -> vendorService.setCreditrating(-1,1));
		});
	}
	
	@Test
	void testVendorPurchasingWebSerceurl() {
		assertThrows(IllegalArgumentException.class, () -> vendorService.setPurchasingwebserviceurl("httpx",1),"url must start with https");
	}
	
	@Test
	void testSampleVendorName() {
		assertThrows(IllegalArgumentException.class, () -> vendorService.setName(null,1),"name cant be null");
	}

}
