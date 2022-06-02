package co.edu.icesi.colmenares.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.colmenares.model.prod.Product;

@Repository
@Scope("singleton")
public class ProductDao implements IProductDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(Product p) {
		// TODO Auto-generated method stub
		entityManager.merge(p);
	}

	@Override
	public Product findById(int id) {
		// TODO Auto-generated method stub
		return entityManager.find(Product.class, id);
	}

	@Override
	public void delete(Product p) {
		// TODO Auto-generated method stub
		entityManager.remove(p);
	}

	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return entityManager.createQuery("select p from Product p").getResultList();
	}

}
