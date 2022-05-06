package co.edu.icesi.colmenares.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.colmenares.model.person.Person;
import co.edu.icesi.colmenares.repository.IPersonRepository;

@Service
public class PersonService implements IPersonService {

	@Autowired
	private IPersonRepository personRepository;
	
	@Override
	public void save(Person person) {
		// TODO Auto-generated method stub
		personRepository.save(person);
	}

	@Override
	public Iterable<Person> findAll() {
		// TODO Auto-generated method stub
		return personRepository.findAll();
	}

	@Override
	public Optional<Person> findById(int id) {
		// TODO Auto-generated method stub
		return personRepository.findById(id);
	}

	
}
