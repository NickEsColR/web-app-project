package co.edu.icesi.colmenares.Taller2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import co.edu.icesi.colmenares.dao.IPurchaseorderdetailDao;
import co.edu.icesi.colmenares.dao.IPurchaseorderheaderDao;
import co.edu.icesi.colmenares.model.hr.Employee;
import co.edu.icesi.colmenares.model.person.Businessentity;
import co.edu.icesi.colmenares.model.person.Person;
import co.edu.icesi.colmenares.model.prchasing.Purchaseorderdetail;
import co.edu.icesi.colmenares.model.prchasing.Purchaseorderheader;
import co.edu.icesi.colmenares.model.security.UserApp;
import co.edu.icesi.colmenares.model.security.UserType;
import co.edu.icesi.colmenares.repository.IBusinessentityRepository;
import co.edu.icesi.colmenares.repository.IEmployeeRepository;
import co.edu.icesi.colmenares.repository.IPersonRepository;
import co.edu.icesi.colmenares.repository.IUserAppRepository;
import co.edu.icesi.colmenares.service.IPurchaseorderdetailService;
import co.edu.icesi.colmenares.service.IPurchaseorderheaderService;



@SpringBootApplication
@EnableJpaRepositories(basePackages="co.edu.icesi.colmenares")
@EntityScan(basePackages = "co.edu.icesi.colmenares")
@ComponentScan(basePackages = {"co.edu.icesi.colmenares"})
public class Taller2Application {

	public static void main(String[] args) {

	 	ConfigurableApplicationContext config = SpringApplication.run(Taller2Application.class, args);
		IEmployeeRepository employeeRepository = config.getBean(IEmployeeRepository.class);
		IPersonRepository personRepository = config.getBean(IPersonRepository.class);
		IBusinessentityRepository businessentityRepository = config.getBean(IBusinessentityRepository.class);
		IUserAppRepository userAppRepository = config.getBean(IUserAppRepository.class);
		IPurchaseorderdetailService podDao = config.getBean(IPurchaseorderdetailService.class);
		IPurchaseorderheaderService pohDao = config.getBean(IPurchaseorderheaderService.class);
		//businessentities
		Businessentity be = new Businessentity();
		businessentityRepository.save(be);
		be = new Businessentity();
		businessentityRepository.save(be);
		be = new Businessentity();
		businessentityRepository.save(be);
		//Employees
		Employee e = new Employee();
		employeeRepository.save(e);
		e = new Employee();
		employeeRepository.save(e);
		e = new Employee();
		employeeRepository.save(e);
		//Persons
		Person p = new Person();
		personRepository.save(p);
		p = new Person();
		personRepository.save(p);
		p = new Person();
		personRepository.save(p);
		//Users
		UserApp u = new UserApp();
		u.setType(UserType.admin.toString());
		u.setUsername("admin");
		u.setPassword("{noop}admin");
		userAppRepository.save(u);
		u = new UserApp();
		u.setType(UserType.operator.toString());
		u.setUsername("operator");
		u.setPassword("{noop}operator");
		userAppRepository.save(u);
		//advance queries;
		Purchaseorderheader poh = new Purchaseorderheader();
		poh.setPurchaseorderid(1);
		poh.setSubtotal(new BigDecimal(1));
		pohDao.savePurchaseorderheader(poh);
		poh = pohDao.findById(1).get();
		Purchaseorderdetail pod = new Purchaseorderdetail();
		pod.setId(1);
		pod.setOrderqty(1);
		pod.setUnitprice(new BigDecimal(1));
		pod.setPurchaseorderheader(poh);
		podDao.savePurchaseorderdetail(pod);
		pod = new Purchaseorderdetail();
		pod.setId(1);
		pod.setOrderqty(1);
		pod.setUnitprice(new BigDecimal(1));
		pod.setPurchaseorderheader(poh);
		podDao.savePurchaseorderdetail(pod);
		List<Purchaseorderdetail> pl = new ArrayList<Purchaseorderdetail>();
		pl.add(podDao.findById(1).get());
		pl.add(podDao.findById(2).get());
		poh.setPurchaseorderdetails(pl);
		pohDao.savePurchaseorderheader(poh);
	}
	
//	
//	@Bean
//	public CommandLineRunner clr(IEmployeeRepository employeeRepository,
//			IPersonRepository personRepository, 
//			IBusinessentityRepository businessentityRepository, IUserAppRepository userAppRepository,IPurchaseorderdetailService podDao,
//			IPurchaseorderheaderService pohDao) {
//		return (args)->{
//			//businessentities
//			Businessentity be = new Businessentity();
//			businessentityRepository.save(be);
//			be = new Businessentity();
//			businessentityRepository.save(be);
//			be = new Businessentity();
//			businessentityRepository.save(be);
//			//Employees
//			Employee e = new Employee();
//			employeeRepository.save(e);
//			e = new Employee();
//			employeeRepository.save(e);
//			e = new Employee();
//			employeeRepository.save(e);
//			//Persons
//			Person p = new Person();
//			personRepository.save(p);
//			p = new Person();
//			personRepository.save(p);
//			p = new Person();
//			personRepository.save(p);
//			//Users
//			UserApp u = new UserApp();
//			u.setType(UserType.admin.toString());
//			u.setUsername("admin");
//			u.setPassword("{noop}admin");
//			userAppRepository.save(u);
//			u = new UserApp();
//			u.setType(UserType.operator.toString());
//			u.setUsername("operator");
//			u.setPassword("{noop}operator");
//			userAppRepository.save(u);
//			//advance queries;
//			Purchaseorderheader poh = new Purchaseorderheader();
//			poh.setPurchaseorderid(1);
//			poh.setSubtotal(new BigDecimal(1));
//			pohDao.savePurchaseorderheader(poh);
//			poh = pohDao.findById(1).get();
//			Purchaseorderdetail pod = new Purchaseorderdetail();
//			pod.setId(1);
//			pod.setOrderqty(1);
//			pod.setUnitprice(new BigDecimal(1));
//			pod.setPurchaseorderheader(poh);
//			podDao.savePurchaseorderdetail(pod);
//			pod = new Purchaseorderdetail();
//			pod.setId(1);
//			pod.setOrderqty(1);
//			pod.setUnitprice(new BigDecimal(1));
//			pod.setPurchaseorderheader(poh);
//			podDao.savePurchaseorderdetail(pod);
//			List<Purchaseorderdetail> pl = new ArrayList<Purchaseorderdetail>();
//			pl.add(podDao.findById(1).get());
//			pl.add(podDao.findById(2).get());
//			poh.setPurchaseorderdetails(pl);
//			pohDao.savePurchaseorderheader(poh);
//			
//		};
//	}

}
