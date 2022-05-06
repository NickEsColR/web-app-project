package co.edu.icesi.colmenares.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.colmenares.model.hr.Employee;

@Repository
public interface IEmployeeRepository extends CrudRepository<Employee, Integer>{

}
