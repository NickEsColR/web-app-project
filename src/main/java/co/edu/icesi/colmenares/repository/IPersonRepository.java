package co.edu.icesi.colmenares.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.colmenares.model.person.Person;

@Repository
public interface IPersonRepository extends CrudRepository<Person, Integer>{

}
