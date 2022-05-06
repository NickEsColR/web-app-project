package co.edu.icesi.colmenares.service;

import java.util.Optional;

import co.edu.icesi.colmenares.model.person.Person;

public interface IPersonService {
	void save(Person person);
	Iterable<Person> findAll();
	Optional<Person> findById(int id);
}
