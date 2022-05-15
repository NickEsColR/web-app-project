package co.edu.icesi.colmenares.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import co.edu.icesi.colmenares.Taller2.Taller2Application;
import co.edu.icesi.colmenares.dao.IPurchaseorderheaderDao;
import co.edu.icesi.colmenares.model.hr.Employee;
import co.edu.icesi.colmenares.model.person.Person;
import co.edu.icesi.colmenares.model.prchasing.Purchaseorderheader;
import co.edu.icesi.colmenares.repository.IEmployeeRepository;
import co.edu.icesi.colmenares.repository.IPersonRepository;
import co.edu.icesi.colmenares.repository.IPurchaseorderheaderRepository;

@SpringBootTest(classes = Taller2Application.class)
class PurchaseorderheaderServiceTest {

	
	static IPurchaseorderheaderDao pohRepositoryMock = Mockito.mock(IPurchaseorderheaderDao.class);
	static IEmployeeRepository employeeRepositoryMock = Mockito.mock(IEmployeeRepository.class);
	static IPersonRepository personRepositoryMock = Mockito.mock(IPersonRepository.class);
	PurchaseorderheaderService pohService = new PurchaseorderheaderService(pohRepositoryMock, 
			employeeRepositoryMock, personRepositoryMock);
	 
	@BeforeEach
	void setUp(){
		LocalDate now = LocalDate.now();
		Purchaseorderheader poh2 = new Purchaseorderheader();
		poh2.setPurchaseorderid(2);
		poh2.setOrderdate(now);
		poh2.setSubtotal(new BigDecimal(1));
		poh2.setEmployeeid(1);
		Optional<Employee> e = Optional.of(new Employee());
		e.get().setBusinessentityid(1);
		Optional<Purchaseorderheader> poh = Optional.of(new Purchaseorderheader());
		poh.get().setPurchaseorderid(Integer.valueOf(1));
		Optional<Person> p = Optional.of(new Person());
		p.get().setBusinessentityid(1);
		Mockito.when(personRepositoryMock.findById(1)).thenReturn(p);
		Mockito.when(employeeRepositoryMock.findById(1)).thenReturn(e);
		Mockito.when(pohRepositoryMock.findById(1)).thenReturn(poh.get());
		Mockito.when(pohRepositoryMock.findById(1)).thenReturn(poh2);
		Mockito.when(employeeRepositoryMock.findById(2)).thenReturn(Optional.ofNullable(null));
		Mockito.when(personRepositoryMock.findById(2)).thenReturn(Optional.ofNullable(null));
	}
	
	@Test
	void testValidcase1() {
		LocalDate now = LocalDate.now();
		Purchaseorderheader poh2 = new Purchaseorderheader();
		poh2.setPurchaseorderid(2);
		poh2.setOrderdate(now);
		poh2.setSubtotal(new BigDecimal(1));
		poh2.setEmployeeid(1);
		poh2.setPersonid(1);
		pohService.savePurchaseorderheader(poh2);
		assertAll("succesfully save", () -> {
			assertEquals(1, pohService.getSubtotal(1).intValue());
			assertTrue(pohService.getOrderdate(1).equals(now));
			assertEquals(1, pohService.getEmployeeid(1));
		});
	}
	
	@Test
	void testInvalidcase1() {
		LocalDate now = LocalDate.now();	
		Purchaseorderheader poh2 = new Purchaseorderheader();
		poh2.setPurchaseorderid(2);
		poh2.setOrderdate(now.plusDays(1));
		poh2.setSubtotal(new BigDecimal(1));
		poh2.setEmployeeid(1);
		poh2.setPersonid(1);
		assertThrows(IllegalArgumentException.class, () ->		pohService.savePurchaseorderheader(poh2));
	}
	
	@Test
	void testInvalidcase2() {
		LocalDate now = LocalDate.now();
		Purchaseorderheader poh2 = new Purchaseorderheader();
		poh2.setPurchaseorderid(2);
		poh2.setOrderdate(now.minusDays(1));
		poh2.setSubtotal(new BigDecimal(1));
		poh2.setEmployeeid(1);
		poh2.setPersonid(1);
		assertThrows(IllegalArgumentException.class, () ->		pohService.savePurchaseorderheader(poh2));
	}
	
