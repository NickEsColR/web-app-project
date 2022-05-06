package co.edu.icesi.colmenares.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.icesi.colmenares.Taller2.Taller2Application;
import co.edu.icesi.colmenares.model.prchasing.Shipmethod;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Taller2Application.class)
class ShipmethodServiceTest2 {

	IShipmethodService shipmethodService;
	
	@Autowired
	public ShipmethodServiceTest2(IShipmethodService shipmethodService) {
		super();
		this.shipmethodService = shipmethodService;
	}

	@BeforeEach
	void setUp() {
		Shipmethod s2 = new Shipmethod();
		s2.setShipmethodid(Integer.valueOf(2));
		s2.setShipbase(new BigDecimal(1));
		s2.setShiprate(new BigDecimal(1));
		s2.setName("four");
		shipmethodService.saveShipmethod(s2);
	}

	@Disabled
	@Test
	void testValidCase1() {
		assertAll("Shipmethod created",() -> {
			assertEquals(1, shipmethodService.getShipbase(2).intValue());
			assertEquals(1, shipmethodService.getShiprate(2).intValue());
			assertEquals("four",shipmethodService.getName(2));
		});
	}

	@Disabled
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
}
