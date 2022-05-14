package co.edu.icesi.colmenares.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import co.edu.icesi.colmenares.Taller2.Taller2Application;
import co.edu.icesi.colmenares.model.prchasing.Shipmethod;

@SpringBootTest(classes = Taller2Application.class)
class ShipmethodDaoTest {

	@Autowired
	private IShipmethodDao shipmethodDao;
	
	@Test
	void test() {
		Shipmethod s = new Shipmethod();
		s.setShipmethodid(1);
		shipmethodDao.save(s);
		assertEquals(s, shipmethodDao.findById(1));
	}

	@Test
	void findAllTest() {
		assertEquals(1, shipmethodDao.findAll().size());
	}
	
	@Test
	void updateTest() {
		Shipmethod s = shipmethodDao.findById(1);
		s.setName("update");
		shipmethodDao.update(s);
		assertEquals(s.getName(), shipmethodDao.findById(1).getName());
	}
	
	@Test
	void deleteTest() {
		shipmethodDao.delete(shipmethodDao.findById(1));
		assertNull(shipmethodDao.findById(1));
	}
}
