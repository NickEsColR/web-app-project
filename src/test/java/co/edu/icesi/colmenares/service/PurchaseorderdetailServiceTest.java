package co.edu.icesi.colmenares.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import co.edu.icesi.colmenares.Taller2.Taller2Application;
import co.edu.icesi.colmenares.model.prchasing.Purchaseorderdetail;
import co.edu.icesi.colmenares.model.prchasing.Purchaseorderheader;
import co.edu.icesi.colmenares.repository.IPurchaseorderdetailRepository;
import co.edu.icesi.colmenares.repository.IPurchaseorderheaderRepository;

@SpringBootTest(classes = Taller2Application.class)
class PurchaseorderdetailServiceTest {
	
	static IPurchaseorderheaderRepository pohRepositoryMock = Mockito.mock(IPurchaseorderheaderRepository.class);
	static IPurchaseorderdetailRepository purchaseorderdetailRepositoryMock = Mockito.mock(IPurchaseorderdetailRepository.class);
	IPurchaseorderdetailService podService = new PurchaseorderdetailService(purchaseorderdetailRepositoryMock, pohRepositoryMock);
	
	@BeforeEach
	void setUp(){
		Optional<Purchaseorderheader> poh = Optional.of(new Purchaseorderheader());
		poh.get().setPurchaseorderid(1);
		Optional<Purchaseorderdetail> pod2 = Optional.of(new Purchaseorderdetail());
		java.lang.Integer podid = java.lang.Integer.valueOf(1);
		pod2.get().setId(podid);
		Purchaseorderdetail pod = new Purchaseorderdetail();
		pod.setId(2);
		pod.setOrderqty(1);
		pod.setUnitprice(new BigDecimal(1));
		pod.setPurchaseorderheader(poh.get());
		Mockito.when(pohRepositoryMock.findById(1)).thenReturn(poh);
		Mockito.when(purchaseorderdetailRepositoryMock.findById(1)).thenReturn(pod2);
		Mockito.when(purchaseorderdetailRepositoryMock.findById(2)).thenReturn(Optional.of(pod));
		Mockito.when(pohRepositoryMock.findById(2)).thenReturn(Optional.ofNullable(null));
	}
	
	@Test
	void testSaveValidcase1() {
		Purchaseorderdetail pod = new Purchaseorderdetail();
		pod.setId(2);
		pod.setOrderqty(1);
		pod.setUnitprice(new BigDecimal(1));
		Purchaseorderheader poh = new Purchaseorderheader();
		poh.setPurchaseorderid(1);
		pod.setPurchaseorderheader(poh);
		podService.savePurchaseorderdetail(pod);
		assertAll("succesfuly save", () -> {
			assertEquals(1,podService.getOrderqty(2));
			assertEquals(1,podService.getUnitprice(2).intValue());
			assertEquals(1, podService.getPurchaseorderheaderid(2).getPurchaseorderid());
		});
	}
	
	@Test
	void testSaveInvalidcase1() {
		Purchaseorderdetail pod = new Purchaseorderdetail();
		pod.setOrderqty(0);
		pod.setUnitprice(new BigDecimal(1));
		Purchaseorderheader poh = new Purchaseorderheader();
		poh.setPurchaseorderid(1);
		pod.setPurchaseorderheader(poh);
		assertThrows(IllegalArgumentException.class, () -> podService.savePurchaseorderdetail(pod));
	}
	
	@Test
	void testSaveInvalidcase2() {
		Purchaseorderdetail pod = new Purchaseorderdetail();
		pod.setOrderqty(-1);
		pod.setUnitprice(new BigDecimal(1));
		Purchaseorderheader poh = new Purchaseorderheader();
		poh.setPurchaseorderid(1);
		pod.setPurchaseorderheader(poh);
		assertThrows(IllegalArgumentException.class, () -> podService.savePurchaseorderdetail(pod));
	}
	
	@Test
	void testSaveInvalidcase3() {
		Purchaseorderdetail pod = new Purchaseorderdetail();
		pod.setOrderqty(1);
		pod.setUnitprice(new BigDecimal(0));
		Purchaseorderheader poh = new Purchaseorderheader();
		poh.setPurchaseorderid(1);
		pod.setPurchaseorderheader(poh);
		assertThrows(IllegalArgumentException.class, () -> podService.savePurchaseorderdetail(pod));
	}
	
	@Test
	void testSaveInvalidcase4() {
		Purchaseorderdetail pod = new Purchaseorderdetail();
		pod.setOrderqty(1);
		pod.setUnitprice(new BigDecimal(-1));
		Purchaseorderheader poh = new Purchaseorderheader();
		poh.setPurchaseorderid(1);
		pod.setPurchaseorderheader(poh);
		assertThrows(IllegalArgumentException.class, () -> podService.savePurchaseorderdetail(pod));
	}
	
	@Test
	void testSaveInvalidSavePurchaseorderheder() {
		Purchaseorderdetail pod = new Purchaseorderdetail();
		pod.setOrderqty(0);
		pod.setUnitprice(new BigDecimal(1));
		Purchaseorderheader poh = new Purchaseorderheader();
		poh.setPurchaseorderid(2);
		pod.setPurchaseorderheader(poh);
		assertThrows(IllegalArgumentException.class, () -> podService.savePurchaseorderdetail(pod));
	}
	
	@Test
	void testValidPurchaseorderheader() {
		Purchaseorderheader poh = new Purchaseorderheader();
		poh.setPurchaseorderid(1);
		podService.setPurchaseorderheader(poh,1);
		assertEquals(1, podService.getPurchaseorderheaderid(1).getPurchaseorderid());
	}
	
	@Test
	void testInvalidPurchaseorderheader() {
		Purchaseorderheader poh = new Purchaseorderheader();
		poh.setPurchaseorderid(2);
		assertThrows(IllegalArgumentException.class,() -> podService.setPurchaseorderheader(poh, 1));
	}
	
	@Test
	void testCreatePurchaseorderdetail() {
		podService.setOrderqty(1,1);
		podService.setUnitprice(new BigDecimal(1),1);
		assertAll("Purchaseorderdetail created",() -> {
			assertEquals(1, podService.getOrderqty(1));
			assertEquals(1, podService.getUnitprice(1).intValue());	
		});
	}
	
	@Test
	void testPurchaseorderdetailOrderqty() {
		assertAll("validate orderqty",() -> {
			assertThrows(IllegalArgumentException.class, () -> podService.setOrderqty(0,1));
			assertThrows(IllegalArgumentException.class, () -> podService.setOrderqty(-1,1));
		});
	}
	
	@Test
	void testPurchaseorderdetailUnitprice() {
		assertAll("validate unit price",() -> {
			assertThrows(IllegalArgumentException.class, () -> podService.setUnitprice(new BigDecimal(0),1));
			assertThrows(IllegalArgumentException.class, () -> podService.setUnitprice(new BigDecimal(-1),1));
		});
	}
}
