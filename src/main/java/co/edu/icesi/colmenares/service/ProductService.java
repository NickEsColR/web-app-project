package co.edu.icesi.colmenares.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.colmenares.dao.IProductDao;
import co.edu.icesi.colmenares.model.prod.Product;
@Service
public class ProductService implements IProductService {

	@Autowired
	private IProductDao dao;
	
	@Override
	public void save(Product p) {
		// TODO Auto-generated method stub
		dao.save(p);
	}

	@Override
	public void delete(Product p) {
		// TODO Auto-generated method stub
		dao.delete(p);
	}

	@Override
	public Product findById(int id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

}
