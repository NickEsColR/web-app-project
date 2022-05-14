package co.edu.icesi.colmenares.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import co.edu.icesi.colmenares.Taller2.Taller2Application;
import co.edu.icesi.colmenares.model.prchasing.Purchaseorderdetail;

@SpringBootTest(classes = Taller2Application.class)
class PurchaseorderdetailDaoTest {

	@Autowired
	private IPurchaseorderdetailDao podDao;
	
	@Test
	void test() {
		Purchaseorderdetail pod = new Purchaseorderdetail();
		pod.setId(1);
		pod.setProductid(1);
		podDao.save(pod);
		assertEquals(pod.getId(), podDao.findById(1));
	}
	
	@Test
	void findAllTest() {
		assertEquals(1, podDao.findAll().size());
	}
	
	@Autowired
	void updateTest() {
		Purchaseorderdetail pod = podDao.findById(1);
		pod.setUnitprice(new BigDecimal(1));
		podDao.update(pod);
		assertEquals(pod.getUnitprice(), podDao.findById(1).getUnitprice());
	}
	
	@Test
	void findByUnitpriceTest() {
		assertEquals(1, podDao.findByUnitprice(new BigDecimal(1)).size());
	}
	
	@Test
	void findByProductTest() {
		assertEquals(1, podDao.findByProduct(1).size());
	}
	
	@Test
	void deleteTest() {
		podDao.delete(podDao.findById(1));
		assertNull(podDao.findById(1));
	}
}

