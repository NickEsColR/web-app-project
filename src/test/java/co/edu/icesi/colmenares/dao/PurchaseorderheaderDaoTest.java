package co.edu.icesi.colmenares.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import co.edu.icesi.colmenares.Taller2.Taller2Application;
import co.edu.icesi.colmenares.model.prchasing.Purchaseorderheader;

@SpringBootTest(classes = Taller2Application.class)
class PurchaseorderheaderDaoTest {

	@Autowired
	private IPurchaseorderheaderDao pohDao;
	
	@Test
	void test() {
		Purchaseorderheader poh = new Purchaseorderheader();
		poh.setPurchaseorderid(1);
		pohDao.save(poh);
		assertEquals(poh, pohDao.findById(1));
	}
	
	@Test
	void updateTest() {
		Purchaseorderheader poh = pohDao.findById(1);
		poh.setSubtotal(new BigDecimal(1));
		pohDao.update(poh);
		assertEquals(poh.getSubtotal(), pohDao.findById(1).getSubtotal());
	}

	@Test
	void findAllTest() {
		assertEquals(1, pohDao.findAll().size());
	}
	
	@Test
	void findByShipmethodTest() {
		
	}
	
	@Test
	void findByVendorTest() {
		
	}
	
	@Test
	void findWithTwoplusPurchaseorderdetailsTest() {
		
	}
	
	@Test
	void findAllWithSumUnitpricesTest() {
		
	}
	
	@Test
	void deleteTest() {
		pohDao.delete(pohDao.findById(1));
		assertNull(pohDao.findById(1));
	}
}
