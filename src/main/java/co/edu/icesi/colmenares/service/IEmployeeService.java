package co.edu.icesi.colmenares.service;

import java.util.Optional;

import co.edu.icesi.colmenares.model.hr.Employee;

public interface IEmployeeService {
	void save(Employee employee);
	Iterable<Employee> findAll();
	Optional<Employee> findById(int id);
}
