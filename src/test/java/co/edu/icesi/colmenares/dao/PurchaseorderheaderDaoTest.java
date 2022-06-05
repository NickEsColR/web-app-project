package co.edu.icesi.colmenares.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import co.edu.icesi.colmenares.Taller2.Taller2Application;
import co.edu.icesi.colmenares.model.prchasing.Purchaseorderdetail;
import co.edu.icesi.colmenares.model.prchasing.Purchaseorderheader;
import co.edu.icesi.colmenares.model.prchasing.Shipmethod;
import co.edu.icesi.colmenares.model.prchasing.Vendor;

@SpringBootTest(classes = Taller2Application.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PurchaseorderheaderDaoTest {

	@Autowired
	private IPurchaseorderheaderDao pohDao;
	
	@Autowired
	private IShipmethodDao shipmethodDao;
	
	@Autowired
	private IVendorDao vendorDao;
	@Autowired
	IPurchaseorderdetailDao podDao;
	
	@Test
	@Transactional
	@Order(1)
	void test() {
		Purchaseorderheader poh = new Purchaseorderheader();
		poh.setPurchaseorderid(1);
		pohDao.save(poh);
		assertEquals(poh.getPurchaseorderid(), pohDao.findById(1).getPurchaseorderid());
	}
	
	@Test
	@Transactional
	@Order(2)
	void updateTest() {
		Purchaseorderheader poh = new Purchaseorderheader();
		poh.setPurchaseorderid(1);
		poh.setSubtotal(new BigDecimal(1));
		pohDao.save(poh);
		Purchaseorderheader poh1 = pohDao.findById(2);
		poh1.setSubtotal(new BigDecimal(1));
		pohDao.update(poh1);
		assertEquals(poh1.getSubtotal(), pohDao.findById(2).getSubtotal());
	}

	@Test
	@Transactional
	@Order(3)
	void findAllTest() {
		Purchaseorderheader poh = new Purchaseorderheader();
		poh.setPurchaseorderid(1);
		poh.setSubtotal(new BigDecimal(1));
		pohDao.save(poh);
		assertEquals(1, pohDao.findAll().size());
	}
	@Test
	@Transactional
	@Order(5)
	void findByShipmethodTest() {
		Shipmethod s = new Shipmethod();
		s.setName("test");
		s.setShipbase(new BigDecimal(1));
		s.setShiprate(new BigDecimal(1));
		shipmethodDao.save(s);
		s = shipmethodDao.findById(1);
		Purchaseorderheader poh = new Purchaseorderheader();
		poh.setPurchaseorderid(1);
		poh.setSubtotal(new BigDecimal(1));
		poh.setShipmethod(s);
		pohDao.save(poh);
		List<Purchaseorderheader> pl = new ArrayList<>();
		pl.add(pohDao.findById(5));
		s.setPurchaseorderheaders(pl);
		shipmethodDao.save(s);
		assertEquals(1, pohDao.findByShipmethod(s).size());
	}
	@Test
	@Transactional
	@Order(6)
	void findByVendorTest() {
		Vendor v = new Vendor();
		v.setBusinessentityid(1);
		v.setCreditrating(1);
		v.setName("prueba");
		vendorDao.save(v);
		v = vendorDao.findById(1);
		Purchaseorderheader poh = new Purchaseorderheader();
		poh.setPurchaseorderid(1);
		poh.setSubtotal(new BigDecimal(1));
		poh.setVendor(v);
		pohDao.save(poh);
		List<Purchaseorderheader> pl = new ArrayList<>();
		pl.add(pohDao.findById(6));
		v.setPurchaseorderheaders(pl);
		vendorDao.save(v);
		assertEquals(1, pohDao.findByVendor(v).size());
	}

	@Test
	@Transactional
	@Order(8)
	void findWithTwoplusPurchaseorderdetailsTest() {
		Purchaseorderheader poh = new Purchaseorderheader();
		poh.setPurchaseorderid(1);
		poh.setSubtotal(new BigDecimal(1));
		pohDao.save(poh);
		poh = pohDao.findById(8);
		Purchaseorderdetail pod = new Purchaseorderdetail();
		pod.setId(1);
		pod.setOrderqty(1);
		pod.setUnitprice(new BigDecimal(1));
		pod.setPurchaseorderheader(poh);
		podDao.save(pod);
		pod = new Purchaseorderdetail();
		pod.setId(1);
		pod.setOrderqty(1);
		pod.setUnitprice(new BigDecimal(1));
		pod.setPurchaseorderheader(poh);
		podDao.save(pod);
		List<Purchaseorderdetail> pl = new ArrayList<Purchaseorderdetail>();
		pl.add(podDao.findById(3));
		pl.add(podDao.findById(4));
		poh.setPurchaseorderdetails(pl);
		pohDao.save(poh);
		assertEquals(1, pohDao.findWithTwoplusPurchaseorderdetails().size());
	}
	@Test
	@Transactional
	@Order(7)
	void findAllWithSumUnitpricesTest() {
		Purchaseorderheader poh = new Purchaseorderheader();
		poh.setPurchaseorderid(1);
		poh.setSubtotal(new BigDecimal(1));
		poh.setOrderdate(LocalDate.of(2022, 4, 20));
		pohDao.save(poh);
		poh = pohDao.findById(7);
		Purchaseorderdetail pod = new Purchaseorderdetail();
		pod.setId(1);
		pod.setOrderqty(1);
		pod.setUnitprice(new BigDecimal(1));
		pod.setPurchaseorderheader(poh);
		podDao.save(pod);
		pod = new Purchaseorderdetail();
		pod.setId(1);
		pod.setOrderqty(1);
		pod.setUnitprice(new BigDecimal(1));
		pod.setPurchaseorderheader(poh);
		podDao.save(pod);
		List<Purchaseorderdetail> pl = new ArrayList<Purchaseorderdetail>();
		pl.add(podDao.findById(1));
		pl.add(podDao.findById(2));
		poh.setPurchaseorderdetails(pl);
		pohDao.save(poh);
		List<Object> list = pohDao.findAllWithSumUnitprices(LocalDate.of(2022, 4, 10),LocalDate.of(2022, 4, 30));
		assertEquals(1, list.size());
	}
	
	@Test
	@Transactional
	@Order(4)
	void deleteTest() {
		Purchaseorderheader poh = new Purchaseorderheader();
		poh.setPurchaseorderid(1);
		poh.setSubtotal(new BigDecimal(1));
		pohDao.save(poh);
		pohDao.delete(pohDao.findById(4));
		assertNull(pohDao.findById(4));
	}
}
