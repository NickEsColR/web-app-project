package co.edu.icesi.colmenares.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import co.edu.icesi.colmenares.Taller2.Taller2Application;
import co.edu.icesi.colmenares.model.prchasing.Vendor;

@SpringBootTest(classes = Taller2Application.class)
class VendorDaoTest {
	
	@Autowired
	private IVendorDao vendorDao;
	
	
	@Test
	void test() {
		Vendor v = new Vendor();
		v.setVendorid(1);
		v.setName("Test");
		vendorDao.save(v);
		Vendor v2 = vendorDao.findById(1);
		assertEquals(v.getName(), v2.getName());
	}
	@Disabled
	@Test
	void findAllTest() {
		List<Vendor> v = vendorDao.findAll();
		assertEquals(1, v.size());
	}
	@Disabled
	@Test
	void updateAndFindByCreditratingTest() {
		Vendor v = vendorDao.findById(1);
		v.setCreditrating(1);
		vendorDao.update(v);
		List<Vendor> v2 = vendorDao.findByCreditRating(1);
		assertEquals(1, v2.size());
	}
	@Disabled
	@Test
	void deleteTest() {
		vendorDao.delete(vendorDao.findById(1));
		assertNull(vendorDao.findById(1));
	}
}
