package co.edu.icesi.colmenares.restController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.colmenares.model.person.Businessentity;
import co.edu.icesi.colmenares.service.BusinessentityService;

@RestController
@RequestMapping("/api/businessentities")
public class BusinessEntityRestController {

	private BusinessentityService bService;
	
	public BusinessEntityRestController(BusinessentityService bService) {
		this.bService = bService;
	}
	
	@PostMapping
	public void add(@RequestBody Businessentity be) {
		bService.save(be);
	}
	
	@GetMapping
	public Iterable<Businessentity> getAll() {
		return bService.findAll();
	}
	
}
