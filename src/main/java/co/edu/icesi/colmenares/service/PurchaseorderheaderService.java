package co.edu.icesi.colmenares.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.colmenares.dao.IPurchaseorderheaderDao;
import co.edu.icesi.colmenares.model.hr.Employee;
import co.edu.icesi.colmenares.model.person.Person;
import co.edu.icesi.colmenares.model.prchasing.Purchaseorderheader;
import co.edu.icesi.colmenares.repository.IEmployeeRepository;
import co.edu.icesi.colmenares.repository.IPersonRepository;
import co.edu.icesi.colmenares.repository.IPurchaseorderheaderRepository;

@Service
public class PurchaseorderheaderService implements IPurchaseorderheaderService {
	
	private IPurchaseorderheaderDao purchaseorderheaderRepository;
	private IEmployeeRepository employeeRepository;
	private IPersonRepository personRepository;
	
	

	public PurchaseorderheaderService(IPurchaseorderheaderDao purchaseorderheaderRepository,
			IEmployeeRepository employeeRepository, IPersonRepository personRepository) {
		this.purchaseorderheaderRepository = purchaseorderheaderRepository;
		this.employeeRepository = employeeRepository;
		this.personRepository = personRepository;
	}

	@Override
	@Transactional
	public void savePurchaseorderheader(Purchaseorderheader value) {
		// TODO Auto-generated method stub
		LocalDate now = LocalDateTime.now().toLocalDate();
		Optional<Employee> e = employeeRepository.findById(value.getEmployeeid());
		Optional<Person> p = personRepository.findById(value.getPersonid());
		if(value.getSubtotal().intValue()<=0 || !now.equals(value.getOrderdate()) || e.isEmpty() || p.isEmpty()) {
			throw new IllegalArgumentException();
		}
		purchaseorderheaderRepository.save(value);
	}

	@Override
	@Transactional
	public void setSubtotal(BigDecimal value, int id) {
		// TODO Auto-generated method stub
		if(value.intValue()<=0) {
			throw new IllegalArgumentException();
		}
		Optional<Purchaseorderheader> poh = purchaseorderheaderRepository.findById(id);
		if(poh.isPresent()) {
			poh.get().setSubtotal(value);
		}
	}

	@Override
	@Transactional
	public void setOrderdate(LocalDate value, int id) {
		// TODO Auto-generated method stub
		LocalDate now = LocalDateTime.now().toLocalDate();
		if(now.equals(value)) {
			Optional<Purchaseorderheader> poh = purchaseorderheaderRepository.findById(id);
			if(poh.isPresent()) {
				poh.get().setOrderdate(value);
			}
		}else {			
			throw new IllegalArgumentException();
		}
		
	}

	@Override
	@Transactional
	public void setEmployeeid(int employeeid, int id) {
		// TODO Auto-generated method stub
		Optional<Employee> e = employeeRepository.findById(employeeid);
		if(e.isEmpty()) {
			throw new IllegalArgumentException();
		}
		Optional<Purchaseorderheader> poh = purchaseorderheaderRepository.findById(id);
		if(poh.isPresent()) {
			poh.get().setEmployeeid(employeeid);
		}
	}

	@Override
	public BigDecimal getSubtotal(int id) {
		// TODO Auto-generated method stub
		Optional<Purchaseorderheader> poh = purchaseorderheaderRepository.findById(id);
		if(poh.isPresent()) {
			return poh.get().getSubtotal();
		}
		return null;
	}

	@Override
	public LocalDate getOrderdate(int id) {
		// TODO Auto-generated method stub
		Optional<Purchaseorderheader> poh = purchaseorderheaderRepository.findById(id);
		if(poh.isPresent()) {
			return poh.get().getOrderdate();
		}
		return null;
	}

	@Override
	public int getEmployeeid(int id) {
		// TODO Auto-generated method stub
		Optional<Purchaseorderheader> poh = purchaseorderheaderRepository.findById(id);
		if(poh.isPresent()) {
			return poh.get().getEmployeeid();
		}
		return 0;
	}

	@Override
	public void setPersonid(int personid, int id) {
		// TODO Auto-generated method stub
		Optional<Person> p = personRepository.findById(personid);
		if(p.isEmpty()) {
			throw new IllegalArgumentException();
		}
		Optional<Purchaseorderheader> poh = purchaseorderheaderRepository.findById(id);
		if(poh.isPresent()) {
			poh.get().setPersonid(personid);
		}
	}

	@Override
	public int getPersonid(int id) {
		// TODO Auto-generated method stub
		Optional<Purchaseorderheader> poh = purchaseorderheaderRepository.findById(id);
		if(poh.isPresent()) {
			return poh.get().getPersonid();
		}
		return 0;
	}

	@Override
	public Iterable<Purchaseorderheader> findAll() {
		// TODO Auto-generated method stub
		return purchaseorderheaderRepository.findAll();
	}

	@Override
	public Optional<Purchaseorderheader> findById(int id) {
		// TODO Auto-generated method stub
		return purchaseorderheaderRepository.findById(id);
	}


}
