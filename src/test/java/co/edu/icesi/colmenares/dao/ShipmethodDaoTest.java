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
import co.edu.icesi.colmenares.model.prchasing.Shipmethod;

@SpringBootTest(classes = Taller2Application.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ShipmethodDaoTest {

	@Autowired
	private IShipmethodDao shipmethodDao;
	
	@Test
	@Transactional
	@Order(1)
	void test() {
		Shipmethod s = new Shipmethod();
		s.setName("test");
		shipmethodDao.save(s);
		assertEquals(s.getName(), shipmethodDao.findById(1).getName());
	}

	@Test
	@Transactional
	@Order(2)
	void findAllTest() {
		Shipmethod s = new Shipmethod();
		s.setName("test");
		s.setShipbase(new BigDecimal(1));
		s.setShiprate(new BigDecimal(1));
		shipmethodDao.save(s);
		assertEquals(1, shipmethodDao.findAll().size());
	}
	
	@Test
	@Transactional
	@Order(3)
	void updateTest() {
		Shipmethod s = new Shipmethod();
		s.setName("test");
		s.setShipbase(new BigDecimal(1));
		s.setShiprate(new BigDecimal(1));
		shipmethodDao.save(s);
		Shipmethod s1 = shipmethodDao.findById(3);
		s1.setName("update");
		shipmethodDao.update(s1);
		assertEquals(s1.getName(), shipmethodDao.findById(3).getName());
	}
	
	@Test
	@Transactional
	@Order(4)
	void deleteTest() {
		Shipmethod s = new Shipmethod();
		s.setName("test");
		s.setShipbase(new BigDecimal(1));
		s.setShiprate(new BigDecimal(1));
		shipmethodDao.save(s);
		shipmethodDao.delete(shipmethodDao.findById(4));
		assertNull(shipmethodDao.findById(4));
	}
}
