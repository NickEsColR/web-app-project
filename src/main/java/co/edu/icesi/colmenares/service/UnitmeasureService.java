package co.edu.icesi.colmenares.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.colmenares.dao.IUnitmeasureDao;
import co.edu.icesi.colmenares.model.prod.Unitmeasure;

@Service
public class UnitmeasureService implements IUnitmeasureService {

	@Autowired
	private IUnitmeasureDao dao;
	
	@Override
	public void save(Unitmeasure u) {
		// TODO Auto-generated method stub
		dao.save(u);
	}

	@Override
	public void delete(Unitmeasure u) {
		// TODO Auto-generated method stub
		dao.delete(u);
	}

	@Override
	public Unitmeasure findById(String id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public List<Unitmeasure> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

}
