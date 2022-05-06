package co.edu.icesi.colmenares.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.icesi.colmenares.Taller2.Taller2Application;
import co.edu.icesi.colmenares.model.hr.Employee;
import co.edu.icesi.colmenares.model.person.Person;
import co.edu.icesi.colmenares.model.prchasing.Purchaseorderheader;
import co.edu.icesi.colmenares.repository.IEmployeeRepository;
import co.edu.icesi.colmenares.repository.IPersonRepository;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Taller2Application.class)
class PurchaseorderheaderServiceTest2 {
	
	IPersonRepository personRepositor;
	IEmployeeRepository employeeRepository;
	PurchaseorderheaderService pohService;
	private LocalDate now;
	
	@Autowired
	public PurchaseorderheaderServiceTest2(IPersonRepository personRepositor, IEmployeeRepository employeeRepository,
			PurchaseorderheaderService pohService) {
		this.personRepositor = personRepositor;
		this.employeeRepository = employeeRepository;
		this.pohService = pohService;
	}

	@BeforeEach
	void setUp(){
		now = LocalDate.now();
		Purchaseorderheader poh2 = new Purchaseorderheader();
		poh2.setPurchaseorderid(2);
		poh2.setOrderdate(now);
		poh2.setSubtotal(new BigDecimal(1));
		poh2.setEmployeeid(1);
		poh2.setPersonid(1);
		Employee e = new Employee();
		e.setBusinessentityid(1);
		employeeRepository.save(e);
		e = new Employee();
		e.setBusinessentityid(2);
		employeeRepository.save(e);
		Person p = new Person();
		p.setBusinessentityid(1);
		personRepositor.save(p);
		p = new Person();
		p.setBusinessentityid(2);
		personRepositor.save(p);
		pohService.savePurchaseorderheader(poh2);
	}

	@Disabled
	@Test
	void testValidcase1() {

		assertAll("succesfully save", () -> {
			assertEquals(1, pohService.getSubtotal(1).intValue());
			assertTrue(pohService.getOrderdate(1).equals(now));
			assertEquals(1, pohService.getEmployeeid(1));
		});
	}

	@Disabled
	@Test
	void testValidEmployee() {
		pohService.setEmployeeid(2, 1);
		assertEquals(2, pohService.getEmployeeid(1));
	}
	
	@Disabled
	@Test
	void testValidPerson() {
		pohService.setPersonid(2, 1);
		assertEquals(2, pohService.getEmployeeid(1));
	}
	
	@Disabled
	@Test
	void testPurchaseorderheader() {
		pohService.setSubtotal(new BigDecimal(1),1);
		LocalDate now = LocalDateTime.now().toLocalDate();
		pohService.setOrderdate(now,1);
		assertAll("Purchaseorderheader created",() -> {
			assertEquals(1, pohService.getSubtotal(1).intValue());
			assertTrue(pohService.getOrderdate(1).equals(now));
		});
	}
}