	@Test
	void testInvalidcase3() {
		LocalDate now = LocalDate.now();
		Purchaseorderheader poh2 = new Purchaseorderheader();
		poh2.setPurchaseorderid(2);
		poh2.setOrderdate(now);
		poh2.setSubtotal(new BigDecimal(0));
		poh2.setEmployeeid(1);
		poh2.setPersonid(1);
		assertThrows(IllegalArgumentException.class, () ->		pohService.savePurchaseorderheader(poh2));
	}
	
	@Test
	void testInvalidcase4() {
		LocalDate now = LocalDate.now();
		now.minusDays(1);
		Purchaseorderheader poh2 = new Purchaseorderheader();
		poh2.setPurchaseorderid(2);
		poh2.setOrderdate(now);
		poh2.setSubtotal(new BigDecimal(-1));
		poh2.setEmployeeid(1);
		poh2.setPersonid(1);
		assertThrows(IllegalArgumentException.class, () ->		pohService.savePurchaseorderheader(poh2));
	}
	
	@Test
	void testInvalidcase5() {
		LocalDate now = LocalDate.now();
		now.minusDays(1);
		Purchaseorderheader poh2 = new Purchaseorderheader();
		poh2.setPurchaseorderid(2);
		poh2.setOrderdate(now);
		poh2.setSubtotal(new BigDecimal(1));
		poh2.setEmployeeid(2);
		poh2.setPersonid(1);
		assertThrows(IllegalArgumentException.class, () ->		pohService.savePurchaseorderheader(poh2));
	}
	
	@Test
	void testInvalidcase6() {
		LocalDate now = LocalDate.now();
		now.minusDays(1);
		Purchaseorderheader poh2 = new Purchaseorderheader();
		poh2.setPurchaseorderid(2);
		poh2.setOrderdate(now);
		poh2.setSubtotal(new BigDecimal(1));
		poh2.setEmployeeid(1);
		poh2.setPersonid(2);
		assertThrows(IllegalArgumentException.class, () ->		pohService.savePurchaseorderheader(poh2));
	}
	
	@Test
	void testValidEmployee() {
		pohService.setEmployeeid(1, 1);
		assertEquals(1, pohService.getEmployeeid(1));
	}
	
	@Test
	void testInvalidEmployee() {
		assertThrows(IllegalArgumentException.class,() -> pohService.setEmployeeid(2,1), "business entity must exist");
	}
	
	@Test
	void testValidPerson() {
		pohService.setPersonid(1, 1);
		assertEquals(1, pohService.getEmployeeid(1));
	}
	
	@Test
	void testInvalidPerson() {
		assertThrows(IllegalArgumentException.class,() -> pohService.setPersonid(2,1), "business entity must exist");
	}
	
	@Test
	void testPurchaseorderheader() {
		pohService.setSubtotal(new BigDecimal(1),1);
		//LocalDateTime now = LocalDateTime.parse(LocalDate.now().toString(),dtf);
		//long now = System.currentTimeMillis();
		//h.setOrderdate(new Timestamp(now.getNano()/1000000));
		LocalDate now = LocalDateTime.now().toLocalDate();
		pohService.setOrderdate(now,1);
		assertAll("Purchaseorderheader created",() -> {
			assertEquals(1, pohService.getSubtotal(1).intValue());
			assertTrue(pohService.getOrderdate(1).equals(now));
		});
	}
	
	@Test
	void testPurchaseorderheaderSubtotal() {
		assertAll("validate unit price",() -> {
			assertThrows(IllegalArgumentException.class, () -> pohService.setSubtotal(new BigDecimal(0),1));
			assertThrows(IllegalArgumentException.class, () -> pohService.setSubtotal(new BigDecimal(-1),1));
		});
	}
	
	@Test
	void testPurchaseorderheaderOrderdate() {
		//long now = System.currentTimeMillis();
		//LocalDateTime now = LocalDateTime.parse(LocalDate.now().toString(),dtf);
		LocalDate now = LocalDateTime.now().toLocalDate();
		LocalDate dateMayor = now.plusDays(1);
		LocalDate dateMinor = now.minusDays(1);		
		assertAll("validate order date",() -> {
			assertThrows(IllegalArgumentException.class, () ->		pohService.setOrderdate(dateMayor,1));
			assertThrows(IllegalArgumentException.class, () -> 		pohService.setOrderdate(dateMinor,1));
		});
	}
}
