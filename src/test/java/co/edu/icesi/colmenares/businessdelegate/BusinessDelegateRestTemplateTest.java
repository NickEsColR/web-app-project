package co.edu.icesi.colmenares.businessdelegate;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import co.edu.icesi.colmenares.model.prchasing.Purchaseorderdetail;
import co.edu.icesi.colmenares.model.prchasing.Purchaseorderheader;
import co.edu.icesi.colmenares.model.prchasing.Shipmethod;
import co.edu.icesi.colmenares.model.prchasing.Vendor;
import co.edu.icesi.colmenares.model.security.UserApp;
import co.edu.icesi.colmenares.model.security.UserType;

class BusinessDelegateRestTemplateTest {

	
	BusinessDelegateRestTemplate delegate = Mockito.mock(BusinessDelegateRestTemplate.class);
	
	@BeforeEach
	void setUp(){
		//user
		UserApp u = new UserApp();
		u.setUsername("prueba");
		u.setPassword("prueba");
		u.setType(UserType.admin.toString());
		List<UserApp> lu = new ArrayList<>();
		lu.add(u);
		//vendor
		Vendor v2 = new Vendor();
		v2.setVendorid(1);
		v2.setCreditrating(1);
		v2.setPurchasingwebserviceurl("https");
		v2.setName("prueba");
		v2.setBusinessentityid(1);
		List<Vendor> lv = new ArrayList<>();
		lv.add(v2);
		//shipmethod
		Shipmethod s2 = new Shipmethod();
		s2.setShipmethodid(1);
		s2.setShipbase(new BigDecimal(1));
		s2.setShiprate(new BigDecimal(1));
		s2.setName("four");
		List<Shipmethod>ls = new ArrayList<Shipmethod>();
		ls.add(s2);
		//purchase order header
		LocalDate now = LocalDate.now();
		Purchaseorderheader poh2 = new Purchaseorderheader();
		poh2.setPurchaseorderid(1);
		poh2.setOrderdate(now);
		poh2.setSubtotal(new BigDecimal(1));
		poh2.setEmployeeid(1);
		poh2.setPersonid(1);
		poh2.setVendor(v2);
		poh2.setShipmethod(s2);
		List<Purchaseorderheader> lh = new ArrayList<Purchaseorderheader>();
		lh.add(poh2);
		//purchase order detail
		Purchaseorderdetail pod = new Purchaseorderdetail();
		pod.setOrderqty(1);
		pod.setUnitprice(new BigDecimal(1));
		pod.setProductid(1);
		pod.setPurchaseorderheader(poh2);
		Purchaseorderdetail pod2 = new Purchaseorderdetail();
		pod2.setOrderqty(1);
		pod2.setUnitprice(new BigDecimal(1));
		pod2.setProductid(1);
		pod2.setPurchaseorderheader(poh2);
		List<Purchaseorderdetail> ld = new ArrayList<Purchaseorderdetail>();
		ld.add(pod2);
		ld.add(pod);
		//mockito
		//user
		Mockito.when(delegate.userFindAll()).thenReturn(lu);
		Mockito.when(delegate.userFindById(1)).thenReturn(u);
		Mockito.doAnswer(invocation -> {
			UserApp arg0 = invocation.getArgument(0);
			u.setUsername(arg0.getUsername());
			return null;
		}).when(delegate).userUpdate(u);
		Mockito.doAnswer(invocation -> {
			Mockito.when(delegate.userFindById(1)).thenReturn(null);
			return null;
		}).when(delegate).userDelete(1);
		//vendor
		Mockito.when(delegate.vendorFindAll()).thenReturn(lv);
		Mockito.when(delegate.vendorFindById(1)).thenReturn(v2);
		Mockito.when(delegate.vendorFindByCreditRating()).thenReturn(lv);
		Mockito.doAnswer(invocation ->{
			Vendor v = invocation.getArgument(0);
			v2.setName(v.getName());
			return null;
		}).when(delegate).vendorUpdate(v2);
		Mockito.doAnswer(invocation ->{
			Mockito.when(delegate.vendorFindById(1)).thenReturn(null);
			return null;
		}).when(delegate).vendorDelete(1);
		//Ship method
		Mockito.when(delegate.shipmethodFindAll()).thenReturn(ls);
		Mockito.when(delegate.shipmethodFindById(1)).thenReturn(s2);
		Mockito.doAnswer(invocation->{
			Shipmethod s = invocation.getArgument(0);
			s2.setName(s.getName());
			return null;
		}).when(delegate).shipmethodUpdate(s2);
		Mockito.doAnswer(invocation->{
			Mockito.when(delegate.shipmethodFindById(1)).thenReturn(null);
			return null;
		}).when(delegate).shipmethodDelete(1);
		//purchase order details
		Mockito.when(delegate.purchaseorderdetailFindAll()).thenReturn(ld);
		Mockito.when(delegate.purchaseorderdetailFindByUnitprice(new BigDecimal(1))).thenReturn(ld);
		Mockito.when(delegate.purchaseorderdetailFindByProduct(1)).thenReturn(ld);
		Mockito.when(delegate.purchaseorderdetailFindById(1)).thenReturn(pod);
		Mockito.doAnswer(invocation->{
			Purchaseorderdetail p = invocation.getArgument(0);
			pod.setUnitprice(p.getUnitprice());
			return null;
		}).when(delegate).purchaseorderdetailUpdate(pod);
		Mockito.doAnswer(invocation->{
			Mockito.when(delegate.purchaseorderdetailFindById(1)).thenReturn(null);
			return null;
		}).when(delegate).purchaseorderdetailDelete(1);
		//purchase order header
		Mockito.when(delegate.purchaseorderheaderFindAll()).thenReturn(lh);
		Mockito.when(delegate.purchaseorderheaderFindById(1)).thenReturn(poh2);
		Mockito.when(delegate.purchaseorderheaderFindByVendor(v2)).thenReturn(lh);
		Mockito.when(delegate.purchaseorderheaderFindByShipmethod(s2)).thenReturn(lh);
		Mockito.when(delegate.purchaseorderheaderFindWithTwoplusPurchaseorderdetails()).thenReturn(lh);
		Mockito.when(delegate.purchaseorderheaderFindAllWithSumUnitprices(now.minusDays(1), now.plusDays(1))).thenReturn(lh);
		Mockito.doAnswer(invocation->{
			Purchaseorderheader p = invocation.getArgument(0);
			poh2.setPersonid(p.getPersonid());
			return null;
		}).when(delegate).purchaseorderheaderUpdate(poh2);
		Mockito.doAnswer(invocation->{
			Mockito.when(delegate.purchaseorderheaderFindById(1)).thenReturn(null);
			return null;
		}).when(delegate).purchaseorderheaderDelete(1);
	}

