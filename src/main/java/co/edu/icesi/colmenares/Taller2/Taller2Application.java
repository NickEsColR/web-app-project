package co.edu.icesi.colmenares.Taller2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import co.edu.icesi.colmenares.model.hr.Employee;
import co.edu.icesi.colmenares.model.person.Businessentity;
import co.edu.icesi.colmenares.model.person.Person;
import co.edu.icesi.colmenares.model.security.UserApp;
import co.edu.icesi.colmenares.model.security.UserType;
import co.edu.icesi.colmenares.repository.IBusinessentityRepository;
import co.edu.icesi.colmenares.repository.IEmployeeRepository;
import co.edu.icesi.colmenares.repository.IPersonRepository;
import co.edu.icesi.colmenares.repository.IUserAppRepository;



@SpringBootApplication
@EnableJpaRepositories(basePackages="co.edu.icesi.colmenares")
@EntityScan(basePackages = "co.edu.icesi.colmenares")
@ComponentScan(basePackages = {"co.edu.icesi.colmenares"})
public class Taller2Application {

	public static void main(String[] args) {

		SpringApplication.run(Taller2Application.class, args);
	}
	
	@Bean
	public CommandLineRunner clr(IEmployeeRepository employeeRepository,
			IPersonRepository personRepository, 
			IBusinessentityRepository businessentityRepository, IUserAppRepository userAppRepository) {
		return (args)->{
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
		};
	}

}
