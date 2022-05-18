package co.edu.icesi.colmenares.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import co.edu.icesi.colmenares.Taller2.Taller2Application;
import co.edu.icesi.colmenares.model.prchasing.Vendor;

@SpringBootTest(classes = Taller2Application.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
class VendorDaoTest {
	
	@Autowired
	private IVendorDao vendorDao;
	
	
	@Test
	@Transactional
	@Order(1)
	void test() {
		Vendor v = new Vendor();
		v.setName("Test");
		vendorDao.save(v);
		Vendor v2 = vendorDao.findById(1);
		assertEquals(v.getName(), v2.getName());
	}
	@Transactional
	@Test
	@Order(2)
	void findAllTest() {
		Vendor v = new Vendor();
		v.setBusinessentityid(1);
		v.setCreditrating(1);
		v.setName("prueba");
		vendorDao.save(v);
		List<Vendor> vl = vendorDao.findAll();
		assertEquals(1, vl.size());
	}
	@Transactional
	@Test
	@Order(3)
	void updateAndFindByCreditratingTest() {
		Vendor v = new Vendor();
		v.setBusinessentityid(1);
		v.setCreditrating(1);
		v.setName("prueba");
		vendorDao.save(v);
		Vendor v1 = vendorDao.findById(3);
		v1.setCreditrating(1);
		vendorDao.update(v1);
		List<Vendor> v2 = vendorDao.findByCreditRating(1);
		assertEquals(1, v2.size());
	}
	@Transactional
	@Test
	@Order(4)

	void deleteTest() {
		Vendor v = new Vendor();
		v.setBusinessentityid(1);
		vendorDao.save(v);
		vendorDao.delete(vendorDao.findById(4));
		assertNull(vendorDao.findById(4));
	}
}