	@Test
	void userFindAllTest() {
		List<UserApp> l = new ArrayList<UserApp>();
		delegate.userFindAll().forEach(x -> l.add(x));
		assertEquals(1, l.size());
	}
	
	
	@Test
	void userSaveAndFindByIdTest() {
		UserApp u = new UserApp();
		u.setUsername("prueba");
		u.setPassword("prueba");
		u.setType(UserType.admin.toString());
		delegate.userSave(u);
		UserApp uf = delegate.userFindById(1);
		assertAll(()->{
			assertEquals(u.getUsername(), uf.getUsername());
			assertEquals(u.getPassword(), uf.getPassword());
			assertEquals(u.getType(), uf.getType());
		});
	}
	
	@Test
	void userUpdateTest() {
		UserApp u = delegate.userFindById(1);
		u.setUsername("update");
		delegate.userUpdate(u);
		assertEquals(u.getUsername(), delegate.userFindById(1).getUsername());
	}
	
	@Test
	void userDelete() {
		delegate.userDelete(1);
		assertNull(delegate.userFindById(1));
	}
	
	//vendor
	@Disabled
	@Test
	void vendorFindAll() {
		List<Vendor> l = new ArrayList<Vendor>();
		delegate.vendorFindAll().forEach(x->l.add(x));
		assertEquals(1, l.size());
	}
	
