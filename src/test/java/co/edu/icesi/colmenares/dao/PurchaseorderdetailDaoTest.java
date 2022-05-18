package co.edu.icesi.colmenares.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import co.edu.icesi.colmenares.Taller2.Taller2Application;
import co.edu.icesi.colmenares.model.prchasing.Purchaseorderdetail;

@SpringBootTest(classes = Taller2Application.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PurchaseorderdetailDaoTest {

	@Autowired
	private IPurchaseorderdetailDao podDao;
	
	@Test
	@Transactional
	@Order(1)
	void test() {
		Purchaseorderdetail pod = new Purchaseorderdetail();
		pod.setId(1);
		pod.setProductid(1);
		podDao.save(pod);
		assertEquals(pod.getId(), podDao.findById(1).getId());
	}
	
	@Test
	@Transactional
	@Order(2)
	void findAllTest() {
		Purchaseorderdetail pod = new Purchaseorderdetail();
		pod.setId(1);
		pod.setOrderqty(1);
		pod.setUnitprice(new BigDecimal(1));
		podDao.save(pod);
		assertEquals(1, podDao.findAll().size());
	}
	
	@Test
	@Transactional
	@Order(3)
	void updateTest() {
		Purchaseorderdetail pod = new Purchaseorderdetail();
		pod.setId(1);
		pod.setOrderqty(1);
		pod.setUnitprice(new BigDecimal(1));
		podDao.save(pod);
		Purchaseorderdetail pod1 = podDao.findById(3);
		pod1.setUnitprice(new BigDecimal(2));
		podDao.update(pod1);
		assertEquals(pod1.getUnitprice(), podDao.findById(3).getUnitprice());
	}
	
	@Test
	@Transactional
	@Order(4)
	void findByUnitpriceTest() {
		Purchaseorderdetail pod = new Purchaseorderdetail();
		pod.setId(1);
		pod.setOrderqty(1);
		pod.setUnitprice(new BigDecimal(1));
		podDao.save(pod);
		assertEquals(1, podDao.findByUnitprice(new BigDecimal(1)).size());
	}
	
	@Test
	@Transactional
	@Order(5)
	void findByProductTest() {
		Purchaseorderdetail pod = new Purchaseorderdetail();
		pod.setId(1);
		pod.setOrderqty(1);
		pod.setUnitprice(new BigDecimal(1));
		pod.setProductid(1);
		podDao.save(pod);
		assertEquals(1, podDao.findByProduct(1).size());
	}
	
	@Test
	@Transactional
	@Order(6)
	void deleteTest() {
		Purchaseorderdetail pod = new Purchaseorderdetail();
		pod.setId(1);
		pod.setOrderqty(1);
		pod.setUnitprice(new BigDecimal(1));
		pod.setProductid(1);
		podDao.save(pod);
		podDao.delete(podDao.findById(6));
		assertNull(podDao.findById(6));
	}
}

