package co.edu.icesi.colmenares.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.colmenares.dao.IShipmethodDao;
import co.edu.icesi.colmenares.model.prchasing.Shipmethod;
import co.edu.icesi.colmenares.repository.IShipmethodRepository;

@Service
public class ShipmethodService implements IShipmethodService {
	
	private IShipmethodDao shipmethodRepository;
	
	
	public ShipmethodService(IShipmethodDao shipmethodRepository) {
		this.shipmethodRepository = shipmethodRepository;
	}

	@Override
	@Transactional
	public void saveShipmethod(Shipmethod value) {
		// TODO Auto-generated method stub
		if(value.getShipbase().intValue() <= 0 || value.getShiprate().intValue() <= 0 || value.getName().length()<4) {
			throw new IllegalArgumentException();
		}
		shipmethodRepository.save(value);
	}

	@Override
	@Transactional
	public void setShipbase(BigDecimal value, int id) {
		// TODO Auto-generated method stub
		if(value.intValue() <= 0) {
			throw new IllegalArgumentException();
		}
		Optional<Shipmethod> s = shipmethodRepository.findById(id);
		if(s.isPresent()) {
			s.get().setShipbase(value);
		}
	}

	@Override
	@Transactional
	public void setShiprate(BigDecimal value, int id) {
		// TODO Auto-generated method stub
		if(value.intValue() <= 0) {
			throw new IllegalArgumentException();
		}
		Optional<Shipmethod> s = shipmethodRepository.findById(id);
		if(s.isPresent()) {
			s.get().setShiprate(value);
		}
	}

	@Override
	@Transactional
	public void setName(String value, int id) {
		// TODO Auto-generated method stub
		if(value.length()<4) {
			throw new IllegalArgumentException();
		}
		Optional<Shipmethod> s = shipmethodRepository.findById(id);
		if(s.isPresent()) {
			s.get().setName(value);
		}
	}

	@Override
	public BigDecimal getShipbase(int id) {
		// TODO Auto-generated method stub
		Optional<Shipmethod> s = shipmethodRepository.findById(id);
		if(s.isPresent()) {
			return s.get().getShipbase();
		}
		return null;
	}

	@Override
	public BigDecimal getShiprate(int id) {
		// TODO Auto-generated method stub
		Optional<Shipmethod> s = shipmethodRepository.findById(id);
		if(s.isPresent()) {
			return s.get().getShiprate();
		}
		return null;
	}

	@Override
	public String getName(int id) {
		// TODO Auto-generated method stub
		Optional<Shipmethod> s = shipmethodRepository.findById(id);
		if(s.isPresent()) {
			return s.get().getName();
		}
		return null;
	}

	@Override
	public Iterable<Shipmethod> findAll() {
		// TODO Auto-generated method stub
		return shipmethodRepository.findAll();
	}

	@Override
	public Optional<Shipmethod> findById(Integer id) {
		// TODO Auto-generated method stub
		return shipmethodRepository.findById(id);
	}

}