	@Test
	void vendorSaveAndFindById() {
		Vendor v2 = new Vendor();
		v2.setCreditrating(1);
		v2.setPurchasingwebserviceurl("https");
		v2.setName("prueba");
		v2.setBusinessentityid(1);
		delegate.vendorSave(v2);
		Vendor v = delegate.vendorFindById(1);
		assertAll("Case 1", ()-> {
			assertEquals(1, v.getCreditrating());
			assertEquals("https", v.getPurchasingwebserviceurl());
			assertEquals("prueba", v.getName());
			assertEquals(1, v.getBusinessentityid());
		});
	}
	
	@Test
	void vendorFindByCreditRating() {
		List<Vendor> l = new ArrayList<Vendor>();
		delegate.vendorFindByCreditRating().forEach(x->l.add(x));
		assertEquals(1,l.size());	
	}
	
	@Test
	void vendorUpdate() {
		Vendor v = delegate.vendorFindById(1);
		v.setName("update");
		delegate.vendorUpdate(v);
		Vendor v2 = delegate.vendorFindById(1);
		assertEquals(v.getName(), v2.getName());
	}
	
	@Test
	void vendorDelete() {
		delegate.vendorDelete(1);
		assertNull(delegate.vendorFindById(1));
	}
	
	//Shipmethod
	
	@Test
	void shipmethodFindAll() {
		List<Shipmethod> l = new ArrayList<Shipmethod>();
		delegate.shipmethodFindAll().forEach(x->l.add(x));
		assertEquals(1,l.size());
	}
	
	@Test
	void shipmethodSaveAndFindById() {
		Shipmethod s2 = new Shipmethod();
		s2.setShipbase(new BigDecimal(1));
		s2.setShiprate(new BigDecimal(1));
		s2.setName("four");
		delegate.shipmethodSave(s2);
		Shipmethod s = delegate.shipmethodFindById(1);
		assertAll("Shipmethod created",() -> {
			assertEquals(1, s.getShipbase().intValue());
			assertEquals(1, s.getShiprate().intValue());
			assertEquals("four",s.getName());
		});
	}
	
	@Test
	void shipmethodUpdate() {
		Shipmethod s = delegate.shipmethodFindById(1);
		s.setName("update");
		delegate.shipmethodSave(s);
		assertEquals(s.getName(), delegate.shipmethodFindById(1).getName());
	}
	
	@Test
	void shipmethodDelete() {
		delegate.shipmethodDelete(1);
		assertNull(delegate.shipmethodFindById(1));
	}
	
	//purchase order detail
	
	@Test
	void purchaseorderdetailFindAll() {
		List<Purchaseorderdetail> l = new ArrayList<Purchaseorderdetail>();
		delegate.purchaseorderdetailFindAll().forEach(x->l.add(x));
		assertEquals(2,l.size());
	}
	
	@Test
	void purchaseorderdetailFindByUnitprice() {
		List<Purchaseorderdetail> l = new ArrayList<Purchaseorderdetail>();
		delegate.purchaseorderdetailFindByUnitprice(new BigDecimal(1)).forEach(x->l.add(x));
		assertEquals(2, l.size());
	}
	
	@Test
	void purchaseorderdetailFindByProduct() {
		List<Purchaseorderdetail> l = new ArrayList<Purchaseorderdetail>();
		delegate.purchaseorderdetailFindByProduct(1).forEach(x-> l.add(x));
		assertEquals(2, l.size());
	}
	
	@Test
	void purchaseorderdetailSaveAndFindById() {
		Purchaseorderdetail pod = new Purchaseorderdetail();
		pod.setOrderqty(1);
		pod.setUnitprice(new BigDecimal(1));
		pod.setProductid(1);
		Purchaseorderheader poh = new Purchaseorderheader();
		poh.setPurchaseorderid(1);
		pod.setPurchaseorderheader(poh);
		delegate.purchaseorderdetailSave(pod);
		Purchaseorderdetail pod2 = delegate.purchaseorderdetailFindById(1);
		assertAll("succesfuly save", () -> {
			assertEquals(1,pod2.getOrderqty());
			assertEquals(1,pod2.getUnitprice().intValue());
			assertEquals(1, pod2.getPurchaseorderheader().getPurchaseorderid());
			assertEquals(1, pod2.getProductid());
		});
	}
	
