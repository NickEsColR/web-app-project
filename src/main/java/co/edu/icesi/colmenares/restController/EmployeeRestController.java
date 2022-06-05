package co.edu.icesi.colmenares.restController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.colmenares.model.hr.Employee;
import co.edu.icesi.colmenares.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeRestController {
	
	private EmployeeService eService;
	
	public EmployeeRestController(EmployeeService eService) {
		this.eService = eService;
	}
	
	
	@GetMapping
	public Iterable<Employee> getAll() {
		return eService.findAll();
	}

}
