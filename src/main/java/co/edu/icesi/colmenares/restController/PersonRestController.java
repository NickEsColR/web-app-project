package co.edu.icesi.colmenares.restController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.colmenares.model.person.Person;
import co.edu.icesi.colmenares.service.PersonService;

@RestController
@RequestMapping("/api/people")
public class PersonRestController {

private PersonService pService;
	
	public PersonRestController(PersonService pService) {
		this.pService = pService;
	}
	
	
	@GetMapping
	public Iterable<Person> getAll() {
		return pService.findAll();
	}
}