	@Test
	void purchaseorderdetailUpdate() {
		Purchaseorderdetail p = delegate.purchaseorderdetailFindById(1);
		p.setUnitprice(new BigDecimal(99));
		delegate.purchaseorderdetailUpdate(p);
		assertEquals(p.getUnitprice(), delegate.purchaseorderdetailFindById(1).getUnitprice());
	}
	
	@Test
	void purchaseorderdetailDelete() {
		delegate.purchaseorderdetailDelete(1);
		assertNull(delegate.purchaseorderdetailFindById(1));
	}
	
	//purchase order header
	
	@Test
	void purchaseorderheaderFindAll() {
		List<Purchaseorderheader> l = new ArrayList<Purchaseorderheader>();
		delegate.purchaseorderheaderFindAll().forEach(x->l.add(x));
		assertEquals(1, l.size());
	}
	
	@Test
	void  purchaseorderheaderSaveAndFindById() {
		LocalDate now = LocalDate.now();
		Purchaseorderheader poh2 = new Purchaseorderheader();
		poh2.setOrderdate(now);
		poh2.setSubtotal(new BigDecimal(1));
		poh2.setEmployeeid(1);
		poh2.setPersonid(1);
		poh2.setVendor(delegate.vendorFindById(1));
		poh2.setShipmethod(delegate.shipmethodFindById(1));
		delegate.purchaseorderheaderSave(poh2);
		Purchaseorderheader poh = delegate.purchaseorderheaderFindById(1);
		assertAll("succesfully save", () -> {
			assertEquals(1, poh.getSubtotal().intValue());
			assertTrue(poh.getOrderdate().equals(now));
			assertEquals(1, poh.getEmployeeid());
			assertEquals(poh2.getVendor().getVendorid(), poh.getVendor().getVendorid());
			assertEquals(poh2.getShipmethod().getShipmethodid(), poh.getShipmethod().getShipmethodid());
		});
	}
	
	@Test
	void  purchaseorderheaderFindByVendor() {
		List<Purchaseorderheader> l = new ArrayList<Purchaseorderheader>();
		delegate.purchaseorderheaderFindByVendor(delegate.vendorFindById(1)).forEach(x->l.add(x));
		assertEquals(1, l.size());
	}
	
	@Test
	void  purchaseorderheaderFindByShipmethod() {
		List<Purchaseorderheader> l = new ArrayList<Purchaseorderheader>();
		delegate.purchaseorderheaderFindByShipmethod(delegate.shipmethodFindById(1)).forEach(x->l.add(x));
		assertEquals(1, l.size());
	}
	
	@Test
	void purchaseorderheaderFindWithTwoplusPurchaseorderdetails() {
		List<Purchaseorderheader> l = new ArrayList<Purchaseorderheader>();
		delegate.purchaseorderheaderFindWithTwoplusPurchaseorderdetails().forEach(x->l.add(x));
		assertEquals(1, l.size());
	}
	
	@Test
	void purchaseorderheaderFindAllWithSumUnitprices() {
		List<Purchaseorderheader> l = new ArrayList<Purchaseorderheader>();
		LocalDate date = LocalDate.now();
		delegate.purchaseorderheaderFindAllWithSumUnitprices(date.minusDays(1), date.plusDays(1)).forEach(x->l.add(x));
		assertEquals(1, l.size());
	}
	
	@Test
	void purchaseorderheaderUpdate() {
		Purchaseorderheader p = delegate.purchaseorderheaderFindById(1);
		p.setPersonid(99);
		delegate.purchaseorderheaderSave(p);
		assertEquals(p.getPersonid(), delegate.purchaseorderheaderFindById(1).getPersonid());
	}
	
	@Test
	void  purchaseorderheaderDelete() {
		delegate.purchaseorderheaderDelete(1);
		assertNull(delegate.purchaseorderheaderFindById(1));
	}
	
}
