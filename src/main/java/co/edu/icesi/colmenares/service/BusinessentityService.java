package co.edu.icesi.colmenares.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.colmenares.model.person.Businessentity;
import co.edu.icesi.colmenares.repository.IBusinessentityRepository;

@Service
public class BusinessentityService implements IBusinessentityService {

	@Autowired
	private IBusinessentityRepository businessRepository;
	
	@Override
	public Iterable<Businessentity> findAll() {
		// TODO Auto-generated method stub
		return businessRepository.findAll();
	}

	@Override
	public void save(Businessentity businessentity) {
		// TODO Auto-generated method stub
		businessRepository.save(businessentity);
	}

	@Override
	public Optional<Businessentity> findById(int id) {
		// TODO Auto-generated method stub
		return businessRepository.findById(id);
	}

}
