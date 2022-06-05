package co.edu.icesi.colmenares.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.colmenares.dao.IProductreviewDao;
import co.edu.icesi.colmenares.model.prod.Productreview;
@Service
public class ProductreviewService implements IProductreviewService {

	@Autowired
	private IProductreviewDao dao;
	
	@Transactional
	@Override
	public void save(Productreview p) {
		// TODO Auto-generated method stub
		dao.save(p);
	}

	@Transactional
	@Override
	public void delete(Productreview p) {
		// TODO Auto-generated method stub
		dao.delete(p);
	}

	@Override
	public Productreview findById(int id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public List<Productreview> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

}
