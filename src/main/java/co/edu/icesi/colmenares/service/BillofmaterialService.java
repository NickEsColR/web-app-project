package co.edu.icesi.colmenares.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.colmenares.dao.IBillofmaterialDao;
import co.edu.icesi.colmenares.model.prod.Billofmaterial;
@Service
public class BillofmaterialService implements IBillofmaterialService {

	@Autowired
	private IBillofmaterialDao dao;
	
	@Transactional
	@Override
	public void save(Billofmaterial b) {
		// TODO Auto-generated method stub
		dao.save(b);
	}

	@Transactional
	@Override
	public void delete(Billofmaterial b) {
		// TODO Auto-generated method stub
		dao.delete(b);
	}

	@Override
	public Billofmaterial findById(int id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public List<Billofmaterial> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

}
