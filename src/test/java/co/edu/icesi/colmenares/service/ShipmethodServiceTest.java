package co.edu.icesi.colmenares.service;

import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import co.edu.icesi.colmenares.Taller2.Taller2Application;
import co.edu.icesi.colmenares.dao.IShipmethodDao;
import co.edu.icesi.colmenares.model.prchasing.Shipmethod;
import co.edu.icesi.colmenares.repository.IShipmethodRepository;

@SpringBootTest(classes = Taller2Application.class)
class ShipmethodServiceTest {
	
	static IShipmethodDao shipmethodRepositoryMock = Mockito.mock(IShipmethodDao.class);
	IShipmethodService shipmethodService = new ShipmethodService(shipmethodRepositoryMock);
	
	@BeforeEach
	void setUp(){
		Shipmethod s2 = new Shipmethod();
		s2.setShipmethodid(Integer.valueOf(2));
		s2.setShipbase(new BigDecimal(1));
		s2.setShiprate(new BigDecimal(1));
		s2.setName("four");
		Optional<Shipmethod> s = Optional.of(new Shipmethod());
		s.get().setShipmethodid(Integer.valueOf(1));
		Mockito.when(shipmethodRepositoryMock.findById(1)).thenReturn(s.get());
		Mockito.when(shipmethodRepositoryMock.findById(2)).thenReturn(s2);
	}

	@Test
	void testValidCase1() {
		Shipmethod s2 = new Shipmethod();
		s2.setShipmethodid(Integer.valueOf(2));
		s2.setShipbase(new BigDecimal(1));
		s2.setShiprate(new BigDecimal(1));
		s2.setName("four");
		shipmethodService.saveShipmethod(s2);
		assertAll("Shipmethod created",() -> {
			assertEquals(1, shipmethodService.getShipbase(2).intValue());
			assertEquals(1, shipmethodService.getShiprate(2).intValue());
			assertEquals("four",shipmethodService.getName(2));
		});
	}
	
	@Test
	void testInvalidCase1() {
		Shipmethod s2 = new Shipmethod();
		s2.setShipmethodid(Integer.valueOf(2));
		s2.setShipbase(new BigDecimal(0));
		s2.setShiprate(new BigDecimal(1));
		s2.setName("four");
		assertThrows(IllegalArgumentException.class, () -> shipmethodService.saveShipmethod(s2));
	}
	
	@Test
	void testInvalidCase2() {
		Shipmethod s2 = new Shipmethod();
		s2.setShipmethodid(Integer.valueOf(2));
		s2.setShipbase(new BigDecimal(-1));
		s2.setShiprate(new BigDecimal(1));
		s2.setName("four");
		assertThrows(IllegalArgumentException.class, () -> shipmethodService.saveShipmethod(s2));
	}
	
	@Test
	void testInvalidCase3() {
		Shipmethod s2 = new Shipmethod();
		s2.setShipmethodid(Integer.valueOf(2));
		s2.setShipbase(new BigDecimal(1));
		s2.setShiprate(new BigDecimal(0));
		s2.setName("four");
		assertThrows(IllegalArgumentException.class, () -> shipmethodService.saveShipmethod(s2));
	}
	
	@Test
	void testInvalidCase4() {
		Shipmethod s2 = new Shipmethod();
		s2.setShipmethodid(Integer.valueOf(2));
		s2.setShipbase(new BigDecimal(1));
		s2.setShiprate(new BigDecimal(-1));
		s2.setName("four");
		assertThrows(IllegalArgumentException.class, () -> shipmethodService.saveShipmethod(s2));
	}
	
	@Test
	void testInvalidCase5() {
		Shipmethod s2 = new Shipmethod();
		s2.setShipmethodid(Integer.valueOf(2));
		s2.setShipbase(new BigDecimal(1));
		s2.setShiprate(new BigDecimal(1));
		s2.setName("Ana");
		assertThrows(IllegalArgumentException.class, () -> shipmethodService.saveShipmethod(s2));
	}
	
	@Test
	void testCreateShipmethod() {
		shipmethodService.setShipbase(new BigDecimal(1),1);
		shipmethodService.setShiprate(new BigDecimal(1),1);
		shipmethodService.setName("four",1);
		assertAll("Shipmethod created",() -> {
			assertEquals(1, shipmethodService.getShipbase(1).intValue());
			assertEquals(1, shipmethodService.getShiprate(1).intValue());
			assertEquals("four",shipmethodService.getName(1));
		});
	}
	
	@Test
	void testShipmethodBase() {
		assertAll("Validate Shipmethod base",() -> {
			assertThrows(IllegalArgumentException.class, () -> shipmethodService.setShipbase(new BigDecimal(0),1));
			assertThrows(IllegalArgumentException.class, () -> shipmethodService.setShipbase(new BigDecimal(-1),1));
		});
	}
	
	@Test
	void testShipmethodRate() {
		assertAll("validate Shipmethod rate",() -> {
			assertThrows(IllegalArgumentException.class, () -> shipmethodService.setShiprate(new BigDecimal(0),1));
			assertThrows(IllegalArgumentException.class, () -> shipmethodService.setShiprate(new BigDecimal(-1),1));
		});
	}
	
	@Test
	void testShipmethodName() {
		assertThrows(IllegalArgumentException.class, () -> shipmethodService.setName("Ana",1),"name cant be less than 4 characters long");
	}

}
