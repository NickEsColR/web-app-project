package co.edu.icesi.colmenares.service;

import java.util.Optional;

import co.edu.icesi.colmenares.model.person.Businessentity;

public interface IBusinessentityService {

	Iterable<Businessentity> findAll();
	void save(Businessentity businessentity);
	Optional<Businessentity> findById(int id);
}
