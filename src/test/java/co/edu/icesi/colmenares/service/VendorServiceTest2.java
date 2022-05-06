package co.edu.icesi.colmenares.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.icesi.colmenares.Taller2.Taller2Application;
import co.edu.icesi.colmenares.model.person.Businessentity;
import co.edu.icesi.colmenares.model.prchasing.Vendor;
import co.edu.icesi.colmenares.repository.IBusinessentityRepository;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Taller2Application.class)
class VendorServiceTest2 {
	

	IBusinessentityRepository businessRepository;
	VendorService vendorService;
	
	@Autowired
	public VendorServiceTest2(VendorService vendorService,IBusinessentityRepository businessRepository) {
		this.vendorService = vendorService;
		this.businessRepository = businessRepository;
	}	
	
	@BeforeEach
	void setUp() throws Exception {
		Businessentity be= new Businessentity();
		be.setBusinessentityid(1);
		businessRepository.save(be);
		be= new Businessentity();
		be.setBusinessentityid(2);
		businessRepository.save(be);
		Vendor v2 = new Vendor();
		v2.setVendorid(2);
		v2.setCreditrating(1);
		v2.setPurchasingwebserviceurl("https");
		v2.setName("prueba");
		v2.setBusinessentityid(1);
		vendorService.saveVendor(v2);
	}


	@Disabled
	@Test
	void testValidCase1() {
		assertAll("Case 1", ()-> {
			assertEquals(1, vendorService.getCreditrating(1));
			assertEquals("https", vendorService.getPurchasingwebserviceurl(1));
			assertEquals("prueba", vendorService.getName(1));
			assertEquals(1, vendorService.getBusinessentityId(1));
		});
	}
	
	@Disabled
	@Test
	void testAddBusinessEntity() {
		vendorService.setBusinessentityId(2,1);
		assertEquals(2, vendorService.getBusinessentityId(1));
	}
	
	@Disabled
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
	
	
}
