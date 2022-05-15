package co.edu.icesi.colmenares.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.icesi.colmenares.Taller2.Taller2Application;
import co.edu.icesi.colmenares.dao.IPurchaseorderheaderDao;
import co.edu.icesi.colmenares.model.prchasing.Purchaseorderdetail;
import co.edu.icesi.colmenares.model.prchasing.Purchaseorderheader;
import co.edu.icesi.colmenares.repository.IPurchaseorderheaderRepository;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Taller2Application.class)
class PurchaseorderdetailServiceTest2 {

	IPurchaseorderheaderDao pohRepository;
	IPurchaseorderdetailService podService;
	
@Autowired	
	public PurchaseorderdetailServiceTest2(IPurchaseorderheaderDao pohRepository,
			IPurchaseorderdetailService podService) {
		this.pohRepository = pohRepository;
		this.podService = podService;
	}

	@BeforeEach
	void setUp(){
		Purchaseorderdetail pod = new Purchaseorderdetail();
		pod.setId(2);
		pod.setOrderqty(1);
		pod.setUnitprice(new BigDecimal(1));
		Purchaseorderheader poh = new Purchaseorderheader();
		poh.setPurchaseorderid(1);
		pod.setPurchaseorderheader(poh);
		pohRepository.save(poh);
		poh = new Purchaseorderheader();
		poh.setPurchaseorderid(2);
		pohRepository.save(poh);
		podService.savePurchaseorderdetail(pod);
	}

	@Disabled
	@Test
	void testSaveValidcase1() {
		
		assertAll("succesfuly save", () -> {
			assertEquals(1,podService.getOrderqty(2));
			assertEquals(1,podService.getUnitprice(2).intValue());
			assertEquals(1, podService.getPurchaseorderheaderid(2).getPurchaseorderid());
		});
	}
	@Disabled
	@Test
	void testValidPurchaseorderheader() {
		Purchaseorderheader poh = new Purchaseorderheader();
		poh.setPurchaseorderid(2);
		podService.setPurchaseorderheader(poh,1);
		assertEquals(2, podService.getPurchaseorderheaderid(1).getPurchaseorderid());
	}
	@Disabled
	@Test
	void testCreatePurchaseorderdetail() {
		podService.setOrderqty(1,1);
		podService.setUnitprice(new BigDecimal(1),1);
		assertAll("Purchaseorderdetail created",() -> {
			assertEquals(1, podService.getOrderqty(1));
			assertEquals(1, podService.getUnitprice(1).intValue());	
		});
	}
}
